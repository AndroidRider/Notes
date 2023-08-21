// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

// manual
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}