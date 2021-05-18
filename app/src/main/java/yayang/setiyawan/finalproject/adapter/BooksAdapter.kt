package yayang.setiyawan.finalproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.view.*
import yayang.setiyawan.finalproject.R
import yayang.setiyawan.finalproject.model.Books
import yayang.setiyawan.finalproject.ui.DetailActivity

class BooksAdapter (private var data: List<Books>,private var context: Context):RecyclerView.Adapter<BooksAdapter.MyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(context).inflate(R.layout.list,parent,false)
        )
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(data[position],context)

    override fun getItemCount()=data.size
    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(books:Books,context: Context){
            itemView.title.text = books.title
            itemView.author.text = books.author
            itemView.description.text = books.description
            itemView.setOnClickListener {
                context.startActivity(Intent(context,DetailActivity::class.java).apply {
                    putExtra("books",books)
                })
            }
        }
    }
}