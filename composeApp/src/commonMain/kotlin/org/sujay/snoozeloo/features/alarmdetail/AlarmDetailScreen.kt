package org.sujay.snoozeloo.features.alarmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_close
import snoozeloo.composeapp.generated.resources.save_button_text

@Composable
fun AlarmDetailScreen() {
    Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Image(imageVector = vectorResource(Res.drawable.ic_close), contentDescription = null)
            Button(onClick = {
                // TOOD handle save function
            }) {
                Text(
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W600,
                    text = stringResource(Res.string.save_button_text)
                )
            }
        }
    }) {

    }
}