import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.commonPlugins() {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    id(Plugins.javaTestFixtures)
    id(Plugins.jacoco)
    id(Plugins.Detekt.detekt_plugin) version Plugins.Detekt.version
}

fun RepositoryHandler.commonRepositories() {
    mavenLocal()
    mavenCentral()
}

fun DependencyHandler.commonDependencies() {
    // Kotlin
    add("implementation", Lib.Kotlin.jdk8)
    add("implementation", Lib.Kotlin.stdlib)
    add("implementation", Lib.Kotlin.reflect)
    add("testImplementation", Lib.Kotlin.test)

    // Kotest
    add("testImplementation", Lib.Kotest.arrow)
    add("testImplementation", Lib.Kotest.junit5)

    // ArrowKt
    add("implementation", Lib.ArrowKt.core)
    add("testImplementation", Lib.ArrowKt.core)
    add("testFixturesImplementation", Lib.ArrowKt.core)

    // JUnit
    add("testCompileOnly", Lib.JUnit.api)
    add("testRuntimeOnly", Lib.JUnit.engine)

    // AssertJ
    add("testImplementation", Lib.AssertJ.assertj_core)

    // ArchUnit
    add("testImplementation", Lib.ArchUnit.junit5)
}

object Plugins {
    const val jacoco = "jacoco"
    const val javaTestFixtures = "java-test-fixtures"

    object Detekt {
        const val version = "1.23.5"
        const val detekt_plugin = "io.gitlab.arturbosch.detekt"
        const val detekt_formatting = "$detekt_plugin:detekt-formatting:$version"
    }
}

object Lib {
    object Kotlin {
        const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
        const val test = "org.jetbrains.kotlin:kotlin-test"
    }

    object Kotest {
        private const val junit5_version = "5.5.5"
        const val junit5 = "io.kotest:kotest-runner-junit5:$junit5_version"

        private const val arrow_version = "1.4.0"
        const val arrow = "io.kotest.extensions:kotest-assertions-arrow-jvm:$arrow_version"
    }

    object ArrowKt {
        private const val version = "1.1.5"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object JUnit {
        private const val version = "5.9.1"
        const val api = "org.junit.jupiter:junit-jupiter-api:$version"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
    }

    object AssertJ {
        private const val version = "3.25.3"
        const val assertj_core = "org.assertj:assertj-core:$version"
    }

    object ArchUnit {
        private const val version = "1.0.1"
        const val junit5 = "com.tngtech.archunit:archunit-junit5:$version"
    }
}
