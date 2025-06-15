package com.enesakca.healthreminder

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    val context = LocalContext.current
    val preferences = remember { PreferencesHelper(context) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember {mutableStateOf("")}
    var rememberMe by remember { mutableStateOf(viewModel.rememberMeChecked) }

    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.loginbg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize().alpha(0.7f)
        )
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

                if(firstName.isBlank() || lastName.isBlank()){
                    Toast.makeText(context, "Lütfen adınızı ve soyadınızı girin!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val user = User(
                    firstName = firstName,
                    lastName = lastName
                )
                viewModel.loginUser(user, rememberMe)
                preferences.currentUserId = user.userID
                navController.navigate("main_page")
            }) {
                Text("Kayıt Ol")
            }



        }
    }
}

