package org.sujay.snoozeloo.features.alarmdetail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.vectorResource
import org.sujay.snoozeloo.core.theme.inactiveColor
import org.sujay.snoozeloo.core.utils.get0AppendedTime
import org.sujay.snoozeloo.core.utils.getEpochMillisFromTime
import org.sujay.snoozeloo.core.utils.getTimeUntilAlarm
import snoozeloo.composeapp.generated.resources.Res
import snoozeloo.composeapp.generated.resources.ic_colon

@Composable
fun AlarmTimeInputBox(
    modifier: Modifier = Modifier,
    hour: Int?,
    min: Int?,
    onHourChange: (Int?) -> Unit,
    onMinChange: (Int?) -> Unit,
) {

    Column(
        modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeBox(time = hour, isInactive = (hour == 0 && min == 0)) {
                onHourChange(it)
            }
            Spacer(Modifier.width(10.dp))
            Image(vectorResource(Res.drawable.ic_colon), contentDescription = null)
            Spacer(Modifier.width(10.dp))
            TimeBox(time = min, isInactive = (hour == 0 && min == 0), isMinBox = true) {
                onMinChange(it)
            }
        }

        hour?.getEpochMillisFromTime(hour, min)?.getTimeUntilAlarm()?.let {
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally),
                text = it,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun TimeBox(
    modifier: Modifier = Modifier,
    time: Int?,
    isInactive: Boolean,
    isMinBox: Boolean = false,
    onTimeChange: (Int?) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier.clip(RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp, horizontal = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = Modifier.width(72.dp).align(Alignment.Center),
            value = TextFieldValue(
                text = time?.get0AppendedTime() ?: "",
                selection = TextRange(2)
            ) ,
            onValueChange = {
                val valueInt = it.text.toIntOrNull()
                if(!isMinBox && valueInt != null && valueInt in 0 until 24)
                    onTimeChange(valueInt)
                else if(isMinBox && valueInt != null && valueInt in 0 until 60)
                    onTimeChange(valueInt)
                else if(it.text.isEmpty())
                    onTimeChange(null)
            },
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                color = if (!isInactive) MaterialTheme.colorScheme.secondary else inactiveColor,
                fontSize = 52.sp,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = if(!isMinBox) ImeAction.Next else ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            maxLines = 1
        )
    }
}