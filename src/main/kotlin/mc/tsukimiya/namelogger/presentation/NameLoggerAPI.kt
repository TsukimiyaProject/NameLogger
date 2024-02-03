package mc.tsukimiya.namelogger.presentation

import java.time.LocalDateTime
import java.util.UUID

interface NameLoggerAPI {
    /**
     * 現在のプレイヤー名を取得
     *
     * @param uuid
     * @return
     */
    fun getCurrentName(uuid: UUID): String

    /**
     * 過去のプレイヤー名を取得
     *
     * @param uuid
     * @return
     */
    fun getOldNames(uuid: UUID): Map<LocalDateTime, String>
}