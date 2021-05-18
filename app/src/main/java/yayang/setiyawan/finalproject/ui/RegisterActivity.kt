package yayang.setiyawan.finalproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import yayang.setiyawan.finalproject.R
import yayang.setiyawan.finalproject.contract.RegisterActivityContract
import yayang.setiyawan.finalproject.databinding.ActivityRegisterBinding
import yayang.setiyawan.finalproject.presenter.RegisterActivityPresenter

class RegisterActivity : AppCompatActivity(),RegisterActivityContract.RegisterView {
    private var presenter:RegisterActivityPresenter? = null
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = RegisterActivityPresenter(this)
        doRegister()
        Login()
    }

    private fun doRegister(){
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty()&&password.isNotEmpty()){
                if (password.length>6){
                    if (username.length > 5){
                        presenter?.register(username,email,password,this)
                    }else{
                        showToast("Name must be 5 characters or more")
                    }
                }else{
                    showToast("Password must be 8 characters or more")
                }
            }else{
                showToast("Please fill all forms first")
            }
        }
    }
    private fun Login(){
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun successRegister() {
        startActivity(Intent(this,LoginActivity::class.java)).apply {
            finish()
        }
    }
    override fun showLoading() {
        binding.btnRegister.isEnabled = false
        binding.loading.apply {
            isIndeterminate = true
        }
    }

    override fun hideLoading() {
        binding.btnRegister.isEnabled = true
        binding.loading.apply{
            isIndeterminate = false
            progress = 0
        }
    }
}