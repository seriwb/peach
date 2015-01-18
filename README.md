# peach

Twitter上の特定の発言に反応し、あらかじめ指定された文言をリプライで返すTwitter botプログラムです。  
発言がリプライとリツイートのものは、本アプリケーションのリプライ対象外になります。

デフォルト設定では「痩せよう」というキーワードに反応し、
発言者に対して「そのままがいいよ」というリプライを返します。

Streaming APIのPublic streams（POST statuses/filter）で取得しているため、
キーワードの前後に文字があるとうまく拾えません。  
> もし前後に文字があるものも対象としたい場合は、GET statuses/sampleでTweetを取得して、
> 正規表現でキーワード検索すると良いかと思います。


### ※注意

本プログラムを動作させるTwitterアカウントのアイコンはイケメンにしましょう！  
また、通常使っているTwitterアカウントでこのツールを起動した場合・・・
どうなるかは貴方のイケメン度次第です！ご利用は計画的に！


## 使い方

1. [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)をインストール。
2. [releases](https://github.com/seriwb/peach/releases)からzipファイルを取得し、適当な場所に展開。
3. twitter4j.propertiesで「oauth.consumerKey」「oauth.consumerSecret」「oauth.accessToken」「oauth.accessTokenSecret」の値を設定。
4. botが反応するキーワードとリプライ内容を変更する場合は、conf/Config.groovyを編集。
5. コンソールからpeach.jarがある場所まで移動し、以下のコマンドを実行。
```
java -jar peach.jar
```

終了する場合はコンソール上でCtrl+Cを入力してください。


### 動作環境

- Javaが動作するOS（Windows、Mac、Linux等）
- JRE : 1.7 以上


#### Windowsで動かす場合

Windowsのコマンドプロンプトで実行した場合、プロンプトの文字コードがMS932なため、ログが文字化けします。  
log/peach.logには文字化けしないで表示されますので、結果確認はログファイルを利用してください。



# プログラムガイド

## プログラムの実行

以下のクラスに含まれるmainメソッドを実行してください。

    /peach/src/main/groovy/white/box/peach/Main.groovy


## ビルド

#### ビルド環境

- JDK : 1.8.0_25
- Groovy : 2.3.3
- Gradle : 2.1

#### ビルド手順

Gradleのbuildタスクを実施してください。

    gradle build



# License

MIT License
