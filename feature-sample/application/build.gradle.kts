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
    implementation(project(":feature-sample:application:port:in"))
}
