/*
 * Created by Lee Oh Hyung on 2020/10/02.
 */
package kr.ohyung.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kr.ohyung.local.database.LocalDatabase
import kr.ohyung.local.mock.MockLocalData
import kr.ohyung.local.model.SearchHistoryRoomObject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class SearchHistoryDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDatabase
    private lateinit var searchHistoryDao: SearchHistoryDao

    @Before
    fun initDb() {
        database = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), LocalDatabase::class.java)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        searchHistoryDao = database.searchHistoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `SearchHistoryDao Insert 동작 테스트`() {
        val searchHistory = MockLocalData.searchHistoryRoomObject
        searchHistoryDao.insert(searchHistory).test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(1)
        searchHistoryDao.hasItem(keyword = searchHistory.keyword).test().assertValue(true)

        // drop table
        searchHistoryDao.drop()

        // insert list
        val searchHistoryList = MockLocalData.searchHistoryRoomObjects
        searchHistoryDao.insert(roomObjects = searchHistoryList).test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(searchHistoryList.count())
        searchHistoryDao.hasItem(keyword = searchHistoryList.first().keyword).test().assertValue(true)
    }

    @Test
    fun `SearchHistoryDao getAll 동작 테스트`() {
        val searchHistory = MockLocalData.searchHistoryRoomObject
        searchHistoryDao.insert(roomObject = searchHistory).test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(1)

        val getAllResult = searchHistoryDao.getAll().blockingGet()
        assertEquals(expected = searchHistory.id, actual = getAllResult.first().id)
        assertEquals(expected = searchHistory.keyword, actual = getAllResult.first().keyword)
        assertEquals(expected = searchHistory.timestamp, actual = getAllResult.first().timestamp)


        // insert list
        searchHistoryDao.drop()
        searchHistoryDao.insert(roomObjects = MockLocalData.searchHistoryRoomObjects)
        searchHistoryDao.getCount().test().assertValue(MockLocalData.searchHistoryRoomObjects.count())

        val getAllResult2 = searchHistoryDao.getAll().blockingGet()
        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().id, actual = getAllResult2.first().id)
        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().keyword, actual = getAllResult2.first().keyword)
        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().timestamp, actual = getAllResult2.first().timestamp)
    }

    @Test
    fun `SearchHistoryDao update 동작 테스트`() {
        // First Insert
        val roomObject = MockLocalData.searchHistoryRoomObject
        searchHistoryDao.insert(roomObject = roomObject).test().assertComplete()
        assertEquals(expected = roomObject.id, actual = searchHistoryDao.getAll().blockingGet().first().id)
        assertEquals(expected = roomObject.keyword, actual = searchHistoryDao.getAll().blockingGet().first().keyword)
        assertEquals(expected = roomObject.timestamp, actual = searchHistoryDao.getAll().blockingGet().first().timestamp)

        // After Update
        val updatedRoomObject = roomObject.copy(keyword = "새로운 검색로로로로로옥~")
        searchHistoryDao.update(roomObject = updatedRoomObject).test().assertComplete()
        assertEquals(expected = updatedRoomObject.id, actual = searchHistoryDao.getAll().blockingGet().first().id)
        assertEquals(expected = updatedRoomObject.keyword, actual = searchHistoryDao.getAll().blockingGet().first().keyword)
        assertEquals(expected = updatedRoomObject.timestamp, actual = searchHistoryDao.getAll().blockingGet().first().timestamp)
    }

    @Test
    fun `SearchHistoryDao primary key autoGenerate 동작 테스트`() {
        val roomObject = SearchHistoryRoomObject(keyword = "아무 검색기록 1111")
        searchHistoryDao.insert(roomObject = roomObject).test().assertComplete()
        searchHistoryDao.insert(roomObject = roomObject.copy(keyword = "아무 검색기록 2222", timestamp = System.currentTimeMillis())).test().assertComplete()
        searchHistoryDao.insert(roomObject = roomObject.copy(keyword = "아무 검색기록 3333", timestamp = System.currentTimeMillis())).test().assertComplete()
        searchHistoryDao.insert(roomObject = roomObject.copy(keyword = "아무 검색기록 4444", timestamp = System.currentTimeMillis())).test().assertComplete()

        searchHistoryDao.getAll().blockingGet().forEachIndexed { index, searchHistoryRoomObject ->
            println(searchHistoryRoomObject)
            assertEquals(expected = index.inc(), actual = searchHistoryRoomObject.id.toInt())
        }
    }

    @Test
    fun `SearchHistoryDao delete 동작 테스트`() {
        val roomObject = MockLocalData.searchHistoryRoomObject
        searchHistoryDao.insert(roomObject = roomObject).test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(1)

        // delete
        searchHistoryDao.delete(keyword = roomObject.keyword).test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(0)

        // insert list
        searchHistoryDao.insert(roomObjects = MockLocalData.searchHistoryRoomObjects).test().assertComplete()
        searchHistoryDao.drop().test().assertComplete()
        searchHistoryDao.getCount().test().assertValue(0)
    }
}
