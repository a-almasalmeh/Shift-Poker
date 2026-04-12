import org.gradle.kotlin.dsl.application

plugins {
    kotlin("jvm") version "1.9.25"
    application

}

group = "edu.udo.cs.sopra"
version = "1.0"

/* Change this to the version of the BGW you want to use */
val bgwVersion = "0.10"

kotlin {
    jvmToolchain(11)
}


repositories {
    mavenCentral()
}


dependencies {
    testImplementation(kotlin("test-junit5"))
    implementation(group = "tools.aqua", name = "bgw-gui", version = bgwVersion)
    implementation(group = "tools.aqua", name = "bgw-net-common", version = bgwVersion)
    implementation(group = "tools.aqua", name = "bgw-net-client", version = bgwVersion)
}


tasks.test {
    useJUnitPlatform()
    ignoreFailures = true
}