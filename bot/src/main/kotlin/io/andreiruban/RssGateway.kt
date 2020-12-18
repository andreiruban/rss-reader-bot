package io.andreiruban

import io.heapy.logging.debug
import io.heapy.logging.logger
import io.ktor.client.*
import io.ktor.client.request.*

// TODO: add Ktor Client configuration
// TODO: cover with unit tests
// TODO: Introduce RSS-parser
class RssGateway(
    private val client: HttpClient,
    private val url: String
) {

    suspend fun getFeed(): String {
        val resp = client.get<String>(url)
        LOGGER.debug { resp }
        return resp
    }

    companion object {
        private val LOGGER = logger<RssGateway>()
    }
}