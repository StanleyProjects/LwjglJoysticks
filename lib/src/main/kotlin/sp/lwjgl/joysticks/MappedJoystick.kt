package sp.lwjgl.joysticks

internal class MappedJoystick(
    private val delegate: GLFWJoystick,
    private val mapping: JoystickMapping,
) : Joystick {
    override val metaData: JoystickMetaData = delegate.metaData

    override fun isPressed(button: JoystickButton): Boolean {
        return delegate.buttons[mapping.getIndex(button)].toInt() == 1
    }

    override fun getValue(axis: JoystickAxis): Float {
        return delegate.axes[mapping.getIndex(axis)]
    }
}
