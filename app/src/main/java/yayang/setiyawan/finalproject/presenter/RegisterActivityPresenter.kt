package yayang.setiyawan.finalproject.presenter

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.finalproject.contract.RegisterActivityContract
import yayang.setiyawan.finalproject.model.User
import yayang.setiyawan.finalproject.response.WrappedResponse
import yayang.setiyawan.finalproject.utilities.APIClient
import yayang.setiyawan.finalproject.utilities.Constants

class RegisterActivityPresenter (v:RegisterActivityContract.RegisterView):RegisterActivityContract.RegisterPresenter{
    private var view : RegisterActivityContract.RegisterView?= v
    private var apiService = APIClient.APIService()
    override fun register(username: String, email: String, password: String, context: Context) {
        val request = apiService.register(username, email, password)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedResponse<User>>{
            override fun onResponse(call: Call<WrappedResponse<User>>, response: Response<WrappedResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body!=null && body.status=="200"){
                        Constants.setToken(context, body.data.token!!)
                        view?.showToast("Register Succes")
                        view?.hideLoading()
                    }else{
                        view?.showToast("Register Failed, email might be allready used")
                    }
                    view?.showLoading()
                }else{
                    view?.showToast("Something Wrong, Try again later")
                    view?.hideLoading()
                }
            }
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.showToast("Cant connect to server")
                println(t.message)
                view?.hideLoading()
            }
        })
    }
    override fun destroy() {
        view = null
    }

}