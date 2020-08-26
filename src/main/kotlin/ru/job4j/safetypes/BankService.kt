package ru.job4j.safetypes

class BankService {
    private val users: HashMap<User, ArrayList<Account>> = HashMap()

    fun addUser(user: User) = users.putIfAbsent(user, ArrayList())

    fun findByRequisite(passport: String, requisite: String): Account? {
        val user = findByPassport(passport)
        return users[user]?.find { it.requisite == requisite }
    }

    fun addAccount(passport: String, account: Account) {
        val user = findByPassport(passport)
        users[user]?.add(account)
    }

    fun findByPassport(passport: String): User? = users.keys.find { it.passport == passport }


    fun transferMoney(srcPassport: String, srcRequisite: String, destPassport: String,
                      destRequisite: String, amount: Double): Boolean {
        val source = findByRequisite(srcPassport, srcRequisite)
        val dest = findByRequisite(destPassport, destRequisite)
        val rsl = source != null && dest != null
        if (rsl) {
            source?.apply { balance -= amount }
            dest?.apply { balance += amount }
        }
        return rsl
    }
}

fun main() {
    val bankService = BankService()
    bankService.apply {
        addUser(User("321", "Petr Arsentev"))
        var user = findByPassport("3211")
        println(user?.name)
        user = findByPassport("321")
        println(user?.name)
    }
}
