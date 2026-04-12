plugins {
    kotlin("jvm") version "2.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"


/* Change this to the version of the BGW you want to use */
val bgwVersion = "0.10"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    implementation(group = "tools.aqua", name = "bgw-gui", version = bgwVersion)
    implementation(group = "tools.aqua", name = "bgw-net-common", version = bgwVersion)
    implementation(group = "tools.aqua", name = "bgw-net-client", version = bgwVersion)
}

kotlin {
    jvmToolchain(11)
}

tasks.test {
    useJUnitPlatform()
}