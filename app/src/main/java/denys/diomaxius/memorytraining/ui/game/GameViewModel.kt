package denys.diomaxius.memorytraining.ui.game

import android.util.Log
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

    fun checkUserInput(icon : ImageVector) {
        if (randomizeIconsCreator.passLevel()) {
            generateIconsRow()
        } else {
            if (randomizeIconsCreator.checkUserInput(icon)) {
                Log.i("Check User Input", "Right answer")
            } else {
                Log.i("Check User Input", "Wrong answer")
                generateIconsRow()
            }
        }
    }
}