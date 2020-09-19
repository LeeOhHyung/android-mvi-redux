/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.DomainEntity

enum class OrderBy(val value: String) : DomainEntity {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular")
}
