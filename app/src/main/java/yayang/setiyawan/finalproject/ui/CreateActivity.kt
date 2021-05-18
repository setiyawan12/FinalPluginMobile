package yayang.setiyawan.finalproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create.*
import yayang.setiyawan.finalproject.R
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.presenter.CreateActiviryPresenter
import yayang.setiyawan.finalproject.utilities.Constants

class CreateActivity : AppCompatActivity(),BooksActivityContract.ViewCreate {
    private var presenter = CreateActiviryPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        presenter = CreateActiviryPresenter(this)
        Save()
    }

    private fun Save(){
        btnSave.setOnClickListener {
            val token = Constants.getToken(this)
            val title = etTitle.text.toString().trim()
            val author = etAuthor.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (title.isNotEmpty()&&author.isNotEmpty()&&description.isNotEmpty()){
                token?.let { it -> presenter.save(it,title,author, description) }
            }else{
                toast("Isi Semua form")
            }
        }
    }

    override fun success(message: String?) {
        Toast.makeText(applicationContext, "Data Ditambahkan", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, BookActivity::class.java))
        finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnSave.isEnabled = !state
    }
}