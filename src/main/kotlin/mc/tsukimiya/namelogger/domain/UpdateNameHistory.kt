package mc.tsukimiya.namelogger.domain

import java.time.LocalDateTime
import java.util.*

/**
 * プレイヤー名の更新履歴
 */
internal class UpdateNameHistory(val uuid: UUID) {
    private val history = mutableMapOf<LocalDateTime, Name>()

    /**
     * ログを取得
     *
     * @return
     */
    fun getList(): SortedMap<LocalDateTime, Name> {
        return history.toSortedMap()
    }

    /**
     * ログを追加
     *
     * @param time
     * @param name
     */
    fun add(time: LocalDateTime, name: Name) {
        history[time] = name
    }
}
