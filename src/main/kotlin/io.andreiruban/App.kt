package io.andreiruban

import io.heapy.logging.logger
import io.ktor.client.*
import io.ktor.client.engine.apache.*

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val configuration = DefaultBotConfiguration()
        val client = HttpClient(Apache)

        val rssGateway = RssGateway(
            client = client,
            url = "https://hackernoon.com/feed"
        )

        val rssReaderBot = {
            RssReaderBot(
                configuration = configuration,
                rssGateway = rssGateway
            )
        }

        startBot(configuration, rssReaderBot)

        LOGGER.info("Application has been started.")
    }

    private val LOGGER = logger<App>()
}