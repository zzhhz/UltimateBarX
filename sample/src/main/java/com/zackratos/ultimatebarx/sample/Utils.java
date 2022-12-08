package com.zackratos.ultimatebarx.sample;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by ZZH on 2022/12/8.
 *
 * @Date: 2022/12/8
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class Utils {

    public static int getDpi(Activity context) {
        int dpi = 0;
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }

        int height = context.getWindowManager().getDefaultDisplay().getHeight();
        Log.e("-----", "-----真实屏幕高度：" + dpi + ", 去掉虚拟导航键：" + height);

        return dpi;
    }

}
