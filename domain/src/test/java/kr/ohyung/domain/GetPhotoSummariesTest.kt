/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain

import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import kr.ohyung.domain.entity.OrderBy
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.exception.NoParamsException
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.usecase.GetPhotoSummaries
import kr.ohyung.domain.usecase.PhotoParams
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class GetPhotoSummariesTest : UseCaseTest() {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val expectException: ExpectedException = ExpectedException.none()

    private lateinit var getPhotoSummaries: GetPhotoSummaries
    private val testExecutors = TestExecutors()
    private lateinit var mockPhotoSummary: PhotoSummary
    private lateinit var normalPhotoSummary: PhotoSummary

    @Mock
    lateinit var photoRepository: PhotoRepository

    override fun setup() {
        super.setup()
        mockPhotoSummary = mock(PhotoSummary::class.java)
        normalPhotoSummary = PhotoSummary(
            id = "",
            width = 1000,
            height = 1000,
            color = "",
            description = "이오형이 만드는 재미있는 안드로이드 프로젝트",
            thumbnail = "",
            likes = 10000,
            username = "이오형"
        )
        getPhotoSummaries = GetPhotoSummaries(
            photoRepository = photoRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
    }

    @Test
    fun `params가 null일때 에러 발생 여부 테스트`() {
        expectException.expect(NoParamsException::class.java)
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

        `when`(photoRepository.getPhotos(page = params.page, perPage = params.perPage, params.orderBy))
            .thenReturn(Single.just(listOf( // size = 10
                normalPhotoSummary.copy(likes = 10),
                normalPhotoSummary.copy(likes = 20),
                normalPhotoSummary.copy(likes = 34),
                normalPhotoSummary.copy(likes = 80),
                normalPhotoSummary.copy(likes = 110),
                normalPhotoSummary.copy(likes = 230),
                normalPhotoSummary.copy(likes = 96),
                normalPhotoSummary.copy(likes = 12304),
                normalPhotoSummary.copy(likes = 111),
                normalPhotoSummary.copy(likes = 15),
            )))

        val result = getPhotoSummaries.execute(params = params).blockingGet()
        assertEquals(params.perPage, result.size)
        assertEquals(10, result.minOf { it.likes })
        assertEquals(12304, result.maxOf { it.likes })
    }
}
