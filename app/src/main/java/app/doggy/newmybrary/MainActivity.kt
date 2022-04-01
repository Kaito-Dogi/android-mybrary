package app.doggy.newmybrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    lateinit var bookList: RealmResults<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookList = readAll()

        val adapter = BookAdapter(baseContext, bookList, object: BookAdapter.OnItemClickListener {
            override fun onItemClick(item: Book) {
                val recordIntent = Intent(baseContext, RecordActivity::class.java)
                recordIntent.putExtra("bookId", item.id)
                startActivity(recordIntent)
            }
        }, true)

        bookRecyclerView.setHasFixedSize(true)
        bookRecyclerView.layoutManager = GridLayoutManager(baseContext, 3)
        bookRecyclerView.adapter = adapter

        readFab.setOnClickListener {
            val readIntent = Intent(baseContext, ReadActivity::class.java)
            startActivity(readIntent)
        }

        bookPostFab.setOnClickListener {
            val postIntent = Intent(baseContext, BookPostActivity::class.java)
            startActivity(postIntent)
        }

    }

    override fun onResume() {
        super.onResume()
            emptyText.isVisible = bookList.isEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.DESCENDING)
    }

}