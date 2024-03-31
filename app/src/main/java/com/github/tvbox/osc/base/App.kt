package com.github.tvbox.osc.base

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.github.catvod.crawler.JsLoader
import com.github.tvbox.osc.bean.VodInfo
import com.github.tvbox.osc.callback.EmptyCallback
import com.github.tvbox.osc.callback.LoadingCallback
import com.github.tvbox.osc.data.AppDataManager
import com.github.tvbox.osc.server.ControlManager
import com.github.tvbox.osc.util.AppManager
import com.github.tvbox.osc.util.CrashHandler
import com.github.tvbox.osc.util.EpgUtil
import com.github.tvbox.osc.util.FileUtils
import com.github.tvbox.osc.util.HawkConfig
import com.github.tvbox.osc.util.LOG
import com.github.tvbox.osc.util.OkGoHelper
import com.github.tvbox.osc.util.PlayerHelper
import com.kingja.loadsir.core.LoadSir
import com.orhanobut.hawk.Hawk
import com.p2p.P2PClass
import com.whl.quickjs.android.QuickJSLoader
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 * @author pj567
 * @date :2020/12/17
 * @description:
 */
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        initParams()
        // OKGo
        OkGoHelper.init() //台标获取
        EpgUtil.init()
        // 初始化Web服务器
        ControlManager.init(this)
        //初始化数据库
        AppDataManager.init()
        LoadSir.beginBuilder()
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .commit()
        AutoSizeConfig.getInstance().setCustomFragment(true).unitsManager
            .setSupportDP(false)
            .setSupportSP(false)
            .setSupportSubunits(Subunits.MM)
        PlayerHelper.init()
        QuickJSLoader.init()
        FileUtils.cleanPlayerCache()

        CrashHandler.getInstance(this)
    }

    private fun initParams() {
        // Hawk
        Hawk.init(this).build()
        Hawk.put(HawkConfig.DEBUG_OPEN, false)
        if (!Hawk.contains(HawkConfig.PLAY_TYPE)) {
            Hawk.put(HawkConfig.PLAY_TYPE, 1)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        JsLoader.load()
    }

    @JvmField
    var vodInfo: VodInfo? = null
    val currentActivity: Activity
        get() = AppManager.getInstance().currentActivity()

    @JvmField
    var dashData: String? = null

    companion object {
        @JvmStatic
        var instance: App? = null
            private set
        private var p: P2PClass? = null

        @JvmField
        var burl: String? = null

        @JvmStatic
        fun getp2p(): P2PClass? {
            return try {
                if (p == null) {
                    p = P2PClass(
                        instance!!.externalCacheDir!!.absolutePath
                    )
                }
                p
            } catch (e: Exception) {
                LOG.e(e.toString())
                null
            }
        }
    }
}
