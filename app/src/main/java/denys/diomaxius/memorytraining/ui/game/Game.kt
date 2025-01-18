package denys.diomaxius.memorytraining.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Game(
    gameViewModel: GameViewModel = viewModel()
) {
    val iconsRow = gameViewModel.iconsRow.observeAsState()

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        iconsRow.value?.randomizeIcons?.forEach{
            Icon(imageVector = it, contentDescription = "")
        }
    }
}