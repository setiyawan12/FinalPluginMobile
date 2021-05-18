package yayang.setiyawan.finalproject.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.response.WrappedResponse
import yayang.setiyawan.finalproject.utilities.APIClient

class CreateActiviryPresenter(v:BooksActivityContract.ViewCreate):BooksActivityContract.InteractionPost {
    private var view : BooksActivityContract.ViewCreate?= v
    private var apiService = APIClient.APIService()
    override fun validate(title: String, author: String, description: String): Boolean {
        return true
    }

    override fun save(token: String, title: String, author: String, description: String) {
        apiService.createData(token, title, author, description).enqueue(object  : Callback<WrappedResponse<Books>>{
            override fun onResponse(call: Call<WrappedResponse<Books>>, response: Response<WrappedResponse<Books>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        println("body"+body)
                        view?.success("berhasil")
                    }
                }else{
                    view?.toast("ada yang tidak beres")
                }
                view?.isLoading(false)
            }

            override fun onFailure(call: Call<WrappedResponse<Books>>, t: Throwable) {
                view?.toast("koneksi tidak bisa")
            }

        })
    }

    override fun destroy() {
        view = null
    }

}