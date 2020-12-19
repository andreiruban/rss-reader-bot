package io.andreiruban

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.heapy.logging.logger
import io.ktor.client.*
import io.ktor.client.engine.apache.*

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val configuration = ConfigFactory.load().extract<DefaultConfiguration>()
        val client = HttpClient(Apache)

        val rssGateway = RssGateway(
            client = client,
            url = "https://hackernoon.com/feed"
        )

        val rssReaderBot = {
            RssReaderBot(
                configuration = configuration.bot,
                rssGateway = rssGateway
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