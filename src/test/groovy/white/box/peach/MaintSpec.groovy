package white.box.peach;

import spock.lang.Specification;

/**
 * Main.groovyのテストケース。<br>
 * 以下のテストを行う。
 * <li>ログ出力</li>
 * <li>peach実行確認</li>
 *
 * @author seri
 */
class MaintSpec extends Specification {

	/**
	 * ログ出力とmainの呼び出しを確認する。
	 */
	def 起動テスト() {

		setup:

		Main.main(null)
	}
}
