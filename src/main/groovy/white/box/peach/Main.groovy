package white.box.peach

import groovy.util.logging.Slf4j
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
 * Twitter botを起動させる実行クラス。
 *
 * @author seri
 */
@Slf4j
class Main {

	/**
	 * 実行クラス。
	 * <ul>
	 * <li>TwitterStreamの取得を行う。</li>
	 * <li>リプライ対象となる文言を設定する。</li>
	 * </ul>
	 *
	 * @param args
	 */
	static main(args) {

		log.info("start peach")

		// 設定情報を取得
		ConfigObject config = new ConfigSlurper().parse(
			new File('./conf/Config.groovy').toURI().toURL())

		String targetTweet = config.peach.target.tweet
		String replyMessage = config.peach.reply.message

		if (!config.containsKey("peach.target.tweet") || targetTweet == "" ||
			!config.containsKey("peach.reply.message") || replyMessage == "") {
			log.error("must be setting Config")
			System.exit(-1)
		}

		// Stream情報を取得できるTwitterインスタンスを取得
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance()
		twitterStream.addListener(new PeachStatusAdapter(replyMessage))

		// botが反応するキーワードを設定
		ArrayList<String> track = new ArrayList<>()
		track.addAll(Arrays.asList(targetTweet))
		String[] trackArray = track.toArray(new String[track.size()])

		twitterStream.filter(new FilterQuery(0, null, trackArray))

		// Ctrl+Cによる終了処理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run () {
				log.info("exit.")
			}
		});
	}
}
