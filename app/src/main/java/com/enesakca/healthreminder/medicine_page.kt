package com.enesakca.healthreminder

import android.R.attr.onClick
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun medicine_page(navController : NavController){
    var medicine_name by remember { mutableStateOf("") }
    var medicine_mg by remember { mutableStateOf("") }
    var selectedTime by remember{mutableStateOf("")}
    var selectedDate by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var alertStock by remember { mutableStateOf("") }




    val context = LocalContext.current
    val timePicker = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime = format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
        },
        // Varsayılan saat
        12, 0, true
    )

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val datePicker = DatePickerDialog(
        context,
        { datePicker, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            selectedDate = dateFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    Box(modifier = Modifier.fillMaxSize()
        ){
        Column() {
            Row() {
                OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                    value = medicine_name,
                    onValueChange = {medicine_name = it},
                    label = {Text("İlaç adını giriniz *")}
                )
            }
            Row {
                OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                    value = medicine_mg,
                    onValueChange = {medicine_mg = it},
                    label = {Text("İlaç mg giriniz")},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp,0.dp,20.dp,30.dp)
            ) {
                OutlinedTextField(
                    value = selectedTime,
                    onValueChange = {},
                    label = { Text("Hatırlatma Saati") },
                    modifier = Modifier.clickable { timePicker.show() },
                    enabled = false,
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Bildirim Zamanı"
                        )

                    }
                )
            }
            Row (modifier = Modifier.fillMaxWidth().padding(20.dp,0.dp,20.dp,30.dp)){
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {},
                    label = { Text("Başlangıç Tarihi") },
                    modifier = Modifier
                        .clickable { datePicker.show() },
                    enabled = false,
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Hapların İlk Gün Yönetimi"
                        )
                    }
                )
            }

            Row() {
                OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                    value = stock,
                    onValueChange = {stock = it},
                    label = {Text("Elinizde kaç tablet ilaç var")},keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }

            Row() {
                OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                    value = alertStock,
                    onValueChange = {alertStock = it},
                    label = {Text("Kaç tablet kalınca uyarı istersiniz")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)// klavyede giriş için sayı çıkacak
                )
            }



        }

        Row(modifier = Modifier
            .align (Alignment.BottomCenter).fillMaxWidth().background(color = MaterialTheme.colorScheme.primaryContainer), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom){
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
                Text(text = "İlaç Ekle",color = Color.Red)
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
                Text(text = "Hesabım")
            }

        }
    }
}