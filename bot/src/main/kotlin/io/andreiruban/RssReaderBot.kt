package io.andreiruban

import io.heapy.logging.logger
import kotlinx.coroutines.runBlocking
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class RssReaderBot(
    private val configuration: BotConfiguration,
    private val rssGateway: RssGateway
) : TelegramLongPollingBot() {

    override fun getBotToken() = configuration.token
    override fun getBotUsername() = configuration.name

    // FIXME: Replace simple echo bot with proper functionality
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val messageText = update.message.text
            LOGGER.debug(messageText)
            val chatId = update.message.chatId

            runBlocking {
                val rssFeed = rssGateway.getFeed()
                LOGGER.info(rssFeed)
                val message = SendMessage().setChatId(chatId).setText(rssFeed)
                try {
                    execute(message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        private val LOGGER = logger<RssReaderBot>()
    }
}
