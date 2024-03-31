package com.github.tvbox.osc.util

/**
 *
 * @author: 99154
 * @description 带参单例封装（请注意，Context需要重新赋值为applicationContext）
 * @date: Create in 14:35 2022/6/21
 * https://www.jianshu.com/p/2d0f285f6e4b
 * https://www.jianshu.com/p/3fc4bd25fdb2
 */
open class SingletonHolder<out O, in I>(creator: (I) -> O) {

    private var creator: ((I) -> O)? = creator

    @Volatile
    private var instance: O? = null

    fun getInstance(input: I): O = instance ?: synchronized(this) {
        instance ?: creator!!(input).apply {
            instance = this
        }
    }
}