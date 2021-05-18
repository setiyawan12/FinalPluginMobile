package yayang.setiyawan.finalproject.utilities

import retrofit2.Call
import retrofit2.http.*
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.model.User
import yayang.setiyawan.finalproject.response.WrappedListResponse
import yayang.setiyawan.finalproject.response.WrappedResponse

interface APIService {
    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(@Field("email")email : String, @Field("password")password : String): Call<WrappedResponse<User>>

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register (@Field("username")username:String, @Field("email")email : String, @Field("password")password : String):Call<WrappedResponse<User>>

    @GET("books")
    fun get (@Header("x-access-token")token:String):Call<WrappedListResponse<Books>>

    @FormUrlEncoded
    @POST("books")
    fun createData(@Header("x-access-token") token :String,@Field("title")title:String,@Field("author")author:String,@Field("description")description:String):Call<WrappedResponse<Books>>
    @DELETE("books/{id}")
    fun deleteData (@Path("id")id:Int,@Header("x-access-token") token : String
    ):Call<WrappedResponse<Books>>
}