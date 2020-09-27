/*
 * Created by Lee Oh Hyung on 2020/09/28.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Completable
import kr.ohyung.domain.UseCaseTest
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.exception.NoParamsException
import kr.ohyung.domain.repository.BookmarkRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class DropBookmarksTest : UseCaseTest() {

    private lateinit var dropBookmarks: DropBookmarks
    private lateinit var bookmark: Bookmark

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository

    @Before
    override fun setup() {
        super.setup()
        dropBookmarks = DropBookmarks(
            bookmarkRepository = bookmarkRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )

        bookmark = Bookmark(
            id = "Bookmark",
            description = "Drop Bookmark UseCase 테스트 중",
            thumbnail = "N/A",
            likes = 10224,
            username = "이오형"
        )
    }

    @Test
    fun `BookmarkRepository 에서 drop 을 호출할때 UseCase Complete 여부 테스트`()  {
        Mockito.`when`(bookmarkRepository.drop())
            .thenReturn(Completable.complete())

        dropBookmarks.execute()
            .test()
            .assertComplete()
    }

    @Test
    fun `BookmarkRepository 에서 Error 발생 시 캐치 여부 테스트`() {
        val exception = EntityNotFoundException("this is test exception")
        Mockito.`when`(bookmarkRepository.drop())
            .thenReturn(Completable.error(exception))

        dropBookmarks.execute().test().assertError(exception)
    }
}
