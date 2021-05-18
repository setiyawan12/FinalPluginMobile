package yayang.setiyawan.finalproject.contract

import yayang.setiyawan.finalproject.model.Books

interface BooksActivityContract {
    interface View{
        fun attachToRecycle(books: List<Books>)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
        fun notConnect(message: String?)
    }
    interface Interaction{
        fun allUser(token: String)
        fun destroy()
    }
    interface ViewCreate{
        fun success(message: String?)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
    }
    interface ViewDelete{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }
    interface InteractionPost{
        fun validate(title: String, author: String, description: String) : Boolean
        fun save(token:String, title: String, author: String, description: String)
        fun destroy()
    }
    interface InteractionDelete{
        fun delete(id: Int, token: String)
        fun destroy()
    }
}