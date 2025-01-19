package denys.diomaxius.memorytraining.data

import androidx.compose.ui.graphics.vector.ImageVector
import denys.diomaxius.memorytraining.data.model.RandomizeIcons

class CreateRandomizeIcons {
    private var rightOrderRandomizeIcons = mutableListOf<ImageVector>()
    private var index = 0
    fun createRandomizeIcons(): RandomizeIcons {
        val randomizeIcons = RandomizeIcons()
        for (i in 1..3) {
            randomizeIcons.randomizeIcons.add(iconList.random())
        }
        rightOrderRandomizeIcons = randomizeIcons.randomizeIcons
        return randomizeIcons
    }
    fun passLevel() : Boolean {
       return if (index + 1 == rightOrderRandomizeIcons.size) {
           index = 0
           true
       } else {
           false
       }
    }
    fun checkUserInput (icon: ImageVector) : Boolean {
        return if (icon == rightOrderRandomizeIcons[index]) {
            index++
            true
        } else {
            index = 0
            false
        }
    }
}