package io.andreiruban

import com.ouattararomuald.syndication.Syndication
import io.heapy.logging.logger
import kotlinx.coroutines.runBlocking
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class RssReaderBot(
    private val configuration: BotConfiguration

) : TelegramLongPollingBot() {

    override fun getBotToken() = configuration.token
    override fun getBotUsername() = configuration.name

    // TODO: introduce TG-command to place urls
    private val syndicationReader = Syndication(url = "https://hackernoon.com/feed")

    // TODO: introduce dynamic reader creation
    // TODO: think about possible reader pool
    private val rssReader = syndicationReader.create(RssReader::class.java)


    // FIXME: Replace simple echo bot with proper functionality
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val messageText = update.message.text
            LOGGER.debug(messageText)
            val chatId = update.message.chatId

            runBlocking {
                val rssFeed = rssReader.readRss()
                val links = rssFeed.channel.items?.map { it.link }

                LOGGER.info(rssFeed.toString())

                if (!links.isNullOrEmpty()) {
                    links.forEach {
                        LOGGER.info(it)
                        val message = SendMessage().setChatId(chatId).setText(it)
                        try {
                            execute(message)
                        } catch (e: TelegramApiException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val LOGGER = logger<RssReaderBot>()
    }
}
