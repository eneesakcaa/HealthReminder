package com.enesakca.healthreminder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun stock_page(navController : NavController) {
    Box(modifier = Modifier.fillMaxSize()) {


        Row(
            modifier = Modifier.padding(2.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier.padding(5.dp), text = "stock page")
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter).fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.home),
                    contentDescription = "home_button",
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                        .size(32.dp, 32.dp)
                        .clickable(onClick = { navController.navigate("main_page") }

                        ))
                Text(text = "Ana Sayfa")
            }
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.medicine),
                    contentDescription = "medicine_button",
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                        .size(32.dp, 32.dp)
                        .clickable(onClick = { navController.navigate("medicine_page") }

                        ))
                Text(text = "İlaç ekle")
            }
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.time_stock),
                    contentDescription = "stock_button",
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                        .size(32.dp, 32.dp)
                        .clickable(onClick = { navController.navigate("stock_page") }

                        ))
                Text(text = "Stok", color = Color.Red)
            }
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.account_profile),
                    contentDescription = "account_button_image",
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                        .size(32.dp, 32.dp)
                        .clickable(onClick = { navController.navigate("account_detail_page") }

                        ))
                Text(text = "Hesabım")
            }

        }

    }

}