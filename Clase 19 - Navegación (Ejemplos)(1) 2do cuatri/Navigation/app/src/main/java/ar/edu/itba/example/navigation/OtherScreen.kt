package ar.edu.itba.example.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.navigation.ui.theme.NavigationTheme

@Composable
fun OtherScreen(id:Int?) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.received_id, id ?: "null"),
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OtherScreenPreview() {
    NavigationTheme {
        OtherScreen(1234)
    }
}