package com.svanellewee.firstcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.svanellewee.firstcompose.ui.theme.FIrstComposeTheme
import  androidx.compose.foundation.lazy.items // THIS GOTCHA DOESNT ALWAYS IMPORT ON ITS OWN.
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FIrstComposeTheme {
                // A surface container using the 'background' color from the theme
               CreateBizCard()
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape (CornerSize(18.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(), // should be height(300.dp)
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider()
                CreateDeets()
                Button(
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                        Log.d("Clicked", "CreatedBizzz ${buttonClickedState.value}") },
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(
                        text="Portfolio",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box{}
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            PortFolio(data = listOf<String>("Project1", "Project2", "Project3","Project1", "Project2", "Project3","Project1", "Project2", "Project3"))
        }
    }
}

@Composable
fun PortFolio(data: List<String>) {
    LazyColumn {
        items(items=data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)) {
                Row (modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                ){
                    CreateImageProfile(Modifier.size(100.dp))
                    Column(modifier= Modifier.padding(7.dp).align(Alignment.CenterVertically),
                    ) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = item)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateDeets(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(1.dp),
    ) {
        Text(
            text = "Emperor Palps",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Yur daddy, Darkside OG",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = "@emporerpalps",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        shadowElevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.palps),
            contentDescription = "Profile image example",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultV2iew() {
    FIrstComposeTheme {

        CreateBizCard()

    }
}