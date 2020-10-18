/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.remote.response.geocoding

enum class AddressType(val value: String, val description : String) {
    LEGAL("legalcode", "법정동"),
    ADM("admcode", "행정동"),
    ADDR("addr", "지번주소"),
    ROAD("roadaddr", "도로명주소")
}
