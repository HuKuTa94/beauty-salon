import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val parentProjectDir = projectDir

plugins {
    commonPlugins()
}

repositories {
    commonRepositories()
}

dependencies {
    commonDependencies()
}

subprojects {
    apply {
        plugin("java")
        plugin(Plugins.jacoco)
        plugin(Plugins.javaTestFixtures)
//        plugin(Plugins.Detekt.detekt_plugin)
    }

//    detekt {
//        config.setFrom("$parentProjectDir/tools/detekt/detekt-config.yml")
//        source.from(
//            files("src/main/kotlin", "src/test/kotlin")
//        )
//
//        // Builds the AST in parallel. Rules are always executed in parallel.
//        // Can lead to speedups in larger projects. `false` by default.
//        parallel = true
//
//        buildUponDefaultConfig = true
//
//        reports {
//            html {
//                required.set(true)
//            }
//        }
//
//        dependencies {
//            detektPlugins(Plugins.Detekt.detekt_formatting)
//        }
//    }

    tasks {
        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

        val check = named<DefaultTask>("check")
        check {
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(check)
            finalizedBy(jacocoTestCoverageVerification)
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {

                rule {
                    limit {
                        minimum = Globals.testCoverageThreshold
                    }
                }
            }
        }

        val failOnWarning =
                project.properties["allWarningsAsErrors"] != null &&
                project.properties["allWarningsAsErrors"] == "true"

        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
                allWarningsAsErrors = failOnWarning
                freeCompilerArgs = listOf("-Xjvm-default=all")
            }
        }

        withType<Test> {
            useJUnitPlatform()

            // This is for logging and can be removed
            testLogging {
                events(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
            }
        }
    }
}