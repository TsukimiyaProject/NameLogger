# NameLogger
UUID ⇒ 名前のマッピングプラグイン  
サーバー入室時にプレイヤー名が保存される。  
v2.0で変更履歴実装予定

## DB
### accounts テーブル
| カラム名 | 型 | 初期値 | Null許容 | インデックス | 内容 |
| --- | --- | --- | --- | --- | --- |
| id | BINARY(16) | | × | PRIMARY | プレイヤーのUUID |
| name | VARCHAR(16) | | × | UNIQUE | プレイヤー名 |
