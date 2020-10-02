/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kr.ohyung.local.database.LocalDatabase
import kr.ohyung.local.mock.MockLocalData
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
class BookmarkDaoTest {

    @get:Rule
    val instantTaskRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `BookmarkDao insert Test`() {
        val bookmarkRoomObject = MockLocalData.bookmarkRoomObject
        database.bookmarksDao().insert(roomObject = bookmarkRoomObject).test().assertComplete()
        database.bookmarksDao().hasItem(id = bookmarkRoomObject.id).test().assertValue(true)
        database.bookmarksDao().getCount().test().assertValue(1)

        val fetchedBookmark = database.bookmarksDao().getAll().blockingGet().first()
        assertEquals(bookmarkRoomObject.id, fetchedBookmark.id)
        assertEquals(bookmarkRoomObject.description, fetchedBookmark.description)
        assertEquals(bookmarkRoomObject.likes, fetchedBookmark.likes)

        val bookmarkRoomObjects = MockLocalData.bookmarkRoomObjects
        database.bookmarksDao().drop()
        database.bookmarksDao().insert(roomObjects = bookmarkRoomObjects).test().assertComplete()
        database.bookmarksDao().getCount().test().assertValue(bookmarkRoomObjects.count())

        val fetchedBookmarks = database.bookmarksDao().getAll().blockingGet().first()
        assertEquals(expected = bookmarkRoomObjects.first().id, actual = fetchedBookmarks.id)
        assertEquals(expected = bookmarkRoomObjects.first().description, actual = fetchedBookmarks.description)
        assertEquals(expected = bookmarkRoomObjects.first().likes, actual = fetchedBookmarks.likes)
    }

    @Test
    fun `BookmarkDao getAll Test`() {
        val bookmarkRoomObject = MockLocalData.bookmarkRoomObject
        database.bookmarksDao().insert(roomObject = bookmarkRoomObject).test().assertComplete()

        var firstItem = database.bookmarksDao().getAll().blockingGet().first()
        assertEquals(expected = bookmarkRoomObject.id, firstItem.id)
        assertEquals(expected = bookmarkRoomObject.description, firstItem.description)
        assertEquals(expected = bookmarkRoomObject.thumbnail, firstItem.thumbnail)
        assertEquals(expected = bookmarkRoomObject.likes, firstItem.likes)
        assertEquals(expected = bookmarkRoomObject.username, firstItem.username)

        // drop table
        database.bookmarksDao().drop().test().assertComplete()

        val roomObjects = MockLocalData.bookmarkRoomObjects
        database.bookmarksDao().insert(roomObjects = roomObjects).test().assertComplete()
        database.bookmarksDao().getCount().test().assertValue(1)
        firstItem = database.bookmarksDao().getAll().blockingGet().first()
        assertEquals(expected = bookmarkRoomObject.id, firstItem.id)
        assertEquals(expected = bookmarkRoomObject.description, firstItem.description)
        assertEquals(expected = bookmarkRoomObject.thumbnail, firstItem.thumbnail)
        assertEquals(expected = bookmarkRoomObject.likes, firstItem.likes)
        assertEquals(expected = bookmarkRoomObject.username, firstItem.username)
    }

    @Test
    fun `BookmarkDao update Test`() {
        val bookmarkRoomObject = MockLocalData.bookmarkRoomObject
        val testDao = database.bookmarksDao()
        testDao.insert(roomObject = bookmarkRoomObject).test().assertComplete()

        val updatedRoomObject = testDao.get(id = bookmarkRoomObject.id)
            .blockingGet()
            .first()
            .copy(description = "수정 테스트 중!!")
        testDao.update(updatedRoomObject).test().assertComplete()
        val result = testDao.get(id = bookmarkRoomObject.id).blockingGet().first()
        assertEquals(expected = "수정 테스트 중!!", result.description)

        testDao
            .update(roomObject = bookmarkRoomObject.copy(description = "바로바로 수정해보는 중"))
            .test()
            .assertComplete()
    }

    @Test(expected = NoSuchElementException::class)
    fun `BookmarkDao delete Test`() {
        val bookmarkRoomObject = MockLocalData.bookmarkRoomObject
        database.bookmarksDao().insert(roomObject = bookmarkRoomObject).test().assertComplete()
        database.bookmarksDao().hasItem(id = bookmarkRoomObject.id).test().assertValue(true)

        // after delete
        database.bookmarksDao().delete(id = bookmarkRoomObject.id).test().assertComplete()
        database.bookmarksDao().hasItem(id = bookmarkRoomObject.id).test().assertValue(false)
        database.bookmarksDao().getCount().test().assertValue(0)
        database.bookmarksDao().getAll().blockingGet().first() // this is error
    }
}
