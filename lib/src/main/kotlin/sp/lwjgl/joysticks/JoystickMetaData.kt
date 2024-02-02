package sp.lwjgl.joysticks

import java.util.UUID

data class JoystickMetaData(
    val number: Int,
    val id: UUID,
    val name: String,
)
