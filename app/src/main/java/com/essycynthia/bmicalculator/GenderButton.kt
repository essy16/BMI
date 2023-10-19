package com.essycynthia.bmicalculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.bmicalculator.ui.theme.violet

@Composable
fun genderButton(
    modifier: Modifier,
    painterResourceId: Int,
    defaultColor: Color,
    selectedColor: Color,
    symbol: String,
    onClick: () -> Unit,
    isSelected: Boolean

    ) {
    val backgroundColor = if (isSelected) selectedColor else defaultColor

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .clickable { onClick() }
            .background(color = backgroundColor)
            .then(modifier)) {
        Column() {


            Image(
                painter = painterResource(id = painterResourceId),
                contentDescription = "gender",
                modifier = Modifier.size(60.dp)

            )
            Text(
                text = symbol,
                fontSize = 30.sp,
                color = MaterialTheme.colors.primary

            )
        }


    }
}