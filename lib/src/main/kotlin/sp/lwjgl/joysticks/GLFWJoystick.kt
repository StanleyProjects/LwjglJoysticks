package sp.lwjgl.joysticks

import java.util.Objects

internal class GLFWJoystick(
    val metaData: JoystickMetaData,
    val buttons: ByteArray,
    val axes: FloatArray,
) {
    override fun toString(): String {
        val buttonsText = buttons.joinToString { String.format("%d", it) }
        val axesText = axes.joinToString { String.format("%+.2f", it) }
        return "GLFWJoystick($metaData, buttons: [$buttonsText], axes: $axesText)"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is GLFWJoystick -> {
                metaData == other.metaData &&
                    buttons.contentEquals(other.buttons) &&
                    axes.contentEquals(other.axes)
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(metaData, buttons.contentHashCode(), axes.contentHashCode())
    }
}
