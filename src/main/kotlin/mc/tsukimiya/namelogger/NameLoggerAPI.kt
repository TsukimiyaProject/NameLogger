package mc.tsukimiya.namelogger

import java.time.LocalDateTime
import java.util.*

interface NameLoggerAPI {
    /**
     * 現在のプレイヤー名を取得
     *
     * @param uuid
     * @return
     */
    fun getCurrentName(uuid: UUID): String?

    /**
     * 名前からUUIDを取得
     *
     * @param name
     * @return
     */
    fun getIDByName(name: String): UUID?

    /**
     * 過去のプレイヤー名を取得
     *
     * @param uuid
     * @return
     */
    fun getOldNames(uuid: UUID): Map<LocalDateTime, String>?

    /**
     * アカウント名を更新
     *
     * @param uuid プレイヤーUUID
     * @param name プレイヤー名
     */
    fun updateAccountName(uuid: UUID, name: String)
}
