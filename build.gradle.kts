import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.kjudge"
version = "0.1-SNAPSHOT"

buildscript {
    var kotlinVersion: String by extra
    val springBootVersion = "1.5.9.RELEASE"
    kotlinVersion = "1.2.10"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlinVersion))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        //Required for kotlin-spring plugin
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }

}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

val kotlinVersion: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlinVersion))

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

