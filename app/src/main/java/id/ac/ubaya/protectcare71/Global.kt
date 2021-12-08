package id.ac.ubaya.protectcare71

object Global {
    var check_id= 0
    var check_location: String?= null
    var check_time: String?= null
    var username: String? = ""
    var user_id:String? = ""
    var vaccine=0

    val histories = ArrayList<History>()

    val cardColor = arrayOf(
        "red", "yellow", "green"
    )

    const val SHARED_PREFERENCE_APP = "APP_PREFERENCE"
    const val SHARED_PREFERENCE_KEY_USER_ID = "USER_ID_KEY"
    const val SHARED_PREFERENCE_KEY_USERNAME = "USERNAME_KEY"
    const val SHARED_PREFERENCE_KEY_VACCINE = "VACCINE_KEY"
    const val SHARED_PREFERENCE_KEY_CHECK_ID = "CHECK_ID_KEY"
    const val SHARED_PREFERENCE_KEY_CHECK_LOCATION = "CHECK_LOCATION_KEY"
    const val SHARED_PREFERENCE_KEY_CHECK_TIME = "CHECK_TIME_KEY"

}