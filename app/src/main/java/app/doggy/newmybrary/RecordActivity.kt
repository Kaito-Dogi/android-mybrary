package app.doggy.newmybrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    lateinit var bookId: String
    lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        supportActionBar?.hide()

        bookId = intent.getStringExtra("bookId") as String
        book = realm.where(Book::class.java).equalTo("id", bookId).findFirst() as Book

        if (book.imageId == "") {
            bookImageInRecord.setImageResource(R.drawable.book_black)
        } else {
            bookImageInRecord.load(book.imageId)
        }

        val recordList = readAll(bookId)

        val adapter =
                RecordAdapter(
                        this,
                        recordList,
                        object: RecordAdapter.OnItemClickListener {
                            override fun onItemClick(item: Record) {
                                val postIntent = Intent(baseContext, RecordPostActivity::class.java)
                                postIntent.putExtra("id", item.id)
                                startActivity(postIntent)
                            }},
                        object: RecordAdapter.OnItemLongClickListener {
                            override fun onItemLongClick(item: Record) {
                                AlertDialog
                                        .Builder(this@RecordActivity)
                                        .setMessage(R.string.delete_record_dialog_message)
                                        .setPositiveButton(getText(R.string.delete_dialog_positive_button)) { dialog, which ->
                                            Toast.makeText(baseContext, getText(R.string.delete_record_toast_text), Toast.LENGTH_SHORT).show()
                                            deleteRecord(item.id)
                                            updateCurrentPage(bookId, book)
                                        }
                                        .setNegativeButton(getText(R.string.delete_dialog_negative_button)) { dialog, which ->
                                        }
                                        .show()
                            }},
                        true
                )

        recordRecyclerView.setHasFixedSize(true)
        recordRecyclerView.layoutManager = LinearLayoutManager(this)
        recordRecyclerView.adapter = adapter

        recordPostFab.setOnClickListener {
            val postIntent = Intent(baseContext, RecordPostActivity::class.java)
            postIntent.putExtra("bookId", book.id)
            postIntent.putExtra("bookPageCount", book.pageCount)
            startActivity(postIntent)
        }

        recordAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit -> {
                    val postIntent = Intent(baseContext, BookPostActivity::class.java)
                    postIntent.putExtra("id", book.id)
                    startActivity(postIntent)
                    true
                }
                R.id.delete -> {
                    AlertDialog
                            .Builder(this@RecordActivity)
                            .setMessage(R.string.delete_book_dialog_message)
                            .setPositiveButton(getText(R.string.delete_dialog_positive_button)) { dialog, which ->
                                Toast.makeText(baseContext, getText(R.string.delete_toast_text_before).toString() + book?.title + getText(R.string.delete_toast_text_after), Toast.LENGTH_SHORT).show()
                                deleteAll(bookId, book as Book)
                                finish()
                            }
                            .setNegativeButton(getText(R.string.delete_dialog_negative_button)) { dialog, which ->
                            }
                            .show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        titleText.text = book.title
        authorText.text = book.author
        pageCountText.text = "${book.pageCount}" + getText(R.string.total_page_text)
        descriptionText.text = book.description
        updateCurrentPage(bookId, book)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun readAll(bookId: String): RealmResults<Record> {
        return realm.where(Record::class.java)
            .equalTo("bookId", bookId)
            .findAll()
            .sort("createdAt", Sort.DESCENDING)
    }

    private fun deleteRecord(id: String) {
        realm.executeTransaction {
            val record = realm.where(Record::class.java).equalTo("id", id).findFirst()
                    ?: return@executeTransaction
            record.deleteFromRealm()
        }
    }

    private fun deleteAll(bookId: String, book: Book) {
        realm.executeTransaction {
            val records = realm.where(Record::class.java).equalTo("bookId", bookId).findAll()
                    ?: return@executeTransaction
            records.deleteAllFromRealm()
            book.deleteFromRealm()
        }
    }

    private fun updateCurrentPage(bookId: String, book: Book) {
        realm.executeTransaction {
            val record = realm
                    .where(Record::class.java)
                    .equalTo("bookId", bookId)
                    .sort("createdAt", Sort.DESCENDING)
                    .findFirst()
                    ?: return@executeTransaction
            book.currentPage = record.currentPage
        }
    }

}