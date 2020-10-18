/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.entity.unit

import kr.ohyung.domain.Entity

enum class TempUnit(val value: String) : Entity{
    IMPERIAL("imperial"),
    METRIC("metric"),
    KELVIN("kelvin")
}
