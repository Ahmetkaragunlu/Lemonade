package com.ahmetkaragunlu.lemonade

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmetkaragunlu.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}


@Composable
fun Lemonade(modifier: Modifier=Modifier) {
    var result by remember {
        mutableStateOf(1)
    }
    var imageResource=when(result) {
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        else->R.drawable.lemon_restart
    }
    var textResource = when(result) {
        1 ->R.string.Lemon_tree
        2->R.string.Lemon
        3->R.string.Glass_of_lemonade
        else->R.string.Empty_glass
    }
    var clickCount by remember {
        mutableStateOf(0)
    }
   Column ( horizontalAlignment = Alignment.CenterHorizontally){
       Text(text = "Lemonade",
           modifier.fillMaxWidth().background(color = Color.Yellow).padding(35.dp),
           textAlign = TextAlign.Center,
           fontSize=22.sp,
           fontWeight = FontWeight.Bold
           )
   }

    Column(modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        ) {
     Image(painter = painterResource(id =imageResource), contentDescription =null,
         modifier
             .clickable {
                 if (result == 1) {
                     result++
                     clickCount = (2..4).random()
                 } else if (result == 2) {
                     clickCount--
                     if (clickCount == 0) {
                         result++
                     }
                 } else if (result == 3) {
                     result++
                 } else {
                     result = 1
                 }
             }
             .border(2.dp, Color(0xFFBAE786), shape = RoundedCornerShape(12))
             .background(Color(0xFFD0F7A3), shape = RoundedCornerShape(12))
         )
        Spacer(modifier.height(16.dp))
        Text(text = stringResource(id = textResource),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade()
    }
}