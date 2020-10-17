package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class LegalName(
    val latitude: Double,
    val longitude: Double,
    val countryCode: String,
    val city: String
): Entity
