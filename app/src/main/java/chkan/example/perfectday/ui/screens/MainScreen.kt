package chkan.example.perfectday.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chkan.example.perfectday.ui.theme.PerfectDayTheme

@Composable
fun MainScreen() {

    val list = remember{
        List(5) {index -> "Text Item #$index"}
    }

    LazyColumn(modifier =
    Modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
    ){

        stickyHeader(text = "My Perfect Day")

        items(list){ item ->
            ListItem(text = item)
        }

        stickyHeader(text = "My Perfect Week")

        items(5){ index ->
            Text(
                text = "Text #$index",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun ListItem(text: String){
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(16.dp))
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.stickyHeader(text: String){
    stickyHeader {
        Surface(
            shadowElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PerfectDayTheme {
        MainScreen()
    }
}
