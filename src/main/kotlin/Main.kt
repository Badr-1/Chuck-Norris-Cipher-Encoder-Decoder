const val BIN_LENGTH = 7
const val PAD_CHAR = '0'
fun main(args: Array<String>) {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        when (val option = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                println("Bye!")
                break
            }

            else -> println("There is no '$option' operation")
        }
    }
}
fun decode(){
    println("Input encoded string:")
    val encoded = readln().split(" ").toMutableList()
    if (encoded.size % 2 != 0) { // checks if The number of blocks is odd;
        println("Encoded string is not valid.")
        return
    }
    var binary = ""
    encoded.forEachIndexed { index, s ->
        if (index % 2 == 0 && index != encoded.lastIndex) {
            if (s != "0" && s != "00") { // checks if The first block of each sequence is not 0 or 00;
                println("Encoded string is not valid.")
                return
            }
            val bit = if (encoded[index] == "0") "1" else "0"
            if (!encoded[index + 1].matches(Regex("0+"))) { // checks if The encoded message includes characters other than 0 or spaces;
                println("Encoded string is not valid.")
                return
            }
            val num = encoded[index + 1].length
            binary += bit.repeat(num)
        }
    }
    if (binary.length % 7 != 0) // checks if The length of the decoded binary string is not a multiple of 7.
    {
        println("Encoded string is not valid.")
        return
    }
    val charsBin = binary.chunked(BIN_LENGTH).toMutableList()
    val chars = mutableListOf<Char>()
    charsBin.forEach { chars.add(Char(it.toInt(2))) }
    println("Decoded string:")
    println(chars.joinToString(""))
}

fun encode(){
    println("Input string:")
    val message = readln()
    val chars = message.toMutableList()
    val binaries = chars.map { Integer.toBinaryString(it.code).padStart(BIN_LENGTH, PAD_CHAR) }
    val joinedBinaries = binaries.joinToString(separator = "")
    val encryptedBinary = mutableListOf<String>()
    var binary = joinedBinaries
    while (binary.isNotEmpty()) {
        val bin = binary.substringBefore(if (binary[0] == '0') '1' else '0')
        binary = binary.substringAfter(bin)
        // encrypt
        encryptedBinary.add(if (bin[0] == '0') "00" else "0")
        encryptedBinary.add("0".repeat(bin.length))
    }

    println("Encoded string:")
    encryptedBinary.forEach {
        print("$it ")
    }
    println()
}