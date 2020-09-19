/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

enum class OrderBy(val value: String) : Entity {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular")
}
