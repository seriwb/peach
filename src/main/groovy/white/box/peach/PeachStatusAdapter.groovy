package white.box.peach

import groovy.util.logging.Slf4j;
import twitter4j.Status
import twitter4j.StatusAdapter

/**
 * peach用のTwitterStreamリスナークラス。<br>
 * 以下の仕様を実現する。
 * <ul>
 * <li>特定の発言に反応し、リプライを返す。</li>
 * <li>リプライ、リツイートはリプライ対象外とする。</li>
 * <li></li>
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

		log.debug("@${status.getUser().getScreenName()} - ${status.getText()}")
	}

	@Override
	public void onException(Exception ex) {
		log.error(ex)
	}
}
