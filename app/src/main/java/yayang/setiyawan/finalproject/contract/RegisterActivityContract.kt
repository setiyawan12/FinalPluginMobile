package yayang.setiyawan.finalproject.contract

import android.content.Context

interface RegisterActivityContract {
    interface RegisterView{
        fun showToast(message : String)
        fun successRegister()
        fun showLoading()
        fun hideLoading()
    }
    interface RegisterPresenter{
        fun register(username:String,email : String, password: String, context: Context)
        fun destroy()
    }
}
