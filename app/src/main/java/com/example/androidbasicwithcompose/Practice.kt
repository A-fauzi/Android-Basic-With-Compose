package com.example.androidbasicwithcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbasicwithcompose.ui.theme.AndroidBasicWithComposeTheme

class Practice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimeList()
                }
            }
        }
    }
}

@Composable
fun AnimeList() {
    var statePressButton by remember { mutableStateOf(false) }

    var stateImage by remember { mutableStateOf(1) }
    val image = when (stateImage) {
        1 -> R.drawable.one_piece
        2 -> R.drawable.clay_more
        3 -> R.drawable.kabaneri
        4 -> R.drawable.my_hero_academy
        else -> R.drawable.blue_period
    }

    var stateTitle by remember { mutableStateOf(1) }
    val title = when (stateTitle) {
        1 -> R.string.title_one_piece
        2 -> R.string.title_clay_more
        3 -> R.string.title_kabaneri
        4 -> R.string.title_my_hero_academy
        else -> R.string.title_blue_period
    }

    var stateDesc by remember { mutableStateOf(1) }
    val description = when (stateDesc) {
        1 -> R.string.desc_one_piece
        2 -> R.string.desc_clay_more
        3 -> R.string.desc_kabaneri
        4 -> R.string.desc_my_hero_acdemy
        else -> R.string.desc_blue_period
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimeImage(image)
        Spacer(modifier = Modifier.height(16.dp))
        AnimeDescript(title, description)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonNextPrev(
            {
                statePressButton = false
                Log.d("main", "$statePressButton")

                if (statePressButton == false) {
                    stateImage--
                    stateTitle--
                    stateDesc--
                }
                if (stateImage == 0 && stateTitle == 0 && stateDesc == 0) {
                    stateImage = 5
                    stateTitle = 5
                    stateDesc = 5
                }
            },
            {
                statePressButton = true
                Log.d("main", "$statePressButton")
                if (statePressButton == true) {
                    stateImage++
                    stateTitle++
                    stateDesc++
                }
                if (stateImage > 5 && stateTitle > 5 && stateDesc > 5) {
                    stateImage = 1
                    stateTitle = 1
                    stateDesc = 1
                }
            }
        )
    }
}

@Composable
fun AnimeImage(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "one piece",
        modifier = Modifier
            .size(250.dp)
    )
}

@Composable
fun AnimeDescript(title: Int, description: Int) {

    Card(
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(title))
            Text(
                text = stringResource(description),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ButtonNextPrev(
    statePrev: () -> Unit,
    stateNext: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                statePrev()
            },
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = {
                stateNext()
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
        ) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AndroidBasicWithComposeTheme {
        AnimeList()
    }
}