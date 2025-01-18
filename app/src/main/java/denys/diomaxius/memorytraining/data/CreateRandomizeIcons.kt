package denys.diomaxius.memorytraining.data

import denys.diomaxius.memorytraining.data.model.RandomizeIcons

class CreateRandomizeIcons {
    fun createRandomizeIcons(): RandomizeIcons {
        val randomizeIcons = RandomizeIcons()
        for (i in 1..3) {
            randomizeIcons.randomizeIcons.add(iconList.random())
        }
        return randomizeIcons
    }
}