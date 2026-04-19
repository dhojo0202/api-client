# api-client

daily-dashboard の REST API を呼び出すクライアントアプリです。

## 概要

[daily-dashboard](https://github.com/dhojo0202/daily-dashboard) が提供する API に対して、別システムからリクエストを送り、タスクの取得・追加・完了・削除を行います。

## システム構成

```
ブラウザ → api-client（port:8081） → daily-dashboard API（port:8080） → DB
```

## 機能一覧

- タスク一覧の取得・表示
- タスクの追加
- タスクの完了切り替え
- タスクの削除

## 動作環境

| ソフトウェア | バージョン |
|---|---|
| Java | 25 |
| Maven | 3.9以上 |

## 事前準備

daily-dashboard が起動していることが必要です。

```
http://localhost:8080
```

## セットアップ

### 1. リポジトリをクローン

```bash
git clone https://github.com/dhojo0202/api-client.git
cd api-client
```

### 2. ビルド

```bash
mvn package -DskipTests
```

### 3. 起動

```bash
mvn spring-boot:run
```

ブラウザで `http://localhost:8081` を開く

## 技術構成

| 種別 | 技術 |
|---|---|
| バックエンド | Java 25 / Spring Boot 3.4.5 |
| フロントエンド | Thymeleaf / HTML / CSS |
| HTTP クライアント | Apache HttpClient 5 |
| ビルドツール | Maven |

## APIテスト（Bruno）

`daily-dashboard-api/` フォルダに Bruno のコレクションが含まれています。

### セットアップ

1. [Bruno](https://www.usebruno.com/) をインストール
2. Bruno で `daily-dashboard-api/` フォルダを開く
3. Environment `local` を選択
4. `ログイン` リクエストを送るとトークンが自動でセットされる

### エンドポイント一覧

| リクエスト名 | Method | URL |
|---|---|---|
| ログイン | POST | `/auth/login` |
| タスク一覧取得 | GET | `/api/tasks` |
| タスク追加 | POST | `/api/tasks` |

### 認証情報（開発用）

```
username: admin
password: password
```
