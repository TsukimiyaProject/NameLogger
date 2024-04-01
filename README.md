# NameLogger v0.1.0
UUID ⇒ 名前のマッピングプラグイン  
サーバー入室時にプレイヤー名が保存される。  
v2.0で変更履歴実装予定

## Gradle
### Kotlin DSL
```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/TsukimiyaProject/Lib4B")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    compileOnly("mc.tsukimiya:name-logger:0.1.0")
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
| カラム名 | 型 | 初期値 | Null許容 | インデックス | 内容 |
| --- | --- | --- | --- | --- | --- |
| id | BINARY(16) | | × | PRIMARY | プレイヤーのUUID |
| name | VARCHAR(16) | | × | UNIQUE | プレイヤー名 |
