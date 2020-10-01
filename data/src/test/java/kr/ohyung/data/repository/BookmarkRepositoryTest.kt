/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.repository

import kr.ohyung.data.RepositoryTest
import kr.ohyung.data.mapper.BookmarkEntityMapper
import kr.ohyung.data.mock.MockBookmarkLocalDataSource
import kr.ohyung.data.mock.MockData
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.domain.exception.DuplicatedEntityException
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.repository.BookmarkRepository
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookmarkRepositoryTest : RepositoryTest() {

    /*
     * Repository 에 대한 CRUD 상황을 가정해서 테스트 케이스를 구성한다.
     */
    private lateinit var bookmarkLocalDataSource: BookmarkLocalDataSource
    private lateinit var bookmarkEntityMapper: BookmarkEntityMapper
    private lateinit var bookmarkRepository: BookmarkRepository

    @Before
    override fun setup() {
        super.setup()
        bookmarkLocalDataSource = MockBookmarkLocalDataSource()
        bookmarkEntityMapper = BookmarkEntityMapper()
        bookmarkRepository = BookmarkRepositoryImpl(
            bookmarkLocalDataSource = bookmarkLocalDataSource,
            mapper = bookmarkEntityMapper
        )
    }

    @Test
    fun `BookmarkRepository compose 함수에서 에러 캐치 여부 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity
        bookmarkRepository.insert(entity = bookmarkEntity)
        assertEquals(expected = 1, bookmarkRepository.getCount().blockingGet())

        // compose() 함수에서 Domain Layer 의 Exception 으로 변환됨.
        bookmarkRepository.delete(id = "이상한 Id").test()
            .assertError(EntityNotFoundException::class.java)
    }

    @Test
    fun `BookmarkRepository hasItem 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity

        // insert
        bookmarkRepository.insert(entity = bookmarkEntity)
        bookmarkRepository.hasItem(id = bookmarkEntity.id).test().assertValue(true)

        // delete
        bookmarkRepository.delete(id = bookmarkEntity.id)
        bookmarkRepository.hasItem(id = bookmarkEntity.id).test().assertValue(false)

        // drop
        bookmarkRepository.insert(entity = bookmarkEntity)
        bookmarkRepository.drop()
        bookmarkRepository.hasItem(id = bookmarkEntity.id).test().assertValue(false)
    }

    @Test
    fun `BookmarkRepository insert 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity

        bookmarkRepository.insert(entity = bookmarkEntity)

        // if id of bookmark is duplicated, will triggering exception.
        bookmarkRepository.insert(entity = bookmarkEntity).test()
            .assertError(DuplicatedEntityException::class.java)
        bookmarkRepository.insert(entities = listOf(bookmarkEntity)).test()
            .assertError(DuplicatedEntityException::class.java)

        bookmarkRepository.insert(entity = bookmarkEntity.copy(id = "Bookmark 2"))
        bookmarkRepository.insert(entity = bookmarkEntity.copy(id = "Bookmark 3"))
        bookmarkRepository.insert(entity = bookmarkEntity.copy(id = "Bookmark 4"))

        bookmarkRepository.getCount().test().assertValue(4)
        bookmarkRepository.hasItem(id = bookmarkEntity.id).test().assertValue(true)
        bookmarkRepository.getAll().test().assertValue { it.count() == 4 }
    }

    @Test
    fun `BookmarkRepository getAll 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity
        val copiedList = listOf(bookmarkEntity, bookmarkEntity.copy(id = "Bookmark 2"), bookmarkEntity.copy(id = "Bookmark 3"))

        // insert
        bookmarkRepository.insert(entities = copiedList)
        val result = bookmarkRepository.getAll().blockingGet()
        assertEquals(expected = copiedList.count(), actual = result.count())
        assertEquals(expected = copiedList.first().id, actual = result.first().id)

        // drop
        bookmarkRepository.drop()
        val result2 = bookmarkRepository.getAll().blockingGet()
        assertEquals(expected = 0, actual = result2.count())
        assertEquals(expected = bookmarkRepository.getCount().blockingGet(), actual = result2.count())
    }

    @Test
    fun `BookmarkRepository delete 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity
        bookmarkRepository.insert(bookmarkEntity)
        bookmarkRepository.insert(bookmarkEntity.copy(id = "Bookmark 2"))
        bookmarkRepository.insert(bookmarkEntity.copy(id = "Bookmark 3"))

        // after insertions
        bookmarkRepository.getCount().test().assertValue(3)

        // doing this for triggering exception
        bookmarkRepository.delete(id = bookmarkEntity.id)

        bookmarkRepository.delete(id = bookmarkEntity.id).test().assertError(EntityNotFoundException::class.java)
        bookmarkRepository.hasItem(id = bookmarkEntity.id).test().assertValue(false)
        bookmarkRepository.getCount().test().assertValue(2)

        val result = bookmarkRepository.getAll().blockingGet()
        assertEquals(expected = false, actual = result.any { it.id == bookmarkEntity.id })
    }

    @Test
    fun `BookmarkRepository update 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity
        bookmarkRepository.insert(entity = bookmarkEntity)
        bookmarkRepository.getCount().test().assertValue(1)

        val updatedBookmarkEntity = bookmarkEntity.copy(description = "수정되었다냥", thumbnail = "썸네일도 수정됨")
        bookmarkRepository.update(entity = updatedBookmarkEntity).test().assertComplete()

        // 정상적으로 업데이트 되었는지 확인.
        bookmarkRepository.getAll().test().assertNoErrors()
        val result = bookmarkRepository.getAll().blockingGet()
        assertEquals(expected = updatedBookmarkEntity.description, result.first().description)
        assertEquals(expected = updatedBookmarkEntity.thumbnail, result.first().thumbnail)

        // 모두 지우고, 다시 업데이트 시도 -> 실패가 기대됨.
        bookmarkRepository.drop()
        bookmarkRepository.update(entity = updatedBookmarkEntity).test()
            .assertError(EntityNotFoundException::class.java)
    }
}
