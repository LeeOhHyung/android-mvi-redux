/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.repository

import kr.ohyung.data.RepositoryTest
import kr.ohyung.data.mapper.SearchHistoryEntityMapper
import kr.ohyung.data.mock.MockData
import kr.ohyung.data.mock.MockSearchHistoryLocalDataSource
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.domain.exception.DuplicatedEntityException
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.repository.SearchHistoryRepository
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchHistoryRepositoryTest : RepositoryTest() {

    /*
     * Repository 에 대한 CRUD 상황을 가정해서 테스트 케이스를 구성한다.
     */
    private lateinit var searchHistoryLocalDataSource: SearchHistoryLocalDataSource
    private lateinit var searchHistoryEntityMapper: SearchHistoryEntityMapper
    private lateinit var searchHistoryRepository: SearchHistoryRepository

    @Before
    override fun setup() {
        super.setup()
        searchHistoryLocalDataSource = MockSearchHistoryLocalDataSource()
        searchHistoryEntityMapper = SearchHistoryEntityMapper()
        searchHistoryRepository = SearchHistoryRepositoryImpl(
            searchHistoryLocalDataSource = searchHistoryLocalDataSource,
            mapper = searchHistoryEntityMapper
        )
    }

    @Test
    fun `SearchHistoryRepository compose 함수에서 에러 캐치 여부 테스트`() {
        val searchHistoryEntity = MockData.searchHistoryEntity
        searchHistoryRepository.insert(entity = searchHistoryEntity)
        assertEquals(expected = 1, searchHistoryRepository.getCount().blockingGet())

        // insertions
        searchHistoryRepository.insert(entity = searchHistoryEntity).test()
            .assertError(DuplicatedEntityException::class.java)
        searchHistoryRepository.insert(entities = listOf(searchHistoryEntity)).test()
            .assertError(DuplicatedEntityException::class.java)
        searchHistoryRepository.drop()
        searchHistoryRepository.insert(entity = searchHistoryEntity).test()
            .assertComplete()

        // delete, update
        searchHistoryRepository.delete(keyword = "이상한 검색기록").test()
            .assertError(EntityNotFoundException::class.java)
        searchHistoryRepository.update(entity = searchHistoryEntity.copy(id = 2L)).test()
            .assertError(EntityNotFoundException::class.java)
    }

    @Test
    fun `SearchHistoryRepository insert 동작 테스트`() {
        val searchHistoryEntity = MockData.searchHistoryEntity
        searchHistoryRepository.insert(entity = searchHistoryEntity)
        searchHistoryRepository.getCount().test().assertValue(1)
        searchHistoryRepository.hasItem(keyword = searchHistoryEntity.keyword).test().assertValue(true)

        val getAllResult = searchHistoryRepository.getAll().blockingGet()
        assertEquals(expected = 1, actual = getAllResult.count())
        assertEquals(expected = searchHistoryEntity.keyword, actual = getAllResult.first().keyword)

        searchHistoryRepository.insert(entities = listOf(searchHistoryEntity)).test()
            .assertError(DuplicatedEntityException::class.java)
        searchHistoryRepository.drop()
        searchHistoryRepository.insert(entities = listOf(searchHistoryEntity))
        searchHistoryRepository.getCount().test().assertValue(1)
        searchHistoryRepository.hasItem(keyword = searchHistoryEntity.keyword).test()
            .assertValue(true)
    }

    @Test
    fun `SearchHistoryRepository update 동작 테스트`() {
        val searchHistoryEntity = MockData.searchHistoryEntity
        searchHistoryRepository.insert(entity = searchHistoryEntity)
        searchHistoryRepository.getCount().test().assertValue(1)

        // 업데이트
        val newSearchHistory = searchHistoryEntity.copy(keyword = "새로운 검색기록")
        searchHistoryRepository.update(entity = newSearchHistory).test()
            .assertComplete()
        val fetchedData = searchHistoryRepository.getAll().blockingGet().first()
        assertEquals(expected = newSearchHistory.keyword, actual = fetchedData.keyword)

        // drop 후에 업데이트 시도 ->
        searchHistoryRepository.drop().blockingGet()
        searchHistoryRepository.update(entity = searchHistoryEntity).test()
            .assertError(EntityNotFoundException::class.java)
    }

    @Test
    fun `SearchHistoryRepository delete 동작 테스트`() {
        val searchHistoryEntity = MockData.searchHistoryEntity
        searchHistoryRepository.insert(entity = searchHistoryEntity)
        searchHistoryRepository.getCount().test().assertValue(1)

        // delete
        searchHistoryRepository.delete(keyword = searchHistoryEntity.keyword).test()
            .assertComplete()
        searchHistoryRepository.hasItem(keyword = searchHistoryEntity.keyword).test()
            .assertValue(false)
        searchHistoryRepository.getCount().test().assertValue(0)
        val result = searchHistoryRepository.getAll().blockingGet()
        assertEquals(expected = 0, result.count())

        // drop
        searchHistoryRepository.insert(entity = searchHistoryEntity)
        searchHistoryRepository.getCount().test().assertValue(1)
        searchHistoryRepository.drop()
        searchHistoryRepository.getCount().test().assertValue(0)
        searchHistoryRepository.hasItem(keyword = searchHistoryEntity.keyword).test().assertValue(false)
        assertEquals(expected = 0, searchHistoryRepository.getAll().blockingGet().count())
    }
}
