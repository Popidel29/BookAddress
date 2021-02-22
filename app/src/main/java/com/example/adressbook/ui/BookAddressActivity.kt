package com.example.adressbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adressbook.R
import com.example.adressbook.model.repository.BookAddressRepositoryImpl
import com.example.adressbook.viewmodel.BookAddressViewModel
import com.example.adressbook.viewmodel.BookAddressViewModelFactory
import kotlinx.android.synthetic.main.activity_book_address.*

const val ITEM_KEY = "item_key"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_address)

        val bookAddressListViewModel = ViewModelProvider(
            this,
            BookAddressViewModelFactory(BookAddressRepositoryImpl(application))
        ).get(BookAddressViewModel::class.java)

        bookAddressListViewModel.getListBookAddress()

        bookAddressListViewModel.bookAddressListSuccess.observe(this, Observer { bookList ->

            if(bookList.isNotEmpty())
            {
                noContactsImage.visibility = View.GONE
            }

            progressBar.visibility = View.GONE
            rv_notes.adapter = BookListAdapter(bookList, { id: Int -> itemClicked(id) })
            rv_notes.layoutManager = LinearLayoutManager(parent)

        })


        bookAddressListViewModel.bookAddressListError.observe(this, Observer { bookList ->
            progressBar.visibility = View.GONE
            linearError.visibility = View.VISIBLE
        })

        fab.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                AddBookActivity::class.java
            )
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(input: String): Boolean {
                bookAddressListViewModel.searchBookAddress(input)

                bookAddressListViewModel.bookAddressListSuccess.observe(
                    this@MainActivity,
                    Observer { searchList ->
                        progressBar.visibility = View.GONE
                        rv_notes.adapter =
                            BookListAdapter(searchList, { id: Int -> itemClicked(id) })
                        rv_notes.layoutManager = LinearLayoutManager(parent)
                    })

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

    }

    private fun itemClicked(id: Int) {
        val intent = Intent(this@MainActivity, EditActivity::class.java)
        intent.putExtra(ITEM_KEY, id)
        startActivity(intent)
    }
}