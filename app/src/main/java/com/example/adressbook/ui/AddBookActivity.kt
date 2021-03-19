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
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.note_list_activity.*

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val addBookViewModel = ViewModelProvider(
            this,
            BookAddressViewModelFactory(BookAddressRepositoryImpl(application))
        ).get(BookAddressViewModel::class.java)

        btnAdd.setOnClickListener(View.OnClickListener {
            val name = addAddressName.text.toString()
            val lastName = addAddressLastName.text.toString()
            val email = addEmailAddress.text.toString()
            val phoneNumber = addPhoneNumber.text.toString()
            val address = addAddressLocation.text.toString()

            if (name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && phoneNumber.isNotBlank() && address.isNotBlank()) {
                val noteBookAddress = BookAddressEntity(
                    name = name,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber.toInt(),
                    address = address
                )

                addBookViewModel.addBookAddress(noteBookAddress)

                addBookViewModel.addBookAddressSuccessObservable.observe(this, Observer { result ->
                    if (result == true) {
                        Toast.makeText(this@AddBookActivity, "Contact successful added", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@AddBookActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@AddBookActivity,
                            "No contact added, try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(this@AddBookActivity, "Fields must be empty", Toast.LENGTH_SHORT).show()
            }
        })
    }
}






