@file:OptIn(ExperimentalMaterial3Api::class)

package chkan.example.perfectday.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chkan.example.perfectday.ui.theme.PerfectDayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    createTask: (title: String) -> Unit,
    onDismiss: () -> Unit
) {

    if (isBottomSheetVisible) {
        var text by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        val coroutineScope = rememberCoroutineScope()

        ModalBottomSheet(
            onDismissRequest = {
                onDismiss.invoke()
                focusRequester.freeFocus() },
            sheetState = sheetState,
            dragHandle = null
        ) {

            LaunchedEffect(sheetState.currentValue) {
                if (sheetState.currentValue == SheetValue.Expanded) {
                    focusRequester.requestFocus()
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = text,
                        textStyle = TextStyle.Default,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Create",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                        createTask.invoke(text)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskBottomSheetPreview() {

    val addTaskBottomSheetState = rememberStandardBottomSheetState (
        initialValue = SheetValue.Expanded
    )
    PerfectDayTheme {
        AddTaskBottomSheet(
            isBottomSheetVisible = true,
            sheetState = addTaskBottomSheetState ,
            createTask = {},
            onDismiss = {})
    }
}
