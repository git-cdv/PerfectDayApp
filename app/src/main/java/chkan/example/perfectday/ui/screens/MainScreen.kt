package chkan.example.perfectday.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chkan.example.perfectday.R
import chkan.example.perfectday.ui.theme.PerfectDayTheme

@Composable
fun MainScreen() {

    val list = remember{
        List(5) {index -> "Text Item #$index"}
    }

    LazyColumn(modifier =
    Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
    ){

        stickyHeader(text = "My Perfect Day")

        items(list){ item ->
            ListItem(text = item)
        }

        item {
            OutlinedButtonWithIcon(text = stringResource(id = R.string.add_daily_task), icon = Icons.Filled.Add, onClick = {})
        }

        stickyHeader(text = "My Perfect Week")

        items(5){ index ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Text #$index",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(16.dp))
            }
        }

        item {
            OutlinedButtonWithIcon(text = stringResource(id = R.string.add_weekly_task), icon = Icons.Filled.Add, onClick = {})
        }
    }
}

@Composable
fun OutlinedButtonWithIcon(text: String, icon: ImageVector, onClick: ()->Unit) {
    OutlinedButton(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.normal_padding))
        ,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        border = BorderStroke(dimensionResource(id = R.dimen.normal_border_width), MaterialTheme.colorScheme.secondary)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon,
                contentDescription = text,
                Modifier.padding(horizontal = dimensionResource(id = R.dimen.small_padding))
            )
            Text(text, style = MaterialTheme.typography.titleMedium,)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(text: String){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp, pressedElevation = 2.dp),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_border_width)), onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding), horizontal = dimensionResource(id = R.dimen.normal_padding))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp))
    }
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
