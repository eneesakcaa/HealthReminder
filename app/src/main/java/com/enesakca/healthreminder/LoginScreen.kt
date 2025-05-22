package com.enesakca.healthreminder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.enesakca.healthreminder.database.MedicineViewModel
import com.enesakca.healthreminder.database.User
import com.enesakca.healthreminder.ui.theme.HealthReminderTheme


@Composable
fun login(navController: NavController,
          viewModel: MedicineViewModel = viewModel()

){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember {mutableStateOf("")}
    var rememberMe by remember { mutableStateOf(viewModel.rememberMeChecked) }

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.padding(15.dp).align(Alignment.Center)
        ) {
            OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = firstName,
                onValueChange = {firstName = it},
                label = {Text("Adınızı Giriniz")}
            )
            OutlinedTextField(
                modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = lastName,
                onValueChange = {lastName = it},
                label = {Text("Soyadınızı Giriniz")}
            )
            Row(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = rememberMe,
                    onCheckedChange = { rememberMe = it })
                Text(text = "Beni Hatırla")
            }


            Button(modifier = Modifier.align(Alignment.CenterHorizontally),onClick = {
                val user = User(
                    firstName = firstName,
                    lastName = lastName
                )
                viewModel.handleLoginOrRegister(user, rememberMe)
                navController.navigate("main_page") // Ana ekrana yönlendir
            }) {
                Text("Kayıt Ol")
            }



        }
    }
}

