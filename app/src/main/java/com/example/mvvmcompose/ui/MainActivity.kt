package com.example.mvvmcompose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mvvmcompose.ui.theme.MVVMComposeTheme
import com.example.mvvmcompose.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MVVMComposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val myViewModel: DucksViewModel =
                        viewModel(modelClass = DucksViewModel::class.java)

                    val responseDuck = myViewModel.duck.observeAsState()
                    val responseDog = myViewModel.dog.observeAsState()
                    when (responseDog.value!!) {
                        is NetworkResult.Loading -> {
                            Toast.makeText(applicationContext, "Loading...!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is NetworkResult.Success -> {
                            GlideImage(
                                model = responseDog.value!!.data?.message,
                                contentDescription = null,
                            )
                        }
                        is NetworkResult.Error -> {
                            Toast.makeText(applicationContext, "ERROR!!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}