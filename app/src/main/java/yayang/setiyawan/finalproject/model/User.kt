package yayang.setiyawan.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("id_users") var id_users :Int?,
    @SerializedName("username") var username :String?,
    @SerializedName("token") var token :String?
):Parcelable{
    constructor():this(null,null,null)
}