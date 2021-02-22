package com.example.adressbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adressbook.R
import com.example.adressbook.model.db.BookAddressEntity
import com.example.adressbook.model.repository.BookAddressRepositoryImpl
import com.example.adressbook.viewmodel.BookAddressViewModel
import com.example.adressbook.viewmodel.BookAddressViewModelFactory
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity() {

    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        id = intent.getIntExtra(ITEM_KEY, 0)

        val editBookViewModel = ViewModelProvider(
            this,
            BookAddressViewModelFactory(BookAddressRepositoryImpl(application))
        ).get(BookAddressViewModel::class.java)

        editBookViewModel.getBookAddress(id)

        editBookViewModel.editBookAddressSuccess.observe(this, Observer { bookAddress ->
            editAddressName.setText(bookAddress.name)
            editAddressLastName.setText(bookAddress.lastName)
            editEmailAddress.setText(bookAddress.email)
            editPhoneNumber.setText(bookAddress.phoneNumber.toString())
            editAddressLocation.setText(bookAddress.address)
        })

        editBookViewModel.bookAddressListError.observe(this, Observer { error ->
            Toast.makeText(this@EditActivity, "Error updating, try again", Toast.LENGTH_SHORT)
                .show()
        })

        btnEdit.setOnClickListener(View.OnClickListener {

            var book = BookAddressEntity(
                id = id,
                name = editAddressName.text.toString(),
                lastName = editAddressLastName.text.toString(),
                email = editEmailAddress.text.toString(),
                phoneNumber = editPhoneNumber.text.toString().toInt(),
                address = editAddressLocation.text.toString()
            )
            editBookViewModel.updateBook(book)
            editBookViewModel.updateBookSuccessObservable.observe(this, Observer { result ->
                if (result == true) {
                    val intent = Intent(this@EditActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Book address updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Updated not successful", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}