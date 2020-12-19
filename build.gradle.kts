plugins {
    id("io.andreiruban.build")
    application
}

application {
    applicationName = "rss-reader-bot"
    mainClass.set("io.andreiruban.App")
}

dependencies {
    implementation(logback)
    implementation(komodoLogging)

    implementation(komodoDotenv)
    implementation(config4k)

    implementation(ktorClient)

    implementation(project(":bot"))
}
