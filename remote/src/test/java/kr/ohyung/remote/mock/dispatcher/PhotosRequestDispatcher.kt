/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.mock.dispatcher

import kr.ohyung.remote.mock.getJsonFromFile
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

internal class PhotosRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            (request.path?.startsWith("/photos") == true) ->
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJsonFromFile("response/get_photos_response.json"))

            else -> throw IllegalArgumentException("invalid request path : ${request.path}")
        }
    }
}
