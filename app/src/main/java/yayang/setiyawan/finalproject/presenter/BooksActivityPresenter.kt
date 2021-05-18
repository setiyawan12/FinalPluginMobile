package yayang.setiyawan.finalproject.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.response.WrappedListResponse
import yayang.setiyawan.finalproject.utilities.APIClient

class BooksActivityPresenter (v:BooksActivityContract.View?):BooksActivityContract.Interaction{
    private var view : BooksActivityContract.View? = v
    private var apiService = APIClient.APIService()
    override fun allUser(token: String) {
        val request = apiService.get(token)
        request.enqueue(object : Callback<WrappedListResponse<Books>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Books>>,
                response: Response<WrappedListResponse<Books>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status == "200"){
                        view?.attachToRecycle(body.data)
                    }else{
                        view?.toast("Sometingwrong")
                    }
                }
            }
            override fun onFailure(call: Call<WrappedListResponse<Books>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")            }

        })
    }

    override fun destroy() {
        view = null
    }
}