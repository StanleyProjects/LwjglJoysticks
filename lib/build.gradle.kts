version = "0.0.1"

repositories.mavenCentral()

plugins {
    id("org.jetbrains.kotlin.jvm")
}

val compileKotlinTask = tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin") {
    kotlinOptions {
        jvmTarget = Version.jvmTarget
//        freeCompilerArgs = freeCompilerArgs + setOf("-module-name", colonCase(maven.group, maven.id)) // todo
    }
}

tasks.getByName<JavaCompile>("compileTestJava") {
    targetCompatibility = Version.jvmTarget
}

tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileTestKotlin") {
    kotlinOptions.jvmTarget = Version.jvmTarget
}

dependencies {
    val group = LwjglUtil.group
    implementation(platform("$group:lwjgl-bom:${Version.lwjgl}"))
    LwjglUtil.modules.forEach { name ->
        implementation(group = group, name = name)
    }
}
