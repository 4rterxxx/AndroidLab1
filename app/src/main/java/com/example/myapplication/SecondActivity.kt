package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras!!.getString("Greeting")!!
        val onClick = { fullMessageText: String ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("FullUserMessage", fullMessageText)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        setContent {
            MyApplicationTheme {
                TextArea(
                    modifier = Modifier.fillMaxSize(),
                    greeting = data,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun TextArea(
    modifier: Modifier = Modifier,
    greeting: String,
    onClick: (String) -> Unit
) {
    val mainText = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(Modifier.weight(1f))
        TextField(

            value = mainText.value,
            onValueChange = { newText ->
                mainText.value = newText
            }

        )
        Button(
            onClick = {
                if (mainText.value.isNotEmpty()) {
                    onClick(greeting + mainText.value)
                }
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Image(
                painter = painterResource(R.drawable.minion),
                contentDescription = "Best Minion picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(70.dp)
            )
        }
        Spacer(Modifier.weight(2f))
    }
}
