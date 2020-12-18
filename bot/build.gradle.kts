plugins {
    id("io.andreiruban.build")
}

dependencies {
    api(tgBotApi)
    implementation(ktorClient)
    implementation(komodoLogging)
}