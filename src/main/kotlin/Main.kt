fun main(args: Array<String>) {
    println("Input string:")
    val message = readln()
    val chars = message.toMutableList()
    chars.forEach { char: Char -> print("$char ") }
}