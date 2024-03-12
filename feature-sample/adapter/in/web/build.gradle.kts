plugins {
    commonPlugins()
}

repositories {
    commonRepositories()
}

dependencies {
    commonDependencies()

    // project
    implementation(project(":common"))
}
