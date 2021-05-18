package yayang.setiyawan.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import yayang.setiyawan.finalproject.ui.BookActivity
import yayang.setiyawan.finalproject.ui.CreateActivity
import yayang.setiyawan.finalproject.ui.LoginActivity
import yayang.setiyawan.finalproject.utilities.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logout()
        Show()
        Create()
    }
    private fun Logout(){
        logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)).also {
                Constants.clearToken(this)
                finish()
            }
        }
    }
    private fun Show(){
        btn_book.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }
    }
    private fun Create(){
        create.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))
        }
    }
}