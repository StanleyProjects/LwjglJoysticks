package sp.lwjgl.joysticks

import java.nio.ByteBuffer
import java.nio.FloatBuffer

internal fun ByteBuffer.toByteArray(): ByteArray {
    val result = ByteArray(remaining())
    get(result)
    return result
}

internal fun FloatBuffer.toFloatArray(): FloatArray {
    val result = FloatArray(remaining())
    get(result)
    return result
}
