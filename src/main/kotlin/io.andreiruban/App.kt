package io.andreiruban

import io.heapy.logging.logger

object App {
    @JvmStatic
    fun main(args: Array<String>) {

        val configuration = DefaultBotConfiguration()

        val rssReaderBot = { RssReaderBot(configuration) }

        startBot(configuration, rssReaderBot)

        LOGGER.info("Application has been started.")
    }

    private val LOGGER = logger<App>()
}