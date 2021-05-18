package yayang.setiyawan.finalproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import yayang.setiyawan.finalproject.MainActivity
import yayang.setiyawan.finalproject.R
import yayang.setiyawan.finalproject.contract.BooksActivityContract
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.presenter.DeleteActivityPresenter
import yayang.setiyawan.finalproject.utilities.Constants

class DetailActivity : AppCompatActivity(),BooksActivityContract.ViewDelete {
    private var presenter = DeleteActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DeleteActivityPresenter(this)
        Book()
        Delete()
    }
    private fun Book(){
        val books = getBooks()
        val title = findViewById<TextView>(R.id.tv_title)
        val author = findViewById<TextView>(R.id.tv_author)
        val description = findViewById<TextView>(R.id.tv_description)
        books?.let {
            title.text = it.title
            author.text = it.author
            description.text = it.description
        }
    }
    private fun Delete(){
        btnDelete.setOnClickListener {
            val data = getBooks()
            val id = data?.id_books.toString().toInt()
            val token = Constants.getToken(this)
            token?.let { it1 -> presenter.delete(id,it1) }
        }
    }
    private fun getBooks()=intent.getParcelableExtra<Books>("books")
    override fun success(message: String?) {
        Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, BookActivity::class.java))
        finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    override fun isLoading(state: Boolean) {
        btnDelete.isEnabled = !state
    }
}