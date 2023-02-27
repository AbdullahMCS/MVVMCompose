package com.example.mvvmcompose.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmcompose.ui.theme.MVVMComposeTheme
import com.example.mvvmcompose.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MVVMComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val response by viewModel.currencyList.observeAsState()

                    when(response) {
                        is NetworkResult.Loading -> {
                            Toast.makeText(applicationContext, "Loading...!", Toast.LENGTH_SHORT).show()
                        }
                        is NetworkResult.Success -> {
                            LazyColumn {
                                for (currency in viewModel.currencyList.value!!.data!!) {
                                    item {
                                        CryptoCurrency(
                                            name = currency.name!!,
                                            rank = currency.rank!!,
                                            price = currency.priceUsd!!,
                                            priceChange = currency.changePercent24Hr!!
                                        )
                                    }
                                }
                            }
                        }
                        is NetworkResult.Error -> {
                            Toast.makeText(applicationContext, "ERROR!!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {  }
                    }
                }
            }
        }
    }
}

@Composable
fun CryptoCurrency(
    name: String,
    rank: String,
    price: String,
    priceChange: String
) {
    Column {
        Text(text = name)
        Text(text = rank)
        Text(text = price)
        Text(text = priceChange)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}