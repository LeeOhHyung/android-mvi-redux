/*
 * Created by Lee Oh Hyung on 2020/09/28.
 */
package kr.ohyung.domain.usecase.bookmark

import io.reactivex.Single
import kr.ohyung.domain.UseCaseTest
import kr.ohyung.domain.entity.Bookmark
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.repository.BookmarkRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import kotlin.test.assertEquals

class GetAllBookmarksTest : UseCaseTest() {

    private lateinit var getAllBookmarks: GetAllBookmarks
    private lateinit var bookmark: Bookmark

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository

    @Before
    override fun setup() {
        super.setup()
        getAllBookmarks = GetAllBookmarks(
            bookmarkRepository = bookmarkRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
        bookmark = Bookmark(
            id = "Bookmark",
            description = "GetAllBookmark 테스트 중",
            thumbnail = "N/A",
            likes = 10224,
            username = "이오형"
        )
    }

    @Test
    fun `BookmarkRepository 의 getAll 호출 시 UseCase 정상 동작 여부 테스트`() {
        Mockito.`when`(bookmarkRepository.getAll())
            .thenReturn(Single.just(listOf(bookmark)))

        val result = getAllBookmarks.execute().blockingGet()
        assertEquals(result.first(), bookmark)
    }

    @Test
    fun `BookmarkRepository 의 getAll 수행 중 에러 발생 시 캐치 여부 테스트`() {
        val exception = EntityNotFoundException("this is test exception")
        Mockito.`when`(bookmarkRepository.getAll())
            .thenReturn(Single.error(exception))

        getAllBookmarks.execute()
            .test()
            .assertError(exception)
    }
}
