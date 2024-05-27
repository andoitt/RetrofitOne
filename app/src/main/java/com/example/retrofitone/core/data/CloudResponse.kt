package com.example.retrofitone.core.data

import com.google.gson.annotations.SerializedName


data class CloudResponse(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("info")
    val info: Info? = null

)

/*data class Results(
    @SerializedName("gender")
    val gender: String,

)*/


class Coordinates {
    var latitude: String? = null
    var longitude: String? = null
}


class Dob {
  //  var date: Date? = null
    var age = 0
}


class Id {
    var name: String? = null
    var value: String? = null
}


class Info {
    var seed: String? = null
    var results = 0
    var page = 0
    var version: String? = null
}


data class Location (
    var street: Street? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var postcode: Int = 0,
    var coordinates: Coordinates? = null,
    var timezone: Timezone? = null,
)


class Login {
    var uuid: String? = null
    var username: String? = null
    var password: String? = null
    var salt: String? = null
    var md5: String? = null
    var sha1: String? = null
    var sha256: String? = null
}


class Name {
    var title: String? = null
    var first: String? = null
    var last: String? = null
}


class Picture {
    var large: String? = null
    var medium: String? = null
    var thumbnail: String? = null
}


class Registered {
 //   var date: Date? = null
    var age = 0
}


data class Result (
    var gender: String? = null,
    var name: Name? = null,
    var location: Location? = null,
    var email: String? = null,
    var login: Login? = null,
    var dob: Dob? = null,
    var registered: Registered? = null,
    var phone: String? = null,
    var cell: String? = null,
    var id: Id? = null,
    var picture: Picture? = null,
    var nat: String? = null,
)





class Street {
    var number = 0
    var name: String? = null
}


class Timezone {
    var offset: String? = null
    var description: String? = null
}