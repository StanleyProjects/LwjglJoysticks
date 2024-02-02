package sp.lwjgl.joysticks

interface JoystickMapping {
    fun getIndex(button: JoystickButton): Int
    fun getIndex(axis: JoystickAxis): Int
}
