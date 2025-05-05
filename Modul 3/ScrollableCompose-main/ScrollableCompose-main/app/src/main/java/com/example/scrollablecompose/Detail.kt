package com.example.scrollablecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scrollablecompose.data.Datasource

@Composable
fun ShowDetail(gameId: Int) {
    val game = remember { Datasource().loadFallout().firstOrNull { it.id == gameId } }

    if (game == null) {
        Text(
            text = "Game not found",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.fallout_text)
        )
    } else {
        Column(modifier = Modifier
            .fillMaxHeight()
            .background(color = colorResource(R.color.fallout_bg))
            .run {
                padding(16.dp).fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            }

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.fallout_card))
            ) {
                Image(
                    painter = painterResource(id = game.imageResId),
                    contentDescription = stringResource(id = game.titleResId),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row{
                Text(
                    text = stringResource(id = game.titleResId),
                    style = MaterialTheme.typography.headlineLarge,
                    color = colorResource(R.color.fallout_accent)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = game.yearId),
                    style = MaterialTheme.typography.headlineLarge,
                    color = colorResource(R.color.fallout_text)


                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = game.detailResId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                color = colorResource(R.color.fallout_text)
            )
        }
    }
}
