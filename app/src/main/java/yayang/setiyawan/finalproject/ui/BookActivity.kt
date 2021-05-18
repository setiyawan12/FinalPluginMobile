package yayang.setiyawan.finalproject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_book.*
import yayang.setiyawan.finalproject.R
import yayang.setiyawan.finalproject.adapter.BooksAdapter
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.presenter.BooksActivityPresenter
import yayang.setiyawan.finalproject.utilities.Constants

class BookActivity : AppCompatActivity(),BooksActivityContract.View {
    private var presenter = BooksActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivity(Intent(this,CreateActivity::class.java))
        }
        presenter = BooksActivityPresenter(this)
        checkIsLoggedIn()
    }
    private fun getData(){
        Constants.getToken(this)?.let { presenter.allUser(it) }
    }

    override fun attachToRecycle(books: List<Books>) {
        rvBooks.apply {
            layoutManager = LinearLayoutManager(this@BookActivity)
            adapter = BooksAdapter(books,this@BookActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun notConnect(message: String?) {
        TODO("Not yet implemented")
    }
    private fun checkIsLoggedIn(){
        val token = Constants.getToken(this)
        if (token.equals("UNDEFINED")){
            startActivity(Intent(this,LoginActivity::class.java)).also { finish() }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
}