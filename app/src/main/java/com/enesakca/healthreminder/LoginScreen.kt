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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun login(navController: NavController){
    var name by remember { mutableStateOf("") }
    var surname by remember {mutableStateOf("")}
    var rememberMe by remember { mutableStateOf(false  ) }

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.padding(15.dp).align(Alignment.Center)
        ) {
            OutlinedTextField(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = name,
                onValueChange = {name = it},
                label = {Text("Adınızı Giriniz")}
            )
            OutlinedTextField(
                modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp),
                value = surname,
                onValueChange = {surname = it},
                label = {Text("Soyadınızı Giriniz")}
            )
            Row(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp)) {
                Checkbox(checked = rememberMe,
                    onCheckedChange = { rememberMe = it })
                Text(text = "Beni Hatırla")
            }


            Button(modifier = Modifier.padding(20.dp,0.dp,20.dp,30.dp).align(Alignment.CenterHorizontally) ,
                onClick = {navController.navigate("main_page")}) {
                Text("Kayıt Ol")
            }



        }
    }
}