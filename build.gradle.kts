import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.kjudge"
version = "0.1-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    val springBootVersion: String = "1.5.9.RELEASE"
    kotlin_version = "1.2.10"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
    
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlin_version))

    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-freemarker")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-web-services")

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

