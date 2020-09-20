/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.source

import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.remote.DataSourceTest
import kr.ohyung.remote.mapper.PhotosResponseMapper
import kr.ohyung.remote.mock.dispatcher.PhotosRequestDispatcher
import kr.ohyung.remote.mock.mockPhotosApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class PhotoRemoteDataSourceTest : DataSourceTest(){

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
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getPhotos 에서 통해서 정상적으로 리스트를 가져오는지 테스트`() {
        val photos = photoRemoteDataSource.getPhotos(page = null, perPage = null, orderBy = null).blockingGet()
        println(photos.first())
        assertTrue(photos.isNotEmpty())
    }
}
