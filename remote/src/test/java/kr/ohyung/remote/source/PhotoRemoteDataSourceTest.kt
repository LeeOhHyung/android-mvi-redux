/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.source

import io.reactivex.Single
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.remote.DataSourceTest
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.mapper.PhotosResponseMapper
import kr.ohyung.remote.mock.dispatcher.PhotosRequestDispatcher
import kr.ohyung.remote.mock.mockPhotosApi
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertTrue

internal class PhotoRemoteDataSourceTest : DataSourceTest(){

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().silent()

    @Mock
    private lateinit var mockPhotosApi: PhotosApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var photosResponseMapper: PhotosResponseMapper
    private lateinit var photoRemoteDataSource: PhotoRemoteDataSource

    @Before
    override fun setup() {
        super.setup()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = PhotosRequestDispatcher()
        mockWebServer.start()

        photosResponseMapper = PhotosResponseMapper()
        photoRemoteDataSource = PhotoRemoteDataSourceImpl(mockPhotosApi(mockWebServer), photosResponseMapper)
    }

    @After
    override fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getPhotos 에서 통해서 정상적으로 리스트를 가져오는지 테스트`() {
        val photos = photoRemoteDataSource.getPhotos(page = null, perPage = null, orderBy = null).blockingGet()
        assertTrue(photos.isNotEmpty())
    }

    @Test
    fun `HttpException 400에러 발생할때 캐치할 수 있는지 테스트`() {
        // Given
        val httpException = HttpException(Response.error<Nothing>(400, "".toResponseBody(null)))
        doReturn(Single.error<Nothing>(httpException))
            .`when`(mockPhotosApi)
            .getPhotos(anyInt(), anyInt(), anyString())

        PhotoRemoteDataSourceImpl(mockPhotosApi, photosResponseMapper)
            .getPhotos(page = 1, perPage = 10, orderBy = "popular") // When
            .test()
            .assertError(NetworkException.BadRequestException::class.java) // Then
    }

    @Test
    fun `HttpException 401에러 발생할때 캐치할 수 있는지 테스트`() {
        // Given
        val httpException = HttpException(Response.error<Nothing>(401, "".toResponseBody(null)))
        doReturn(Single.error<Nothing>(httpException))
            .`when`(mockPhotosApi)
            .getPhotos(anyInt(), anyInt(), anyString())

        PhotoRemoteDataSourceImpl(mockPhotosApi, photosResponseMapper)
            .getPhotos(page = 1, perPage = 10, orderBy = "popular") // When
            .test()
            .assertError(NetworkException.UnauthorizedException::class.java) // Then
    }

    @Test
    fun `HttpException 404에러 발생할때 캐치할 수 있는지 테스트`() {
        // Given
        val httpException = HttpException(Response.error<Nothing>(404, "".toResponseBody(null)))
        doReturn(Single.error<Nothing>(httpException))
            .`when`(mockPhotosApi)
            .getPhotos(anyInt(), anyInt(), anyString())

        PhotoRemoteDataSourceImpl(mockPhotosApi, photosResponseMapper)
            .getPhotos(page = 1, perPage = 10, orderBy = "popular") // When
            .test()
            .assertError(NetworkException.NotFoundException::class.java) // Then
    }
}
