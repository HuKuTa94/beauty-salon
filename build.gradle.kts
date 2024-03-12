import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    }

    tasks {
        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

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
                freeCompilerArgs = listOf("-Xjvm-default=enable")
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