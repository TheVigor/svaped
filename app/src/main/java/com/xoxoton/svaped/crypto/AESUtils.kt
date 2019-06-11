package com.xoxoton.svaped.crypto

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESUtil {
    val BM = "utf-8"
    val PHONE_KEY = "388E9E7574D8A2B3"
    val REGISTER_KEY = "a2d6e65fd5472650"
    val PADDING_CONFIG = "AES/CBC/PKCS5Padding"
    val VIPARA = "68C451BFF1BBCDE3".toByteArray()
    val AES_ALGORYTHM_NAME = "AES"

    fun aesDecrypt(paramString1: String, paramString2: String): String? {
        try {
            val arrayOfByte = Base64.decode(paramString1)
            val ivParameterSpec = IvParameterSpec(VIPARA)
            val secretKeySpec = SecretKeySpec(paramString2.toByteArray(), AES_ALGORYTHM_NAME)

            val cipher = Cipher.getInstance(PADDING_CONFIG)
            cipher.init(2, secretKeySpec, ivParameterSpec)
            return String(cipher.doFinal(arrayOfByte), Charset.forName(BM))
        } catch (paramString1: NoSuchAlgorithmException) {

        } catch (paramString1: NoSuchPaddingException) {

        } catch (paramString1: InvalidKeyException) {

        } catch (paramString1: IllegalBlockSizeException) {

        } catch (paramString1: BadPaddingException) {

        } catch (paramString1: InvalidAlgorithmParameterException) {

        } catch (paramString1: UnsupportedEncodingException) {
        }

        return null
    }

    fun aesEncrypt(paramString1: String, paramString2: String): String? {
        try {
            val ivParameterSpec = IvParameterSpec(VIPARA)
            val secretKeySpec = SecretKeySpec(paramString2.toByteArray(), AES_ALGORYTHM_NAME)
            val cipher = Cipher.getInstance(PADDING_CONFIG)
            cipher.init(1, secretKeySpec, ivParameterSpec)
            return Base64.encode(cipher.doFinal(paramString1.toByteArray(charset(BM))))
        } catch (paramString1: NoSuchAlgorithmException) {

        } catch (paramString1: NoSuchPaddingException) {

        } catch (paramString1: InvalidKeyException) {

        } catch (paramString1: UnsupportedEncodingException) {

        } catch (paramString1: BadPaddingException) {

        } catch (paramString1: IllegalBlockSizeException) {

        } catch (paramString1: InvalidAlgorithmParameterException) {
        }

        return null
    }

    private fun str2ByteArray(paramString: String): ByteArray {
        val i = paramString.length / 2
        val arrayOfByte = ByteArray(i)
        for (b in 0 until i) {
            val b1 = (b * 2).toByte()
            arrayOfByte[b] = Integer.valueOf(paramString.substring(b1.toInt(), b1 + 2), 16).toInt().toByte()
        }
        return arrayOfByte
    }
}
