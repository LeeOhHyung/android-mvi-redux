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
import kr.ohyung.data.source.local.BookmarkLocalDataSource
import kr.ohyung.local.DataSourceTest
import kr.ohyung.local.dao.BookmarkDao
import kr.ohyung.local.database.LocalDatabase
import kr.ohyung.local.mapper.BookmarkMapper
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
class BookmarkLocalDataSourceTest : DataSourceTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDatabase
    private lateinit var bookmarkMapper: BookmarkMapper
    private lateinit var bookmarkLocalDataSource: BookmarkLocalDataSource

    @Mock
    private lateinit var bookmarkDao: BookmarkDao

    @Before
    override fun setup() {
        super.setup()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        bookmarkMapper = BookmarkMapper()
        bookmarkLocalDataSource = BookmarkLocalDataSourceImpl(database.bookmarksDao(), bookmarkMapper)
    }

    @After
    override fun tearDown() {
        database.close()
    }

    @Test
    fun `BookmarkLocalDataSource Insert 동작 테스트`() {
        val dataModel = MockLocalData.bookmarkDataModel
        bookmarkLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        bookmarkLocalDataSource.getCount().test().assertValue(1)
        bookmarkLocalDataSource.hasItem(id = dataModel.id)

        // drop
        bookmarkLocalDataSource.drop().test().assertComplete()

        // insert list
        val dataModels = MockLocalData.bookmarkDataModels
        bookmarkLocalDataSource.insert(dataModels = dataModels).test().assertComplete()
        bookmarkLocalDataSource.hasItem(id = dataModels.first().id).test().assertValue(true)
        bookmarkLocalDataSource.getCount().test().assertValue(dataModels.count())
    }

    @Test
    fun `BookmarkLocalDataSource Update 동작 테스트`() {
        val dataModel = MockLocalData.bookmarkDataModel
        bookmarkLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        bookmarkLocalDataSource.getCount().test().assertValue(1)

        // After Update
        val copiedDataModel = dataModel.copy(description = "수정된 데이터 모델")
        bookmarkLocalDataSource.update(dataModel = copiedDataModel).test().assertComplete()

        val fetchedDataModel = bookmarkLocalDataSource.getAll().blockingGet().first()
        assertEquals(expected = copiedDataModel.id, actual = fetchedDataModel.id)
        assertEquals(expected = copiedDataModel.description, actual = fetchedDataModel.description)
        assertEquals(expected = copiedDataModel.thumbnail, actual = fetchedDataModel.thumbnail)
        assertEquals(expected = copiedDataModel.likes, actual = fetchedDataModel.likes)
        assertEquals(expected = copiedDataModel.username, actual = fetchedDataModel.username)
    }

    @Test
    fun `BookmarkLocalDataSource Delete 동작 테스트`() {
        val dataModel = MockLocalData.bookmarkDataModel
        bookmarkLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        bookmarkLocalDataSource.getCount().test().assertValue(1)

        // After Delete
        bookmarkLocalDataSource.delete(id = dataModel.id).test().assertComplete()
        bookmarkLocalDataSource.getCount().test().assertValue(0)
        bookmarkLocalDataSource.hasItem(id = dataModel.id).test().assertValue(false)
        bookmarkLocalDataSource.getCount().test().assertValue(0)

        // After Drop
        bookmarkLocalDataSource.insert(dataModel = dataModel).test().assertComplete()
        bookmarkLocalDataSource.getCount().test().assertValue(1)
        bookmarkLocalDataSource.drop().test().assertComplete()
        bookmarkLocalDataSource.hasItem(id = dataModel.id).test().assertValue(false)
        bookmarkLocalDataSource.getCount().test().assertValue(0)
    }

    @Test
    fun `BookmarkLocalDataSource hasItem 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val dataModel = MockLocalData.bookmarkDataModel
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when hasItem")
        val mockedBookmarkDataSource = BookmarkLocalDataSourceImpl(bookmarkDao, bookmarkMapper)

        // When
        Mockito.`when`(bookmarkDao.hasItem(id = dataModel.id))
            .thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.hasItem(id = dataModel.id).test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `BookmarkLocalDataSource getAll 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when getAll")
        val mockedBookmarkDataSource = BookmarkLocalDataSourceImpl(bookmarkDao, bookmarkMapper)

        // When
        Mockito.`when`(bookmarkDao.getAll()).thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.getAll()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `BookmarkLocalDataSource getCount 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when getCount")
        val mockedBookmarkDataSource = BookmarkLocalDataSourceImpl(bookmarkDao, bookmarkMapper)

        // When
        Mockito.`when`(bookmarkDao.getCount()).thenReturn(Single.error(exception))

        // Then
        mockedBookmarkDataSource.getCount()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }

    @Test
    fun `BookmarkLocalDataSource update 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        // Given
        val dataModel = MockLocalData.bookmarkDataModel
        val mappedRoomObject = bookmarkMapper.toRoomObject(dataModel = dataModel)
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when update")
        val mockedBookmarkDataSource = BookmarkLocalDataSourceImpl(bookmarkDao, bookmarkMapper)

        // When
        Mockito.`when`(bookmarkDao.update(roomObject = mappedRoomObject)).thenReturn(Completable.error(exception))

        // Then
        bookmarkLocalDataSource.update(dataModel = dataModel).test().assertComplete() // 정상 케이스
        mockedBookmarkDataSource.update(dataModel = dataModel) // 비정상 케이스
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)
    }
}
