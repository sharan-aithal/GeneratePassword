import kotlin.random.Random

fun main() {
    val p = Password()
    println(p.generate())
}

class Password(var length: Int = 12) {

    var minLength = 4
    private val SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz"
    private val CAPS_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val NUMBERS = "0123456789"
    private val SYMBOLS = "!@#$%^&*_+-=,.?"
    private val rand = Random(seed = System.currentTimeMillis())

    fun generate(): String {
        val str = SMALL_CHARS + CAPS_CHARS + NUMBERS + SYMBOLS
        val randomPass = getRandomChars(str, str.length)
        return listToString(getRandomChars(listToString(randomPass), length))
    }

    private fun getRandomChars(string: String, len: Int): MutableList<Char> {
        val charList = mutableListOf<Char>()
        for (i in 0 until len) {
            charList.add(string[rand.nextInt(string.length)])
        }
        return charList
    }

    private fun shuffle(list: MutableList<Char>) {
        for (i in 0 until list.size - 1) {
            val j = i + rand.nextInt(list.size - i)
            val tmp = list[i]
            list[i] = list[j]
            list[j] = tmp
        }
    }

    private fun listToString(l: MutableList<Char>): String {
        shuffle(l)
        var s = ""
        l.forEachIndexed { _, char -> s += char }
        return s
    }
}