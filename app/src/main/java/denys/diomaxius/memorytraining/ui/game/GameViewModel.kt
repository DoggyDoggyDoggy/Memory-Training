package denys.diomaxius.memorytraining.ui.game

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
}