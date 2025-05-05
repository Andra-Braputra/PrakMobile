package com.example.scrollablecompose

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scrollablecompose.data.Datasource
import com.example.scrollablecompose.model.Fallout
import com.example.scrollablecompose.ui.theme.FalloutButton

@Composable
fun FalloutApp(navController: NavController) {
    val layoutDirection = LocalLayoutDirection.current
    val paddingValues = WindowInsets.safeDrawing.asPaddingValues()
    val startPadding = paddingValues.calculateStartPadding(layoutDirection)
    val endPadding = paddingValues.calculateEndPadding(layoutDirection)


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = startPadding, end = endPadding),
        color = colorResource(R.color.fallout_bg)
    ) {
        GameList(
            gameList = Datasource().loadFallout(),
            onDetailClick = { id ->
                navController.navigate("detail/$id")
            }
        )
    }
}


@Composable
fun GameList(
    gameList: List<Fallout>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp)
    ) {
        items(gameList.size) { index ->
            val game = gameList[index]
            FalloutCard(
                fallout = game,
                modifier = Modifier.padding(vertical = 4.dp),
                onDetailClick = { onDetailClick(game.id) }
            )
        }
    }
}

@Composable
fun FalloutCard(
    fallout: Fallout,
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val image = painterResource(id = fallout.imageResId)
    val title = stringResource(id = fallout.titleResId)
    val description = stringResource(id = fallout.descriptionResId)
    val wikiUrl = stringResource(id = fallout.wikiResId)

    Card(modifier = modifier.fillMaxHeight(), colors = CardDefaults.cardColors(containerColor = com.example.scrollablecompose.ui.theme.FalloutCard) ) {
        Row(modifier = Modifier.fillMaxHeight()) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .aspectRatio(3f / 5f)
                    .weight(1.5f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                    color = colorResource(R.color.fallout_text)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = colorResource(R.color.fallout_text)
                )
                Row(
                    modifier = Modifier.padding(bottom = 12.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = {
                            openWikiForGame(context, wikiUrl)
                        },
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = FalloutButton)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Wiki",
                            color = colorResource(R.color.fallout_button_text)
                        )
                    }
                    Button(
                        onClick = onDetailClick,
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = FalloutButton)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Detail",
                            color = colorResource(R.color.fallout_button_text)
                        )
                    }
                }
            }
        }
    }
}

fun openWikiForGame(context: Context, wikiUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
    context.startActivity(intent)
}
