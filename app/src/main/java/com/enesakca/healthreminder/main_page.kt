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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.enesakca.healthreminder.database.Medicine
import com.enesakca.healthreminder.database.MedicineViewModel

@Composable()
fun main_page(navController: NavController, viewModel: MedicineViewModel = viewModel()){

    val medicines by viewModel.todayMedicines.collectAsState()




    Box(modifier = Modifier.fillMaxSize()){


        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize().alpha(0.7f)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            items(medicines) { item ->
                MedicineItem(
                    medicine = item.medicine,
                    isTaken = item.isTaken,

                    onCheckedChange = { checked ->
                        viewModel.updateStatus(
                            item.medicine.medicineID, checked
                        )
                    }

                )
            }
        }



        Row(modifier = Modifier
            .align(Alignment.BottomCenter) .fillMaxWidth().background(color = MaterialTheme.colorScheme.primaryContainer),horizontalArrangement = Arrangement.SpaceEvenly ){
            Column(){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.home), contentDescription = "home_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("main_page")}

                    ))
                Text(text = "Ana Sayfa",color = Color.Red)
            }
            Column(){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.medicine), contentDescription = "home_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally).size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("medicine_page")}

                    ))
                Text(text = "İlaç Ekle")
            }
            Column(){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.time_stock), contentDescription = "stock_button", modifier = Modifier.align(
                    Alignment.CenterHorizontally)
                    .size(32.dp,32.dp)
                    .clickable(onClick = {navController.navigate("stock_page")}

                    ))
                Text(text = "Stok")
            }
            Column(){

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

@Composable
fun MedicineItem(
    medicine: Medicine,
    isTaken: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        elevation = CardDefaults.cardElevation(4.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = medicine.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${medicine.medicineDosage} mg",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Checkbox(
                checked = isTaken,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}




