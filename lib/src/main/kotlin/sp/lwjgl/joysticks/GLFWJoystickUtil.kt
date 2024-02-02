package sp.lwjgl.joysticks

import org.lwjgl.glfw.GLFW

internal object GLFWJoystickUtil {
    fun getJoystickOrNull(number: Int): GLFWJoystick? {
        val isPresent = GLFW.glfwJoystickPresent(number)
        if (!isPresent) return null
        val id = GLFW.glfwGetJoystickGUID(number)
        if (id.isNullOrBlank()) return null
        val buttons = GLFW.glfwGetJoystickButtons(number)?.toByteArray() ?: return null
        val axes = GLFW.glfwGetJoystickAxes(number)?.toFloatArray() ?: return null
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
