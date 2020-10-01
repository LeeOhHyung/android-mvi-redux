/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.repository

import kr.ohyung.data.RepositoryTest
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.mock.MockData
import kr.ohyung.data.mock.MockPhotoRemoteDataSource
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.usecase.PhotoParams
import org.junit.Test
import kotlin.test.assertEquals

class PhotoRepositoryTest : RepositoryTest() {

    private lateinit var photoRemoteDataSource: PhotoRemoteDataSource
    private lateinit var photoEntityMapper: PhotoEntityMapper
    private lateinit var photoRepository: PhotoRepository

    override fun setup() {
        super.setup()
        photoRemoteDataSource = MockPhotoRemoteDataSource()
        photoEntityMapper = PhotoEntityMapper()
        photoRepository = PhotoRepositoryImpl(photoRemoteDataSource, photoEntityMapper)
    }

    @Test
    fun `PhotoRepository 에서 사진 가지고 오기 테스트`() {
        val params = PhotoParams()
        val result = photoRepository.getPhotos(page = params.page, perPage =  params.perPage, orderBy = params.orderBy).blockingGet()
        assertEquals(true, result.isNotEmpty())
        assertEquals(MockData.photoSummaryDataModels.count(), result.count())
    }

    @Test
    fun `PhotoRepository 에서 올바른 데이터를 가지오 오는지 테스트`() {
        val params = PhotoParams()
        val result = photoRepository.getPhotos(page = params.page, perPage =  params.perPage, orderBy = params.orderBy).blockingGet()
        val firstPhotoSummary = result.first()
        assertEquals("Photo", firstPhotoSummary.id)
        assertEquals(MockData.photoSummaryDataModel.description, firstPhotoSummary.description)
        assertEquals(MockData.photoSummaryDataModel.width, firstPhotoSummary.width)
        assertEquals(MockData.photoSummaryDataModel.height, firstPhotoSummary.height)
        assertEquals(MockData.photoSummaryDataModel.username, firstPhotoSummary.username)
    }
}
