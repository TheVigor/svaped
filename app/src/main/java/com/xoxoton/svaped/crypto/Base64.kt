package com.xoxoton.svaped.crypto

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream

object Base64 {
    private val LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray()

    private fun decode(paramChar: Char): Int {
        if (paramChar in 'A'..'Z')
            return paramChar - 'A'
        if (paramChar in 'a'..'z')
            return paramChar - 'a' + '\u001a'.toInt()
        if (paramChar in '0'..'9')
            return paramChar - '0' + '\u001a'.toInt() + '\u001a'.toInt()
        if (paramChar != '+') {
            if (paramChar != '/') {
                if (paramChar == '=')
                    return 0
                val stringBuilder = StringBuilder()
                stringBuilder.append("unexpected code: ")
                stringBuilder.append(paramChar)
                throw RuntimeException(stringBuilder.toString())
            }
            return 63
        }
        return 62
    }

    @Throws(IOException::class)
    private fun decode(paramString: String, paramOutputStream: OutputStream) {
        val i = paramString.length
        var b: Byte = 0
        while (true) {
            if (b < i && paramString[b.toInt()] <= ' ') {
                b++
                continue
            }
            if (b.toInt() != i) {
                val j = decode(paramString[b.toInt()])
                var k = decode(paramString[b + 1])
                val b1 = (b + 2).toByte()
                val m = decode(paramString[b1.toInt()])
                val b2 = (b + 3).toByte()
                k = (j shl 18) + (k shl 12) + (m shl 6) + decode(paramString[b2.toInt()])
                paramOutputStream.write(k shr 16 and 0xFF)
                if (paramString[b1.toInt()] != '=') {
                    paramOutputStream.write(k shr 8 and 0xFF)
                    if (paramString[b2.toInt()] != '=') {
                        paramOutputStream.write(k and 0xFF)
                        b = (b + 4).toByte()
                        continue
                    }
                }
            }
            return
        }
    }

    fun decode(paramString: String): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        try {
            decode(paramString, byteArrayOutputStream)
            val arrayOfByte = byteArrayOutputStream.toByteArray()
            try {
                byteArrayOutputStream.close()
            } catch (iOException: IOException) {
                val printStream = System.err
                val stringBuilder = StringBuilder()
                stringBuilder.append("Error while decoding BASE64: ")
                stringBuilder.append(iOException.toString())
                printStream.println(stringBuilder.toString())
            }

            return arrayOfByte
        } catch (paramString: IOException) {
            throw RuntimeException()
        }

    }

    fun encode(paramArrayOfByte: ByteArray): String {
        val i = paramArrayOfByte.size
        val stringBuffer = StringBuffer(paramArrayOfByte.size * 3 / 2)
        var bool = 0
        var j: Int
        j = 0
        val ffs = 0xFF
        while (bool <= (i - 3)) {
            val b =
                (paramArrayOfByte[bool].toInt() and ffs shl 16 or (paramArrayOfByte[bool + 1].toInt() and ffs shl 8) or (paramArrayOfByte[bool + 2].toInt() and ffs)).toByte()
            stringBuffer.append(LEGAL_CHARS[b.toInt() shr 18 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[b.toInt() shr 12 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[b.toInt() shr 6 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[b.toInt() and 0x3F])
            bool += 1
            if (j >= 14) {
                stringBuffer.append(" ")
                j = 0
                j++
                continue
            }
            j++
        }
        j = 0 + i
        if (bool == j - 2) {
            j = paramArrayOfByte[bool].toInt()
            j = paramArrayOfByte[bool + 1].toInt() and ffs shl 8 or (j and ffs shl 16)
            stringBuffer.append(LEGAL_CHARS[j shr 18 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[j shr 12 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[j shr 6 and 0x3F])
            stringBuffer.append("=")
        } else if (bool == j - 1) {
            j = paramArrayOfByte[bool].toInt() and ffs shl 16
            stringBuffer.append(LEGAL_CHARS[j shr 18 and 0x3F])
            stringBuffer.append(LEGAL_CHARS[j shr 12 and 0x3F])
            stringBuffer.append("==")
        }
        return stringBuffer.toString()
    }
}
