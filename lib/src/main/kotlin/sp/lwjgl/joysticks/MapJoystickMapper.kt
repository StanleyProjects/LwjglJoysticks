package sp.lwjgl.joysticks

internal class MapJoystickMapper(
    private val mappings: Map<String, JoystickMapping>,
) : JoystickMapper {
    override fun map(metaData: JoystickMetaData): JoystickMapping? {
        return mappings[metaData.id]
    }
}
