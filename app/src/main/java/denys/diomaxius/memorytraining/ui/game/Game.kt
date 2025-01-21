package denys.diomaxius.memorytraining.ui.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun Game(
    gameViewModel: GameViewModel = viewModel()
) {
    val iconsRow = gameViewModel.iconsRow.observeAsState()
    val userIconsRow = gameViewModel.userIconsRow.observeAsState()

    val shuffleIcons = remember {
        mutableStateOf(true)
    }


    LaunchedEffect(iconsRow.value?.randomizeIcons) {
        if (shuffleIcons.value) {
            delay(3000)
            gameViewModel.shuffleIcons()
            shuffleIcons.value = false
        }

    }

    Column( modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (!shuffleIcons.value){
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                userIconsRow.value?.randomizeIcons?.forEach { icon ->
                    Icon(
                        modifier = Modifier
                            .padding(bottom = 25.dp)
                            .size(50.dp),
                        imageVector = icon,
                        contentDescription = ""
                    )
                }
            }


            Spacer(modifier = Modifier.weight(1f))
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = if (shuffleIcons.value) Alignment.CenterVertically else Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            iconsRow.value?.randomizeIcons?.forEach {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .size(50.dp)
                        .clickable {
                            if (!shuffleIcons.value) {
                                shuffleIcons.value = gameViewModel.checkUserInput(it)
                            }
                        },
                    imageVector = it,
                    contentDescription = ""
                )
            }
        }
    }
}