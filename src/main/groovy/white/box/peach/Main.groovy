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

	public static Boolean restart = Boolean.TRUE

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

		// Ctrl+Cによる終了処理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run () {
				log.info("exit.")
			}});

		Watcher watcher = new Watcher()

		while (true) {

			if (restart) {

				restart = Boolean.FALSE

				try {
					watcher.start()
				} catch (e) {
					log.error("Thread Error!", e)

					restart = Boolean.TRUE

					watcher = new Watcher()
				}
			}

			Thread.sleep(15 * 60 * 1000)
		}
	}
}
