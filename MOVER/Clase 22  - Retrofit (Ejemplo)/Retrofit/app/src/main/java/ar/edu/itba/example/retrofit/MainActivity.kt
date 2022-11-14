package ar.edu.itba.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.example.retrofit.data.network.models.NetworkData
import ar.edu.itba.example.retrofit.ui.theme.RetrofitTheme
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                val scaffoldState: ScaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {  contentPadding ->
                    MainScreen(
                        scaffoldState = scaffoldState,
                        modifier = Modifier.padding(contentPadding),
                        viewModel = viewModel())
                }
            }
        }
    }
}

@Composable
fun MainScreen(scaffoldState: ScaffoldState,
               modifier: Modifier,
               viewModel: MainViewModel) {
    val uiState = viewModel.uiState

    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.isLoading),
        onRefresh = { viewModel.fetchUsers(1) },
    ) {
        Column(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (uiState.isLoading)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.loading),
                            fontSize = 16.sp
                        )
                    }
                else {
                    val list = uiState.users?.data.orEmpty()
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 196.dp)
                    ) {
                        items(
                            count = list.size,
                            key = { index ->
                                list[index].id.toString()
                            }
                        ) { index ->
                            UserCard(list[index])
                        }
                    }
                }
            }
            Button(
                onClick = {
                    viewModel.fetchUsers(2)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.load_users),
                    modifier = Modifier.padding(8.dp))
            }

            if (uiState.hasError) {
                val actionLabel = stringResource(R.string.dismiss)

                LaunchedEffect(scaffoldState.snackbarHostState) {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = uiState.message!!,
                        actionLabel = actionLabel
                    )
                    when (result) {
                        SnackbarResult.Dismissed -> viewModel.dismissMessage()
                        SnackbarResult.ActionPerformed -> viewModel.dismissMessage()
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(
    data: NetworkData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { },
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            AsyncImage(
                model = data.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .weight(1f)) {
                Text(
                    text = "${data.firstName} - ${data.lastName}",
                    fontSize = 16.sp
                )
                Text(
                    text = data.email?: "",
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    UserCard(data = NetworkData(id = 1, email = "johndoe@email.com", firstName = "John", lastName = "Doe", avatar = "https://reqres.in/img/faces/7-image.jpg"))
}