package com.example.vdcall.compose.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vdcall.R
import com.example.vdcall.Screen
import com.example.vdcall.dataStore
import com.example.vdcall.ui.VdcallTheme
import com.example.vdcall.utilities.EXAMPLE_COUNTER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val MenuMap=mapOf(
        "Chat" to fun(){navController.navigate( Screen.Room.route)},
        "Account" to fun(){navController.navigate( Screen.Account.route)}
    )
    val context= LocalContext.current
    var userName by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        context.dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: ""
        }.collect { value ->
            userName = value
        }
    }

    Column {
        Text(text= "Hi: ${userName}", modifier = Modifier.align(CenterHorizontally))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.card_side_margin),
                vertical = dimensionResource(id = R.dimen.header_margin)
            )
        ) {
            MenuMap.forEach { entry ->
                item{
                    CardListItem(entry.key, onClick = entry.value )
                }
            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListItem(name: String,
//                 imageUrl: String,
                 onClick: () -> Unit) {
    Card(

        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Column(Modifier.fillMaxWidth()) {
//            Image(
//                painter:,
//                contentDescription ="" ,//stringResource(R.string.a11y_plant_item_image),
//                Modifier
//                    .fillMaxWidth()
//                    .height(dimensionResource(id = R.dimen.plant_item_image_height)),
//                contentScale = ContentScale.Crop
//            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    VdcallTheme {
        HomeScreen(rememberNavController())
    }
}