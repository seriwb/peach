package white.box.peach

import groovy.util.logging.Slf4j;
import twitter4j.AsyncTwitter
import twitter4j.AsyncTwitterFactory
import twitter4j.FilterQuery
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusAdapter
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.TwitterAdapter
import twitter4j.TwitterException
import twitter4j.TwitterListener
import twitter4j.TwitterMethod
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory
import twitter4j.auth.AccessToken

/**
 * twitterStream.filterを実行するスレッド
 *
 * @author seri
 */
@Slf4j
class Watcher extends Thread {

	@Override
	public void run() {

		// 設定情報を取得
		ConfigObject config = new ConfigSlurper().parse(
			new File('./conf/Config.groovy').getText("UTF-8"))

		Properties props = config.toProperties()
		String targetTweet = props.getProperty("peach.target.tweet")
		String replyMessage = props.getProperty("peach.reply.message")

		if (targetTweet == null || targetTweet == "" ||
			replyMessage == null || replyMessage == "") {
			log.error("must be setting Config")
			System.exit(-1)
		}

		log.info("traget tweet: $targetTweet")
		log.info("reply message: $replyMessage")

		// Stream情報を取得できるTwitterインスタンスを取得
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance()
		twitterStream.addListener(new PeachStatusAdapter(replyMessage))

		// botが反応するキーワードを設定
		ArrayList<String> track = new ArrayList<>()
		track.addAll(Arrays.asList(targetTweet))
		String[] trackArray = track.toArray(new String[track.size()])

		twitterStream.filter(new FilterQuery(0, null, trackArray))

	}
}
