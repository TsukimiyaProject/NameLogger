package mc.tsukimiya.namelogger

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mc.tsukimiya.namelogger.infrastructure.dao.Accounts
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import java.util.*
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class APITest {
    /**
     * 普通の挿入、取得ができるか
     */
    @Test
    fun test1() {
        val map = mutableMapOf<UUID, String>()
        for (i in 1..10) {
            val uuid = UUID.randomUUID()
            val name = getRandomString(Random.nextInt(from = 1, until = 16))
            NameLogger.api.updateAccountName(uuid, name)
            map[uuid] = name
        }

        map.forEach { (key, value) ->
            assert(NameLogger.api.getCurrentName(key) == value)
            assert(NameLogger.api.getIDByName(value) == key)
        }
    }

    /**
     * 更新した場合に正しい値が取得できるか
     */
    @Test
    fun test2() {
        val uuid = UUID.randomUUID()

        var name = getRandomString(Random.nextInt(from = 1, until = 16))
        NameLogger.api.updateAccountName(uuid, name)
        assert(NameLogger.api.getCurrentName(uuid) == name)

        name = getRandomString(Random.nextInt(from = 1, until = 16))
        NameLogger.api.updateAccountName(uuid, name)
        assert(NameLogger.api.getCurrentName(uuid) == name)
    }

    /**
     * 16文字は保存できて17文字以上は保存できないか
     */
    @Test
    fun test3() {
        // 16文字
        val uuid1 = UUID.randomUUID()
        val name1 = getRandomString(16)
        assertDoesNotThrow { NameLogger.api.updateAccountName(uuid1, name1) }

        // 17文字
        val uuid2 = UUID.randomUUID()
        val name2 = getRandomString(17)
        assertThrows<IllegalArgumentException> { NameLogger.api.updateAccountName(uuid2, name2) }

        // 17文字以上(メモリ問題のため1000文字まで)
        val uuid3 = UUID.randomUUID()
        val name3 = getRandomString(Random.nextInt(from = 17, until = 1000))
        assertThrows<IllegalArgumentException> { NameLogger.api.updateAccountName(uuid3, name3) }
    }

    @BeforeAll
    fun databaseSetUp() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:sqlite:file:test?mode=memory&cache=shared"
            driverClassName = "org.sqlite.JDBC"
            transactionIsolation = "TRANSACTION_SERIALIZABLE"
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(Accounts)
        }
    }

    private fun getRandomString(length: Int): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}
