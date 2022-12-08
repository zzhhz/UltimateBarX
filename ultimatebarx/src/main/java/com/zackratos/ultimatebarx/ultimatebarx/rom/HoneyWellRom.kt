package com.zackratos.ultimatebarx.ultimatebarx.rom

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

/**
 * Created by ZZH on 2022/11/18
 *
 * @Date: 2022/11/18 15:30
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 霍尼韦尔设备
 */
internal class HoneyWellRom : BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(context: Context): Boolean = false

    override fun navigationBarExist(context: Context): Boolean {
        //Log.d("","--------------navigationBarExist---: ${Settings.System.getInt(context.contentResolver, "change_nvbar_status", 0)}")
        return Settings.System.getInt(context.contentResolver, "change_nvbar_status", 0) == 1
    }
}