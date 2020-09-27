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

class DeleteBookmarkTest : UseCaseTest() {

    private lateinit var bookmark: Bookmark
    private lateinit var deleteBookmark: DeleteBookmark

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository

    override fun setup() {
        super.setup()
        bookmark = Bookmark(
            id = "HelloWorld",
            description = "deleteBookmark 테스트 중",
            thumbnail = "N/A",
            likes = 10224,
            username = "이오형"
        )
        deleteBookmark = DeleteBookmark(
            bookmarkRepository = bookmarkRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
    }

    @Test(expected = NoParamsException::class)
    fun `DeleteBookmark 의 params 가 null 일때 에러 발생 여부 테스트`() {
        deleteBookmark.execute(params = null)
    }

    @Test
    fun `BookmarkRepository 에서 Bookmark delete 후 complete 여부 테스트`() {
        Mockito.`when`(bookmarkRepository.delete(id = "HelloWorld")).thenReturn(Completable.complete())
        deleteBookmark.execute(params = bookmark.id).test().assertComplete()
    }

    @Test
    fun `BookmarkRepository 에서 Bookmark 가 없을때 에러 발생 여부 테스트`() {
        val copiedList = List(size = 5) { integer -> bookmark.copy(id = "TestBookmark$integer") }
        if(copiedList.any { it.id == bookmark.id }.not())
            Mockito.`when`(bookmarkRepository.delete(id = bookmark.id))
                .thenReturn(Completable.error(EntityNotFoundException()))

        deleteBookmark.execute(params = bookmark.id)
            .test()
            .assertError(EntityNotFoundException::class.java)
    }
}
