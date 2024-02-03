package mc.tsukimiya.namelogger.domain

import java.util.*

interface AccountRepository {
    /**
     * アカウント取得
     * @param uuid
     */
    fun find(uuid: UUID): Account?

    /**
     * 名前からアカウント取得
     * @param name
     */
    fun findByName(name: Name): Account?

    /**
     * アカウントを保存
     * @param account
     */
    fun store(account: Account)
}