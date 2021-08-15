package com.mobiquity.models

import com.google.android.libraries.maps.model.LatLng

data class MapModel(
    val latLng: LatLng,
    val city: String
)