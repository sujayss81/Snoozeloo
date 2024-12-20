package org.sujay.snoozeloo.core.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.theme.grey
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_alarm
import snoozeloo.composeapp.generated.resources.save_button_text

@Composable
fun SaveButton(modifier: Modifier = Modifier, isSaveButtonEnabled: Boolean = true, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        enabled = isSaveButtonEnabled,
        onClick = {
            onClick()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = grey,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W600,
            text = stringResource(Res.string.save_button_text)
        )
    }
}

@Composable
fun LargeAlarmIcon(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(62.dp),
        imageVector = vectorResource(Res.drawable.ic_alarm),
        contentDescription = null
    )
}