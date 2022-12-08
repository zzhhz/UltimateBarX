package com.zackratos.ultimatebarx.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import com.zackratos.ultimatebarx.sample.adjustresize.AdjustResizeActivity
import com.zackratos.ultimatebarx.sample.bottomnav.BottomNavActivity
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.e("", "nav bar：$navigationBarHeight")
        Log.e("", "status bar：$statusBarHeight")
        Log.e("", "：${Build.MODEL}, ${Build.BRAND}")
        statusBar {
            fitWindow = true
            colorRes = R.color.deepSkyBlue
        }
        navigationBar {
            fitWindow = true
            colorRes = R.color.deepSkyBlue
        }




        btnTransparent.setOnClickListener { start(TransparentActivity::class.java) }
        btnSwitch.setOnClickListener { start(SwitchActivity::class.java) }
        btnSwitch2.setOnClickListener { start(SwitchFragmentActivity::class.java) }
        btnViewPager.setOnClickListener { start(ViewPagerActivity::class.java) }
        btnViewPager2.setOnClickListener { start(ViewPagerActivity2::class.java) }
        btnScroll.setOnClickListener { start(ScrollActivity::class.java) }
        btnDrawer.setOnClickListener { start(DrawerActivity::class.java) }
        btnRecyclerFragment.setOnClickListener { start(RecyclerFragmentActivity::class.java) }
        btnFragmentStack.setOnClickListener { start(FragmentStackActivity::class.java) }
        btnPadding.setOnClickListener { start(AddPaddingActivity::class.java) }
        btnCoordinator.setOnClickListener { start(CoordinatorActivity::class.java) }
        btnBottonNav.setOnClickListener { start(BottomNavActivity::class.java) }
        btnAdjustResize.setOnClickListener { start(AdjustResizeActivity::class.java) }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            Log.e(
                "-----1",
                "${wm.currentWindowMetrics.bounds.left}, ${wm.currentWindowMetrics.bounds.top}, ${wm.currentWindowMetrics.bounds.right}, ${wm.currentWindowMetrics.bounds.bottom}, ${wm.currentWindowMetrics.bounds.width()},${wm.currentWindowMetrics.bounds.height()} "
            )
            Log.e("-----nav bar height", "$navigationBarHeight")
            Log.e("-----status bar height", "$statusBarHeight")
            val statusBars =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.statusBars())
            val navigationBars =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.navigationBars())
            val systemBars =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.systemBars())
            val displayCutout =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.displayCutout())
            val ime = wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.ime())
            val captionBar =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.captionBar())
            val systemGestures =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.systemGestures())
            val tappableElement =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.tappableElement())
            val mandatorySystemGestures =
                wm.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.mandatorySystemGestures())
            Log.e("--", "---2:${statusBars.bottom}, ${statusBars.toString()}")
            Log.e("--", "---3:${navigationBars.bottom}, ${navigationBars.toString()}")
            Log.e("--", "---4:${systemBars.bottom}, ${systemBars.toString()}")
            Log.e("--", "---5:${displayCutout.bottom}, ${displayCutout.toString()}")
            Log.e("--", "---6:${ime.bottom}, ${ime.toString()}")
            Log.e("--", "---7:${captionBar.bottom}, ${captionBar.toString()}")
            Log.e("--", "---8:${systemGestures.bottom}, ${systemGestures.toString()}")
            Log.e("--", "---9:${tappableElement.bottom}, ${tappableElement.toString()}")
            Log.e(
                "--",
                "---10:${mandatorySystemGestures.bottom}, ${mandatorySystemGestures.toString()}"
            )
        }


        hasNav()
        Utils.getDpi(this)
    }

    private fun start(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }


    private fun hasNav() {
        val resId = resources.getIdentifier("config_showNavigationBar", "bool", "android")

        if (resId != 0) {
            var hasNav = resources.getBoolean(resId)

            var barOverride = hasNavBarOverride()

            if ("0" == barOverride){
                hasNav = true
            } else if ("1" ==barOverride){
                hasNav = false
            }

            Log.e("---", "---11: $hasNav")
        }else{
            Log.e("---", "---11 resId: 0")
        }
    }

    private fun hasNavBarOverride(): String {
        val c = Class.forName("android.os.SystemProperties")
        val m = c.getDeclaredMethod("get", String::class.java)
        m.isAccessible = true

        return m.invoke(null, "qemu.hw.mainkeys") as String
    }
}
