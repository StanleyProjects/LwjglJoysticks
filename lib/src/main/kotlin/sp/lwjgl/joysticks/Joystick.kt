package sp.lwjgl.joysticks

interface Joystick {
    val metaData: JoystickMetaData
    fun isPressed(button: JoystickButton): Boolean
    fun getValue(axis: JoystickAxis): Float
}
