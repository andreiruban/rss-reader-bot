plugins {
    id("io.andreiruban.build")
}

dependencies {
    api(tgBotApi)
    implementation(logback)
    implementation(komodoLogging)
}