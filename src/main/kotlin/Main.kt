const val BIN_LENGTH = 7
const val PAD_CHAR = '0'
fun main(args: Array<String>) {
    println("Input string:")
    val message = readln()
    val chars = message.toMutableList()
    val binaries = chars.map { Integer.toBinaryString(it.code) }
    println("The result:")
    binaries.forEach {
        println(
            "${chars[binaries.indexOf(it)]} = ${it.padStart(BIN_LENGTH, PAD_CHAR)}"
        )
    }
}