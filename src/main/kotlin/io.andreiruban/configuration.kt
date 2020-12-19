package io.andreiruban

import io.heapy.komodo.config.dotenv.Dotenv
import java.nio.file.Paths

private val env = Dotenv(Paths.get("./devops/.env"))

interface Configuration {
    val bot: BotConfiguration
}

data class DefaultConfiguration(
    override val bot: DefaultBotConfiguration
) : Configuration

data class DefaultBotConfiguration(
    override val token: String,
    override val name: String
) : BotConfiguration
