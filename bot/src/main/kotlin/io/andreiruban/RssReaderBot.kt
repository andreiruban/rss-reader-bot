package io.andreiruban

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.generics.LongPollingBot


fun startBot(
    configuration: BotConfiguration,
    bot: () -> LongPollingBot
) {
    try {
        ApiContextInitializer.init()
        val bot = TelegramBotsApi()
            .registerBot(bot())
//        LOGGER.info("${configuration.name} started.")
//        return bot::stop
    } catch (e: TelegramApiException) {
//        LOGGER.error("An error occurred in the bot", e)
        throw e
    }
}

class RssReaderBot(
    private val configuration: BotConfiguration
) : TelegramLongPollingBot() {

    override fun getBotToken() = configuration.token
    override fun getBotUsername() = configuration.name

    // FIXME: Replace simple echo bot with proper functionality
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val messageText = update.message.text
//            LOG.info(messageText)
            val chatId = update.message.chatId
            val message = SendMessage().setChatId(chatId).setText(messageText)
            try {
                execute(message)
                println(message)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }
}
