/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase

import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import kr.ohyung.domain.UseCaseTest
import kr.ohyung.remote.api.unit.OrderBy
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.exception.NoParamsException
import kr.ohyung.domain.mock.TestExecutors
import kr.ohyung.domain.repository.PhotoRepository
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class GetPhotoSummariesTest : UseCaseTest() {

    private lateinit var mockPhotoSummary: PhotoSummary
    private lateinit var normalPhotoSummary: PhotoSummary
    private lateinit var getPhotoSummaries: GetPhotoSummaries

    @Mock
    private lateinit var photoRepository: PhotoRepository

    override fun setup() {
        super.setup()
        testExecutors = TestExecutors()
        mockPhotoSummary = mock(PhotoSummary::class.java)
        normalPhotoSummary = PhotoSummary(
            id = "",
            width = 1000,
            height = 1000,
            color = "",
            description = "이오형이 만드는 재미있는 안드로이드 프로젝트",
            thumbnail = "",
            fullSizeImageUrl = "",
            regularSizeImageUrl = "",
            likes = 10000,
            username = "이오형"
        )
        getPhotoSummaries = GetPhotoSummaries(
            photoRepository = photoRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.mainThread()
        )
    }

    @Test(expected = NoParamsException::class)
    fun `params 가 null 일때 에러 발생 여부 테스트`() {
        getPhotoSummaries.execute(params = null)
    }

    @Test
    fun `perPage가 2일때 photo 리스트 가져오기`() {
        val params = PhotoParams(perPage = 2)

        `when`(photoRepository.getPhotos(page = params.page, perPage = params.perPage, params.orderBy))
            .thenReturn(Single.just(listOf(mockPhotoSummary, normalPhotoSummary)))

        val result = getPhotoSummaries.execute(params = params).blockingGet()
        assertEquals(params.perPage, result.size)
        assertEquals(listOf(mockPhotoSummary, normalPhotoSummary), result)

        val existedUserName = result.any { it.username == "이오형" }
        assertTrue(existedUserName)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `params에서 page가 변경되어도, perPage 기본값대로 10개씩 가져오는지 테스트`() {
        val params = PhotoParams(page = 2)
        val mockList: List<PhotoSummary> = mock(List::class.java) as List<PhotoSummary>

        `when`(photoRepository.getPhotos(page = params.page, perPage = params.perPage, params.orderBy))
            .thenReturn(Single.just(listOf(mockPhotoSummary)))
        val result = getPhotoSummaries.execute(params = params).blockingGet()
        assertNotEquals(params.perPage, result.size)

        `when`(photoRepository.getPhotos(any(), any(), any()))
            .thenReturn(Single.just(mockList))
        `when`(mockList.count()).thenReturn(params.perPage)
        assertEquals(10, mockList.count())
    }

    @Test
    fun `인기순으로 가져오기 테스트`() {
        val params = PhotoParams(orderBy = OrderBy.POPULAR)
        val copiedList = List(10) { integer -> normalPhotoSummary.copy(likes = integer * 10) }

        `when`(photoRepository.getPhotos(page = params.page, perPage = params.perPage, params.orderBy))
            .thenReturn(Single.just(copiedList))

        val result = getPhotoSummaries.execute(params = params).blockingGet()
        assertEquals(params.perPage, result.size)
        assertEquals(0, result.minOf { it.likes })
        assertEquals(90, result.maxOf { it.likes })
    }
}
