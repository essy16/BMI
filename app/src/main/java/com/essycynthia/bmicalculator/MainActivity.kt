package com.essycynthia.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.essycynthia.bmicalculator.ui.theme.BMICALCULATORTheme
import com.essycynthia.bmicalculator.ui.theme.aqua
import com.essycynthia.bmicalculator.ui.theme.fuchsia
import com.essycynthia.bmicalculator.ui.theme.violet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICALCULATORTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        bmiCalc(
                            navController = navController
                        )
                    }
                    composable("web") {
                        WebViewScreen(
                            navController = navController,
                            url = "https://www.health.harvard.edu/blog/how-useful-is-the-body-mass-index-bmi-201603309339"
                        )
                    }
                }

            }
        }
    }

    @Composable
    private fun bmiCalc(
        navController: NavController
    ) {

        val ageState = remember { mutableStateOf("") }
        val heightState = remember { mutableStateOf("") }
        val weightState = remember { mutableStateOf("") }
        var isMaleSelected by remember { mutableStateOf(false) }
        var isFemaleSelected by remember { mutableStateOf(false) }
        val mContext = LocalContext.current
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(color = violet)
                    .padding(16.dp)
            ) {
                Column(
                    Modifier.background(color = violet),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Interesting for you",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontSize = 30.sp

                    )
                    Text(
                        text = "Yoga benefits and risk",
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.End)
                            .width(100.dp)
                            .height(100.dp)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.yogagirl),
                            contentDescription = "yogaGirl",

                            )
                    }
                    Text(
                        modifier = Modifier.clickable {
                            navController.navigate("web")
                        },
                        text = "Tap to learn more",
                        color = Color.Black,
                        textAlign = TextAlign.Start,

                        )


                }
            }
            Text(
                text = "BMI Calculator",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                genderButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    painterResourceId = R.drawable.ic_male_24,
                    symbol = "MALE",
                    onClick = {
                        isMaleSelected = true
                        isFemaleSelected = false
                    },
                    isSelected = isMaleSelected,

                    defaultColor = violet,
                    selectedColor = aqua
                )
                Spacer(modifier = Modifier.width(12.dp))
                genderButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    painterResourceId = R.drawable.ic_female_24,
                    symbol = "FEMALE",
                    onClick = {
                        isMaleSelected = false
                        isFemaleSelected = true

                    },
                    isSelected = isFemaleSelected,
                    defaultColor = violet,
                    selectedColor = fuchsia
                )

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "AGE",
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 15.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = ageState.value,
                    onValueChange = { newValue ->
                        ageState.value = newValue
                    },
                    label = { Text("Enter your age") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.body1
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "HEIGHT",
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 15.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = heightState.value,
                    onValueChange = { newValue ->
                        heightState.value = newValue
                    },
                    label = { Text("Enter your height in centimetres") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.body1
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "WEIGHT",
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 15.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = weightState.value,
                    onValueChange = { newValue ->
                        weightState.value = newValue
                    },
                    label = { Text("Enter your weight in kilograms") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.body1
                )
            }
            Button(
                onClick = {
                    val height = heightState.value.toFloatOrNull()
                    val weight = weightState.value.toFloatOrNull()
                    val age = ageState.value
                    if (weight != null && height != null) {
                        val result = calculateBMI(weight, height)
                        resultToast(mContext, result)
                    } else if (weight == null) {
                        noWeightToast(mContext)

                    } else if (height == null) {
                        noHeightToast(mContext)

                    } else if (age == null) {
                        noAgeToast(mContext)

                    }
                },
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(text = "CALCULATE")
            }


        }

    }

    private fun resultToast(context: Context, result: Float) {
        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()

    }

    private fun noAgeToast(context: Context) {
        Toast.makeText(context, "Please insert your age!", Toast.LENGTH_SHORT).show()
    }

    private fun noHeightToast(context: Context) {
        Toast.makeText(context, "Please insert your height!", Toast.LENGTH_SHORT).show()
    }

    private fun noWeightToast(context: Context) {
        Toast.makeText(context, "Please insert your weight!", Toast.LENGTH_SHORT).show()
    }

    private fun calculateBMI(weightState: Float, heightState: Float): Float {
        val heightInMetres = heightState / 100
        return weightState / (heightInMetres * heightInMetres)
    }
}

@Composable
fun WebViewScreen(navController: NavController, url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    },
        update = {
            it.loadUrl(url)
        })

}

