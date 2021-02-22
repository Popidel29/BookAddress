package com.example.adressbook

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.adressbook.model.db.BookAddressEntity
import com.example.adressbook.model.repository.BookAddressRepository
import com.example.adressbook.viewmodel.BookAddressViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EditViewModelTest {
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: BookAddressRepository

    private lateinit var bookAddressViewModel: BookAddressViewModel

    private lateinit var bookAddress: BookAddressEntity

    @Before
    fun setup() {
        bookAddressViewModel = BookAddressViewModel(repository)
    }

    @Test
    fun `GetBookAddress single note successful`() {
        val id: Int = 1
        val phoneNumber: Int
        bookAddress =
            BookAddressEntity(id, "Idelson", "Fernandes", "idelsons@gmail.com", 973373468, "london")
        `when`(repository.getBookAddress(id)).thenReturn(Observable.just(bookAddress))
        //when
        bookAddressViewModel.getBookAddress(id)

        //Then
        Assert.assertEquals(bookAddress,bookAddressViewModel.editBookAddressSuccess.value)
    }

}