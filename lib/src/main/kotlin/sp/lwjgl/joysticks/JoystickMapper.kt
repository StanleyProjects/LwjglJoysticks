package sp.lwjgl.joysticks

interface JoystickMapper {
    fun map(metaData: JoystickMetaData): JoystickMapping?
}
