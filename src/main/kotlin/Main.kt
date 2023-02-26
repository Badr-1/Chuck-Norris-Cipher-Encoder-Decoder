const val BIN_LENGTH = 7
const val PAD_CHAR = '0'
fun main(args: Array<String>) {

}
fun decode(){
    println("Input encoded string:")
    val encoded = readln().split(" ").toMutableList()
    var binary = ""
    encoded.forEachIndexed { index, s ->
        if (index % 2 == 0 && index != encoded.lastIndex) {
            val bit = if (encoded[index] == "0") "1" else "0"
            val num = encoded[index + 1].length
            binary += bit.repeat(num)
        }
    }
    val charsBin = binary.chunked(BIN_LENGTH).toMutableList()
    val chars = mutableListOf<Char>()
    charsBin.forEach { chars.add(Char(it.toInt(2))) }
    println("The result:")
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

    println("The result:")
    encryptedBinary.forEach {
        print("$it ")
    }
}