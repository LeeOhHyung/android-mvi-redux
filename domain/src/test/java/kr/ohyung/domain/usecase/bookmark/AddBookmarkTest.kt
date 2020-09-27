/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Completable
import kr.ohyung.domain.UseCaseTest
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.exception.NoParamsException
import kr.ohyung.domain.repository.BookmarkRepository
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class AddBookmarkTest : UseCaseTest() {

    private lateinit var bookmark: Bookmark
    private lateinit var addBookmark: AddBookmark

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository


    override fun setup() {
        super.setup()
        bookmark = Bookmark(
            id = "HelloWorld",
            description = "addBookmark 테스트 중",
            thumbnail = "N/A",
            likes = 10224,
            username = "이오형"
        )
        addBookmark = AddBookmark(
            bookmarkRepository = bookmarkRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
    }

    @Test(expected = NoParamsException::class)
    fun `AddBookmark 의 params 가 null 일때 에러 발생 여부 테스트`() {
        addBookmark.execute(params = null)
    }

    @Test
    fun `BookmarkRepository 에서 Bookmark insert 후 complete 여부 테스트`() {
        Mockito.`when`(bookmarkRepository.insert(bookmark)).thenReturn(Completable.complete())
        addBookmark.execute(params = bookmark).test().assertComplete()
    }

    @Test
    fun `BookmarkRepository 에서 Bookmark insert 중 에러 발생시 캐치 여부 테스트`() {
        val testNoParamsException = NoParamsException("this is test NoParamsException")
        Mockito.`when`(bookmarkRepository.insert(bookmark)).thenReturn(Completable.error(testNoParamsException))
        addBookmark.execute(params = bookmark).test().assertError(testNoParamsException)

        val testEntityNotFoundException = EntityNotFoundException("this is test EntityNotFoundException")
        Mockito.`when`(bookmarkRepository.insert(bookmark)).thenReturn(Completable.error(testEntityNotFoundException))
        addBookmark.execute(params = bookmark).test().assertError(testEntityNotFoundException)
    }
}
