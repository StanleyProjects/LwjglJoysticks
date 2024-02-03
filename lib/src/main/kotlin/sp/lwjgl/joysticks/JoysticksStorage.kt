package sp.lwjgl.joysticks

import org.lwjgl.glfw.GLFW

class JoysticksStorage(
    private val mapper: JoystickMapper,
    private val listener: Listener,
) {
    constructor(
        mappings: Map<String, JoystickMapping>,
        onPressButton: (JoystickMetaData, JoystickButton, Boolean) -> Unit,
    ) : this(
        mapper = MapJoystickMapper(mappings),
        listener = object : Listener {
            override fun onPress(metaData: JoystickMetaData, button: JoystickButton, isPressed: Boolean) {
                onPressButton(metaData, button, isPressed)
            }
        },
    )

    interface Listener {
        fun onPress(metaData: JoystickMetaData, button: JoystickButton, isPressed: Boolean)
    }

    private val joysticks: MutableMap<Int, Joystick> = mutableMapOf()

    fun getJoysticks(): Map<Int, Joystick> {
        return joysticks
    }

    fun update() {
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
            if (old == null || old.metaData.id != joystick.metaData.id) continue
            JoystickButton.entries.forEach { button ->
                val newValue = joystick.buttons[mapping.getIndex(button)].toInt() == 1
                if (old.isPressed(button) != newValue) {
                    listener.onPress(metaData = joystick.metaData, button = button, isPressed = newValue)
                }
            }
        }
    }
}
