package com.zackratos.ultimatebarx.ultimatebarx.extension

import android.os.Build
import android.text.TextUtils
import com.zackratos.ultimatebarx.ultimatebarx.rom.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:00 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

internal fun getRom(): Rom {
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_MIUI)))
        return MiuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_EMUI)))
        return EmuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_VIVO))) {
        return FuntouchRom()
    } else if(TextUtils.equals("honeywell", Build.BRAND.lowercase())){
        return HoneyWellRom()
    }
    return OtherRom()
}

private fun getProp(name: String): String? {
    val line: String?
    var input: BufferedReader? = null
    try {
        val p = Runtime.getRuntime().exec("getprop $name")
        input = BufferedReader(InputStreamReader(p.inputStream), 1024)
        line = input.readLine()
        input.close()
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return line
}