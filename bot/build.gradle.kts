plugins {
    id("io.andreiruban.build")
}

dependencies {
    api(tgBotApi)
    implementation(syndicationApi)
    implementation(komodoLogging)
}