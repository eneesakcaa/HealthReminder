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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun account_detail_page(navController : NavController){

    var new_name_text by remember { mutableStateOf("") }
    var new_surname_text by remember {mutableStateOf("")}
    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.padding(15.dp).align(Alignment.Center)) {
            OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = new_name_text,
                onValueChange = {new_name_text = it},
                label = {Text("Adınızı Yeniden Giriniz")}
            )
            OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = new_surname_text,
                onValueChange = {new_surname_text = it},
                label = {Text("Soyadınızı Yeniden Giriniz")}
            )
            Row(modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally)) {
                Button(onClick = {navController.navigate("")},modifier = Modifier.padding(15.dp)) {
                    Text("Düzenle")
                }
                Button(onClick = {navController.navigate("")},modifier = Modifier.padding(15.dp)) {
                    Text("Çıkış Yap")
                }
            }
        }




        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth().background(color = MaterialTheme.colorScheme.primaryContainer), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom){
            Column(modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.home), contentDescription = "home_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("main_page")}

                    ))
                Text(text = "Ana Sayfa")
            }
            Column(modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.medicine) ,contentDescription = "medicine_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("medicine_page")}

                    ))
                Text(text = "İlaç Ekle")
            }
            Column(modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.time_stock), contentDescription = "stock_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("stock_page")}

                    ))
                Text(text = "Stok")
            }
            Column(modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.account_profile), contentDescription = "account_button_image", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("account_detail_page")}

                    ))
                Text(text = "Hesabım",color = Color.Red)
            }

        }
    }
}