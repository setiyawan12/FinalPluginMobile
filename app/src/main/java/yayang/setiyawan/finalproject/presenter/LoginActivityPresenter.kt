package yayang.setiyawan.finalproject.presenter

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.finalproject.contract.LoginActivityContract
import yayang.setiyawan.finalproject.model.User
import yayang.setiyawan.finalproject.response.WrappedResponse
import yayang.setiyawan.finalproject.utilities.APIClient
import yayang.setiyawan.finalproject.utilities.Constants

class LoginActivityPresenter (v: LoginActivityContract.LoginView):LoginActivityContract.LoginPresenter{
    private var view : LoginActivityContract.LoginView? = v
    private var apiService = APIClient.APIService()
    override fun login(email: String, password: String, context: Context) {
        val request = apiService.login(email, password)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedResponse<User>>{
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status == "200"){
                        Constants.setToken(context, body.data.token!!)
                        view?.showToast("welcome ${body.data.token}")
                        view?.successLogin()
                    }else{
                        view?.hideLoading()
                    }
                }else{
                    view?.hideLoading()
                    view?.showToast("Something went Wrong Try again later")
                }
            }
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.showToast("Gagal Connect Ke Server")
                println(t.message)
                view?.hideLoading()
            }

        })
    }
    override fun destroy() {
        view=null
    }
}