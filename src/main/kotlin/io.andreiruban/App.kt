package io.andreiruban

object App {
    @JvmStatic
    fun main(args: Array<String>) {

        val configuration = DefaultBotConfiguration()

        val rssReaderBot = { RssReaderBot(configuration) }

        startBot(configuration, rssReaderBot)
    }
}