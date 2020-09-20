/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.mock

import com.google.common.io.Resources
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import java.io.File
import java.net.URL

internal val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

internal val moshi: Moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

@Suppress("UnstableApiUsage")
internal fun getJsonFromFile(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}
