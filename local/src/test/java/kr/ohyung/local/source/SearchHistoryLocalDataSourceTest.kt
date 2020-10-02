/*
 * Created by Lee Oh Hyung on 2020/10/02.
 */
package kr.ohyung.local.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.exception.DatabaseException
import kr.ohyung.data.source.local.SearchHistoryLocalDataSource
import kr.ohyung.local.DataSourceTest
import kr.ohyung.local.dao.SearchHistoryDao
import kr.ohyung.local.database.LocalDatabase
import kr.ohyung.local.mapper.SearchHistoryMapper
import kr.ohyung.local.mock.MockLocalData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class SearchHistoryLocalDataSourceTest : DataSourceTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDatabase
    private lateinit var searchHistoryMapper: SearchHistoryMapper
    private lateinit var searchHistoryLocalDataSource: SearchHistoryLocalDataSource

    @Mock
    private lateinit var searchHistoryDao: SearchHistoryDao

    @Before
    override fun setup() {
        super.setup()
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), LocalDatabase::class.java)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        searchHistoryMapper = SearchHistoryMapper()
        searchHistoryLocalDataSource = SearchHistoryLocalDataSourceImpl(database.searchHistoryDao(), searchHistoryMapper)
    }

    @After
    override fun tearDown() {
        database.close()
    }

    @Test
    fun `SearchHistoryLocalDataSource Insert 동작 테스트`() {
        val dataModel = MockLocalData.searchHistoryDataModel
        searchHistoryLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        searchHistoryLocalDataSource.getCount().test().assertValue(1)
        searchHistoryLocalDataSource.hasItem(keyword = dataModel.keyword)

        // drop
        searchHistoryLocalDataSource.drop().test().assertComplete()

        // insert list
        val dataModels = MockLocalData.searchHistoryDataModels
        searchHistoryLocalDataSource.insert(dataModels = dataModels).test().assertComplete()
        searchHistoryLocalDataSource.hasItem(keyword = dataModels.first().keyword).test().assertValue(true)
        searchHistoryLocalDataSource.getCount().test().assertValue(dataModels.count())
    }

    @Test
    fun `SearchHistoryLocalDataSource Update 동작 테스트`() {
        val dataModel = MockLocalData.searchHistoryDataModel
        searchHistoryLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        searchHistoryLocalDataSource.getCount().test().assertValue(1)

        // After Update
        val copiedDataModel = dataModel.copy(keyword = "새로운 검색기록")
        searchHistoryLocalDataSource.update(dataModel = copiedDataModel).test().assertComplete()

        val fetchedDataModel = searchHistoryLocalDataSource.getAll().blockingGet().first()
        assertEquals(expected = copiedDataModel.id, actual = fetchedDataModel.id)
        assertEquals(expected = copiedDataModel.keyword, actual = fetchedDataModel.keyword)
        assertEquals(expected = copiedDataModel.timestamp, actual = fetchedDataModel.timestamp)
    }

    @Test
    fun `SearchHistoryLocalDataSource Delete 동작 테스트`() {
        val dataModel = MockLocalData.searchHistoryDataModel
        searchHistoryLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        searchHistoryLocalDataSource.getCount().test().assertValue(1)

        // After Delete
        searchHistoryLocalDataSource.delete(keyword = dataModel.keyword).test().assertComplete()
        searchHistoryLocalDataSource.getCount().test().assertValue(0)
        searchHistoryLocalDataSource.hasItem(keyword = dataModel.keyword).test().assertValue(false)
        searchHistoryLocalDataSource.getCount().test().assertValue(0)

        // After Drop
        searchHistoryLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        searchHistoryLocalDataSource.getCount().test().assertValue(1)
        searchHistoryLocalDataSource.drop().test().assertComplete()
        searchHistoryLocalDataSource.hasItem(keyword = dataModel.keyword).test().assertValue(false)
        searchHistoryLocalDataSource.getCount().test().assertValue(0)
    }

    @Test
    fun `SearchHistoryLocalDataSource hasItem 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val dataModel = MockLocalData.searchHistoryDataModel
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when hasItem")
        val mockedBookmarkDataSource = SearchHistoryLocalDataSourceImpl(searchHistoryDao, searchHistoryMapper)

        // When
        Mockito.`when`(searchHistoryDao.hasItem(keyword = dataModel.keyword))
            .thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.hasItem(keyword = dataModel.keyword).test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `SearchHistoryLocalDataSource getAll 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when getAll")
        val mockedBookmarkDataSource = SearchHistoryLocalDataSourceImpl(searchHistoryDao, searchHistoryMapper)

        // When
        Mockito.`when`(searchHistoryDao.getAll()).thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.getAll()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `SearchHistoryLocalDataSource getCount 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when getCount")
        val mockedBookmarkDataSource = SearchHistoryLocalDataSourceImpl(searchHistoryDao, searchHistoryMapper)

        // When
        Mockito.`when`(searchHistoryDao.getCount()).thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.getCount()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `SearchHistoryLocalDataSource update 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val dataModel = MockLocalData.searchHistoryDataModel
        val mappedRoomObject = searchHistoryMapper.toRoomObject(dataModel = dataModel)
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when update")
        val mockedBookmarkDataSource = SearchHistoryLocalDataSourceImpl(searchHistoryDao, searchHistoryMapper)

        // When
        Mockito.`when`(searchHistoryDao.update(roomObject = mappedRoomObject)).thenReturn(Completable.error(exception))

        // Then
        searchHistoryLocalDataSource.update(dataModel = dataModel).test().assertComplete() // 정상 케이스
        mockedBookmarkDataSource.update(dataModel = dataModel) // 비정상 케이스
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

}
