package denys.diomaxius.memorytraining.ui.game

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun Game(
    gameViewModel: GameViewModel = viewModel()
) {
    val iconsRow = gameViewModel.iconsRow.observeAsState()

    val shuffleIcons = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(iconsRow.value?.randomizeIcons) {
        if (shuffleIcons.value) {
            delay(3000)
            gameViewModel.shuffleIcons()
            Log.i("Check delay", "Delay")
            shuffleIcons.value = false
        }

    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        iconsRow.value?.randomizeIcons?.forEach {
            Icon(
                modifier = Modifier.clickable {
                   shuffleIcons.value = gameViewModel.checkUserInput(it)
                },
                imageVector = it,
                contentDescription = ""
            )
        }
    }
}