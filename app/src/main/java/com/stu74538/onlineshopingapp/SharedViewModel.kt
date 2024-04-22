package com.stu74538.onlineshopingapp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SharedViewModel: ViewModel() {
    val user = User()
    var product by mutableStateOf<Product?>(null)
        private set

    internal val cart = mutableListOf<CartItem>()



    fun productDetail(selectedProduct: Product) {
        product = selectedProduct
    }

    fun addProduct(selectedProduct: Product, numberOfProduct: Int) {
        val isIn = cart.find {
            it.productId == product?.id
        }
        if (isIn == null) {
            cart.add(
                CartItem(
                    product?.id,
                    product?.img,
                    product?.name,
                    product?.price,
                    numberOfProduct
                )
            )
        } else {
            isIn.numberOfItem += 1
        }
    }
}
