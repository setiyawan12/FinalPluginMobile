package yayang.setiyawan.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Books (
    @SerializedName("id_books") var id_books : String? = null,
    @SerializedName("title") var title : String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("description") var description : String? = null,
): Parcelable