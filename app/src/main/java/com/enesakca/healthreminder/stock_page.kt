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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.enesakca.healthreminder.database.Medicine
import com.enesakca.healthreminder.database.MedicineViewModel


@Composable
fun stock_page(navController : NavController, viewModel : MedicineViewModel = viewModel()) {



    val medicines: List<Medicine> by viewModel.medicines.observeAsState(initial = emptyList())
    if (medicines.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Kayıtlı ilaç bulunamadı", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(
                items = medicines,
                key = { medicine -> medicine.medicineID }
            ) { medicine ->
                StockItem(medicine = medicine)
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize()) {



        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter).fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column() {

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
            Column() {

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
            Column() {

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
            Column() {

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

@Composable
fun StockItem(medicine: Medicine) {


    val isLowStock = medicine.stock <= medicine.alertStock

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isLowStock) Color.Red
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${medicine.stock} adet",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isLowStock) Color.Red
                    else MaterialTheme.colorScheme.onSurface
                )

                if (isLowStock) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Düşük Stok",
                        tint = Color.Red,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
