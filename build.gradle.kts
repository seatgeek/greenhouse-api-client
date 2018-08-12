import org.jetbrains.kotlin.compiler.plugin.parsePluginOption
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.2.60"
}

group = "com.seatgeek.greenhouse"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    val retrofitVersion = "2.4.0"
    val okhttpVersion = "3.11.0"

    compile(kotlin("stdlib-jdk8"))

    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofitVersion}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${retrofitVersion}")

    implementation("com.google.code.gson:gson:2.8.5")
    implementation("io.reactivex.rxjava2:rxjava:2.2.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:${okhttpVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:{$okhttpVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${okhttpVersion}")

    val junitVersion = "5.1.0"

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

apply {
    from("gradle/gradle-mvn-push.gradle")
}