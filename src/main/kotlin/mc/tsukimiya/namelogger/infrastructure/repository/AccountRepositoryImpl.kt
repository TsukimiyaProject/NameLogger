package mc.tsukimiya.namelogger.infrastructure.repository

import mc.tsukimiya.namelogger.domain.Account
import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.domain.Name
import mc.tsukimiya.namelogger.infrastructure.dao.Accounts
import org.jetbrains.exposed.sql.lowerCase
import java.time.LocalDateTime
import java.util.*
import mc.tsukimiya.namelogger.infrastructure.dao.Account as AccountDAO

internal class AccountRepositoryImpl : AccountRepository {
    override fun find(uuid: UUID): Account? {
        val dao = AccountDAO.findById(uuid) ?: return null
        return Account(dao.id.value, Name(dao.name))
    }

    override fun findByName(name: Name): Account? {
        val dao = AccountDAO.find { Accounts.name.lowerCase() eq name.value.lowercase() }.firstOrNull() ?: return null
        return Account(dao.id.value, Name(dao.name))
    }

    override fun store(account: Account) {
        // アカウントを取得
        val dao = AccountDAO.findById(account.id)

        // アカウントが存在する場合
        if (dao != null) {
            // update
            dao.name = account.name.value
            dao.updatedAt = LocalDateTime.now()
        } else {
            // insert
            AccountDAO.new(account.id) {
                name = account.name.value
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }
        }
    }
}
