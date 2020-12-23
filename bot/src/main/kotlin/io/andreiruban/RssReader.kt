package io.andreiruban

import com.ouattararomuald.syndication.atom.AtomFeed
import com.ouattararomuald.syndication.rss.RssFeed

interface RssReader {

    fun readRss(): RssFeed

    fun readAtom(): AtomFeed
}
