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

    private val _userIconsRow = MutableLiveData<RandomizeIcons>()
    val userIconsRow: LiveData<RandomizeIcons> = _userIconsRow

    init {
        generateIconsRow()
    }

    fun generateIconsRow() {
        _userIconsRow.value = RandomizeIcons()
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
                val updatedUserIcons = _userIconsRow.value?.randomizeIcons?.toMutableList()?.apply {
                    add(icon)
                } ?: mutableListOf(icon)
                _userIconsRow.value = RandomizeIcons(randomizeIcons = updatedUserIcons)


                _iconsRow.value = _iconsRow.value?.randomizeIcons?.toMutableList()?.apply {
                    val lastIndex = lastIndexOf(icon)
                    if (lastIndex != -1) removeAt(lastIndex)
                }?.let {
                    _iconsRow.value?.copy(
                        randomizeIcons = it
                    )
                }
                false
            } else {
                generateIconsRow()
                true
            }
        }
    }
}