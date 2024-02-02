package sp.lwjgl.joysticks

import org.lwjgl.glfw.GLFW
import java.util.UUID

internal object GLFWJoystickUtil {
    fun getJoystickOrNull(number: Int): GLFWJoystick? {
        val isPresent = GLFW.glfwJoystickPresent(number)
        if (!isPresent) return null
        val id = try {
            UUID.fromString(GLFW.glfwGetJoystickGUID(number))
        } catch (e: Throwable) {
            return null
        }
        val buttons = GLFW.glfwGetJoystickButtons(number)?.array() ?: return null
        val axes = GLFW.glfwGetJoystickAxes(number)?.array() ?: return null
        val name = GLFW.glfwGetGamepadName(number)?.takeIf { it.isNotBlank() } ?: "Joystick #$number"
        return GLFWJoystick(
            metaData = JoystickMetaData(
                number = number,
                id = id,
                name = name,
            ),
            buttons = buttons,
            axes = axes,
        )
    }
}
