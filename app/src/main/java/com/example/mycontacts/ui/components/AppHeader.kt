package com.example.mycontacts.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycontacts.ui.theme.ContactAccent

@Composable
fun AppHeader(
    title: String,
    onBackClick: (() -> Unit)? = null
) {
    Surface(
        color = ContactAccent
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {

            if (onBackClick != null) {
                Text(
                    text = "←",
                    color = Color.White,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            onBackClick()
                        }
                        .padding(horizontal = 18.dp)
                )
            }

            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}