/*
 * Created by Lee Oh Hyung on 2020/09/30.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.domain.UseCaseTest
import kr.ohyung.domain.entity.SearchHistory
import kr.ohyung.domain.exception.EntityNotFoundException
import kr.ohyung.domain.exception.NoParamsException
import kr.ohyung.domain.repository.SearchHistoryRepository
import kr.ohyung.domain.usecase.search.AddSearchHistory
import kr.ohyung.domain.usecase.search.DeleteSearchHistory
import kr.ohyung.domain.usecase.search.DropSearchHistory
import kr.ohyung.domain.usecase.search.GetAllSearchHistory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class SearchHistoryUseCaseTest : UseCaseTest() {

    private lateinit var addSearchHistory: AddSearchHistory
    private lateinit var deleteSearchHistory: DeleteSearchHistory
    private lateinit var dropSearchHistory: DropSearchHistory
    private lateinit var getAllSearchHistory: GetAllSearchHistory

    private lateinit var searchHistory: SearchHistory
    private lateinit var copiedList: List<SearchHistory>

    @Mock
    private lateinit var searchHistoryRepository: SearchHistoryRepository

    @Before
    override fun setup() {
        super.setup()
        addSearchHistory = AddSearchHistory(
            searchHistoryRepository = searchHistoryRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
        deleteSearchHistory = DeleteSearchHistory(
            searchHistoryRepository = searchHistoryRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
        dropSearchHistory = DropSearchHistory(
            searchHistoryRepository = searchHistoryRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
        getAllSearchHistory = GetAllSearchHistory(
            searchHistoryRepository = searchHistoryRepository,
            executorThread = testExecutors.io(),
            postExecutionThread = testExecutors.main()
        )
        searchHistory = SearchHistory(
            id = 1,
            keyword = "이오형의 재미있는 안드로이드 프로젝트",
            timestamp = System.currentTimeMillis()
        )
        copiedList = listOf(
            searchHistory,
            searchHistory.copy(keyword = "테스트 중~", timestamp = System.currentTimeMillis()),
            searchHistory.copy(keyword = "커피", timestamp = System.currentTimeMillis())
        )
    }

    @Test(expected = NoParamsException::class)
    fun `AddSearchHistory 의 params 가 null 일때 에러 발생 여부 테스트`() {
        addSearchHistory.execute(params = null)
    }

    @Test(expected = NoParamsException::class)
    fun `DeleteSearchHistory 의 params 가 null 일때 에러 발생 여부 테스트`() {
        deleteSearchHistory.execute(params = null)
    }

    @Test
    fun `SearchHistoryRepository 에서 insert 동작 테스트`() {
        Mockito.`when`(searchHistoryRepository.insert(searchHistory))
            .thenReturn(Completable.complete())

        addSearchHistory.execute(params = searchHistory)
            .test()
            .assertComplete()
    }

    @Test
    fun `SearchHistoryRepository 에서 insert 동작 중 에러 발생 시 캐치 여부 테스트`() {
        val testException = EntityNotFoundException("this is test exception")
        Mockito.`when`(searchHistoryRepository.insert(searchHistory))
            .thenReturn(Completable.error(testException))

        addSearchHistory.execute(params = searchHistory)
            .test()
            .assertError(testException)
    }

    @Test
    fun `DeleteSearchHistory 정상 동작 테스트`() {
        if(copiedList.any { it.keyword == searchHistory.keyword }) {
            Mockito.`when`(searchHistoryRepository.delete(keyword = searchHistory.keyword))
                .thenReturn(Completable.complete())

            val expectedResult = copiedList.minus(element = searchHistory)
                .any { it.keyword == searchHistory.keyword }
            assertFalse(actual = expectedResult)
        }

        deleteSearchHistory.execute(params = searchHistory.keyword)
            .test()
            .assertComplete()
    }

    @Test
    fun `SearchHistoryRepository 에서 delete 할 Entity 찾을 수 없을때 DeleteSearchHistoryUseCase 에러 발생 여부 테스트`() {
        val expectedException = EntityNotFoundException("검색 기록을 찾을 수 없음!!")

        if(copiedList.any { it.keyword == "아무런 검색값" }.not()) {
            Mockito.`when`(searchHistoryRepository.delete(keyword = searchHistory.keyword))
                .thenReturn(Completable.error(expectedException))
        } else {
            Mockito.`when`(searchHistoryRepository.delete(keyword = searchHistory.keyword))
                .thenReturn(Completable.complete())
            val result = copiedList.minus(element = searchHistory).any { it.keyword == searchHistory.keyword }
            assertFalse(actual = result)
        }

        deleteSearchHistory.execute(params = searchHistory.keyword)
            .test()
            .assertError(expectedException)
    }

    @Test
    fun `SearchHistoryRepository 에서 Drop 동작 테스트`() {
        Mockito.`when`(searchHistoryRepository.drop()).thenReturn(Completable.complete())

        val droppedList = copiedList.drop()
        Mockito.`when`(searchHistoryRepository.getAll()).thenReturn(Single.just(droppedList))

        dropSearchHistory.execute()
            .test()
            .assertComplete()

        getAllSearchHistory.execute()
            .test()
            .assertValue(droppedList)
        assertEquals(expected = true, actual = getAllSearchHistory.execute().blockingGet().isEmpty())
    }

    @Test
    fun `SearchHistoryRepository 에서 getAll 동작 테스트`() {
        val droppedList = copiedList.drop()
        Mockito.`when`(searchHistoryRepository.getAll())
            .thenReturn(Single.just(copiedList))

        getAllSearchHistory.execute()
            .test()
            .assertValue(copiedList)
        assertEquals(expected = 3, getAllSearchHistory.execute().blockingGet().count())

        if(droppedList.isEmpty()) {
            val expectedException = EntityNotFoundException("가져올 데이터가 없음!!")
            Mockito.`when`(searchHistoryRepository.getAll())
                .thenReturn(Single.error(expectedException))

            getAllSearchHistory.execute()
                .test()
                .assertError(expectedException)
        }
    }
}

fun <T> List<T>.drop() = drop(count())
