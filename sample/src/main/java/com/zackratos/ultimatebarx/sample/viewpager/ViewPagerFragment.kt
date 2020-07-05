package com.zackratos.ultimatebarx.sample.viewpager

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.sample.R
import com.zackratos.ultimatebarx.sample.extension.getStatusBarHeight
import kotlinx.android.synthetic.main.fragment_view_pager.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  4:50 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    companion object {

        private const val COLOR = "color"
        private const val TITLE = "title"
        private const val TITLE_COLOR = "title_color"

        fun newInstance(@ColorInt color: Int, title: String, @ColorInt titleColor: Int) = ViewPagerFragment()
            .apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putInt(COLOR, color)
                    putInt(TITLE_COLOR, titleColor)
                }
            }
    }

    private val title: String by lazy { arguments?.getString(TITLE) ?: "" }
    private val color: Int by lazy { arguments?.getInt(COLOR) ?: Color.TRANSPARENT }
    private val titleColor: Int by lazy { arguments?.getInt(TITLE_COLOR) ?: Color.TRANSPARENT }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStatus.post {
            viewStatus.layoutParams = viewStatus.layoutParams.apply { height = activity?.getStatusBarHeight() ?: 0 }
        }
        toolbar.setBackgroundColor(color)
        toolbar.title = title
        toolbar.setTitleTextColor(titleColor)
        viewStatus.setBackgroundColor(color)
    }

}