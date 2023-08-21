package com.example.pruebalab42
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          MainScreen()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen (){
    val itemList = remember {mutableStateListOf<Receta>()}
    val name = remember { mutableStateOf(TextFieldValue()) }
    val Url = remember { mutableStateOf(TextFieldValue()) }

    Column {

        TextField(value = name.value, onValueChange = {name.value = it}, label = {Text("Nombre de la receta")}, modifier = Modifier.fillMaxWidth() .background(Color.White))
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = Url.value, onValueChange = {Url.value = it}, label = {Text("URL de la imagen")}, modifier = Modifier.fillMaxWidth() .background(Color.White),)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {val receta = Receta(name.value.text,Url.value.text)
            itemList.add(receta)
            name.value = TextFieldValue()
            Url.value = TextFieldValue()} ) {
            Text(text = "Agregar")

        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn{
            items(itemList){
                receta -> ImagenReceta(receta)
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            for(receta in itemList){
                Text(text = "Receta = ${receta.name}, Imagen: ${receta.imageUrl}")
            }
        }
    }


}
data class Receta(val name : String, val imageUrl: String )
@Composable
fun ImagenReceta(receta: Receta){
    val context = LocalContext.current
    val densidad = LocalDensity.current.density
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
    { Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image (
            painter = rememberImagePainter(data = receta.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(4.dp),
                contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = receta.name, style = MaterialTheme.typography.bodyMedium, color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)

        )

    }


    }


@Composable
fun GreetingPreview() {
    MainScreen()

}}
