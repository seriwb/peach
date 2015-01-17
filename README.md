# peach

Twitter上の特定の発言に反応し、あらかじめ指定された文言をリプライで返すTwitter botプログラムです。

デフォルト設定では「痩せよう」というキーワードに反応し、
発言者に対して「そのままがいいよ」というリプライを返します。

### ※注意

本プログラムを動作させるTwitterアカウントのアイコンはイケメンにしましょう！  
また、通常使っているTwitterアカウントでこのツールを起動した場合・・・
どうなるかは貴方のイケメン度次第です！ご利用は計画的に！


## 使い方

1. [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)をインストール。
2. [releases](https://github.com/seriwb/peach/releases)からzipファイルを取得し、適当な場所に展開。
3. 必要に応じてconf/Config.groovyの値を調整。
4. コンソールからpeach.jarがある場所まで移動し、以下のコマンドを実行。
```
java -jar peach.jar
```

終了する場合はコンソール上でCtrl+Cを入力してください。


### 動作環境

- Javaが動作するOS（Windows、Mac、Linux等）
- JRE : 1.7 以上



# プログラムガイド

## ビルド

#### ビルド環境

- JDK : 1.8.0_25
- Groovy : 2.3.3
- Gradle : 2.1

#### ビルド手順

Gradleのbuildタスクを実施してください。

    gradle build


## プログラムの実行

以下のクラスに含まれるmainメソッドを実行してください。

    /peach/src/main/groovy/white/box/peach/Main.groovy



# License

MIT License
