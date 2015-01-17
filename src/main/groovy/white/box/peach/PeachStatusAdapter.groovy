package white.box.peach

import groovy.util.logging.Slf4j;
import java.text.SimpleDateFormat
import twitter4j.Status
import twitter4j.StatusAdapter
import twitter4j.StatusUpdate
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory

/**
 * peach用のTwitterStreamリスナークラス。<br>
 * 以下の仕様を実現する。
 * <ul>
 * <li>特定の発言に反応し、リプライを返す。</li>
 * <li>リプライ、リツイートはリプライ対象外とする。</li>
 * </ul>
 *
 * @author seri
 */
@Slf4j
class PeachStatusAdapter extends StatusAdapter {

	@Override
	public void onStatus(Status status) {

		// リプライ、リツイートの場合は処理しない
		if (status.inReplyToScreenName != null || status.isRetweet()) {
			return;
		}

		log.info("tweet : @${status.getUser().getScreenName()} - ${status.getText()}")

		Twitter twitter = TwitterFactory.getSingleton();

		String message = "@${status.getUser().getScreenName()} そのままがいいよ"

		StatusUpdate tweetstatus = new StatusUpdate(message)
		tweetstatus.setInReplyToStatusId(status.getId())

		// リプライ実行
		try {
			Status replyStatus = twitter.updateStatus(tweetstatus)
		} catch (TwitterException te) {

			// 同一ユーザへ連続してリプライした場合、重複メッセージになり
			// つぶやきが失敗するので、その場合だけ日時を付けてつぶやく
			Date date = new Date()
			def dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			tweetstatus = new StatusUpdate(message + "\n" + dateFormat.format(date))
			tweetstatus.setInReplyToStatusId(status.getId())
			twitter.updateStatus(tweetstatus)
		}

		log.info("reply : ${tweetstatus.getStatus()}")
	}

	@Override
	public void onException(Exception ex) {
		log.error(ex)
	}
}
