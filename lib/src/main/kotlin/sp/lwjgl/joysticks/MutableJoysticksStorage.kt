package sp.lwjgl.joysticks

import org.lwjgl.glfw.GLFW

class MutableJoysticksStorage : JoysticksStorage {
    override val joysticks: MutableMap<Int, Joystick> = mutableMapOf()

    fun update(mapper: JoystickMapper) {
        for (number in GLFW.GLFW_JOYSTICK_1..GLFW.GLFW_JOYSTICK_LAST) {
            val joystick = GLFWJoystickUtil.getJoystickOrNull(number = number)
            if (joystick == null) {
                joysticks.remove(number)
                continue
            }
            val mapping = mapper.map(metaData = joystick.metaData)
            if (mapping == null) {
                joysticks.remove(number)
                continue
            }
            val old = joysticks[number]
            joysticks[number] = MappedJoystick(joystick, mapping)
            if (old == null) continue
            // todo on click
        }
    }
}
