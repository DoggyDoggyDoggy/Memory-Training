package denys.diomaxius.memorytraining.ui.game

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import denys.diomaxius.memorytraining.data.CreateRandomizeIcons
import denys.diomaxius.memorytraining.data.model.RandomizeIcons

class GameViewModel : ViewModel() {
    private val randomizeIconsCreator = CreateRandomizeIcons()

    private val _iconsRow = MutableLiveData<RandomizeIcons>()
    val iconsRow: LiveData<RandomizeIcons> = _iconsRow



    init {
        generateIconsRow()
    }

    fun generateIconsRow() {
        _iconsRow.value = randomizeIconsCreator.createRandomizeIcons()
    }

    fun shuffleIcons() {
        _iconsRow.value = _iconsRow.value?.copy(
            randomizeIcons = _iconsRow.value?.randomizeIcons?.shuffled()?.toMutableList() ?: mutableListOf()
        )
    }

    fun checkUserInput(icon : ImageVector) : Boolean {
        return if (randomizeIconsCreator.passLevel()) {
            generateIconsRow()
            true
        } else {
            if (randomizeIconsCreator.checkUserInput(icon)) {
                false
            } else {
                generateIconsRow()
                true
            }
        }
    }
}