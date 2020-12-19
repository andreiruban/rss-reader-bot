package io.andreiruban

import io.heapy.logging.logger
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.generics.LongPollingBot

fun startBot(
    configuration: BotConfiguration,
    bot: () -> LongPollingBot
): ShutdownBot {
    try {
        ApiContextInitializer.init()
        val bot = TelegramBotsApi()
            .registerBot(bot())
        LOGGER.info("${configuration.name} started.")
        return bot::stop
    } catch (e: TelegramApiException) {
        LOGGER.error("An error occurred in the bot", e)
        throw e
    }
}

typealias ShutdownBot = () -> Unit

private val LOGGER = logger<RssReaderBot>()
