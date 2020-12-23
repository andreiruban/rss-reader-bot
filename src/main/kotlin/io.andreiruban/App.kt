package io.andreiruban

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.heapy.logging.logger

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val configuration = ConfigFactory.load().extract<DefaultConfiguration>()

        val rssReaderBot = {
            RssReaderBot(
                configuration = configuration.bot
            )
        }

        startBot(
            configuration = configuration.bot,
            bot = rssReaderBot
        )

        LOGGER.info("Application has been started.")
    }

    private val LOGGER = logger<App>()
}