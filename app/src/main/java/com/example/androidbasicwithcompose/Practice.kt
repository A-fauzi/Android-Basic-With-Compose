package com.example.androidbasicwithcompose

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Drinking()
                }
            }
        }
    }
}

enum class BounceState { PRESSED, RELEASED }


@Composable
fun Drinking() {
    var currentState: BounceState by remember { mutableStateOf(BounceState.RELEASED) }
    val transition = updateTransition(targetState = currentState, label = "animation")
    val scale: Float by transition
        .animateFloat(
            transitionSpec = { spring(stiffness = 900f) },
            label = ""
        ) { state ->
            if (state == BounceState.PRESSED) 0.80f
            else 1f
        }

    val context = LocalContext.current

    var imageResource = 0
    var description = 0
    var descriptionSqueeze = ""

    var stateProcess by remember { mutableStateOf(1) }
    var squeezeLemonState by remember { mutableStateOf(0) }

    when (stateProcess) {
        1 -> {
            imageResource = R.drawable.lemon_tree
            description = R.string.lemon_tree
        }
        2 -> {
            imageResource = R.drawable.lemon_squeeze
            description = R.string.lemon_squeeze
            descriptionSqueeze = "squeeze $squeezeLemonState of 5"
        }
        3 -> {
            imageResource = R.drawable.lemon_drink
            description = R.string.lemon_drink
        }
        else -> {
            imageResource = R.drawable.lemon_restart
            description = R.string.lemon_restart
        }
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(description),
            fontSize = 18.sp
        )
        Text(
            text = descriptionSqueeze,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    currentState = BounceState.PRESSED
                    tryAwaitRelease()
                    currentState = BounceState.RELEASED

                    stateProcess++
                    if (stateProcess == 3) {
                        stateProcess = 2
                        squeezeLemonState++
                        if (squeezeLemonState == 6) {
                            squeezeLemonState = 0
                            stateProcess++
                        }

                    }
                    if (stateProcess > 4) stateProcess = 1

                })
            }
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(description),
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AndroidBasicWithComposeTheme {
        Drinking()
    }
}