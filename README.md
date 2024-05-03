# NameLogger v1.1.0
UUID ⇒ 名前のマッピングプラグイン  
サーバー入室時にプレイヤー名が保存される。  
v2.0で変更履歴実装予定

## Gradle
### Kotlin DSL
```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/TsukimiyaProject/NameLogger")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    compileOnly("mc.tsukimiya:name-logger:1.1.0")
}
```

## API
### import
```kotlin
import mc.tsukimiya.namelogger.NameLogger
```

### UUIDからプレイヤー名取得
```kotlin
val uuid: UUID
val name = NameLogger.api.getCurrentName(uuid)
```

### 名前からUUID取得
```kotlin
val name: String
val uuid = NameLogger.api.getIDByName(name)
```

### プレイヤー名更新
```kotlin
val uuid: UUID
val name: String
NameLogger.api.updateAccountName(uuid, name)
```

## DB
### accounts テーブル
| カラム名 | データ型 | 主キー | 非Null | 一意 | 自動増加 | デフォルト値 | 内容 |
| --- | --- | :---: | :---: | :---: | :---: | --- | --- |
| id | BINARY(16) | ○ | ○ | × | × | | プレイヤーのUUID |
| name | VARCHAR(16) | × | ○ | ○ | × | | プレイヤー名 |
| created_at | DATETIME | × | ○ | × | × | | レコード作成日 |
| updated_at | DATETIME | × | ○ | × | × | | レコード更新日 |
