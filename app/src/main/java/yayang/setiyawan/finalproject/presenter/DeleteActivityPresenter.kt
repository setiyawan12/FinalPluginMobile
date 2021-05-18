package yayang.setiyawan.finalproject.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.response.WrappedResponse
import yayang.setiyawan.finalproject.utilities.APIClient

class DeleteActivityPresenter (v:BooksActivityContract.ViewDelete):BooksActivityContract.InteractionDelete{
    private var view : BooksActivityContract.ViewDelete? = v
    private var apiService = APIClient.APIService()
    override fun delete(id: Int, token: String) {
        val request = apiService.deleteData(id, token)
        request.enqueue(object : Callback<WrappedResponse<Books>>{
            override fun onResponse(
                call: Call<WrappedResponse<Books>>,
                response: Response<WrappedResponse<Books>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        view?.success("Berhasil")
                    }
                }else{
                    view?.toast("Ada yang tidak beres, coba lagi nanti, atau hubungi admin")
                }
                view?.isLoading(false)

            }

            override fun onFailure(call: Call<WrappedResponse<Books>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
            }

        })
    }
    override fun destroy() {
        view = null
    }

}