package com.example.edz.myFunction

import android.annotation.SuppressLint
import android.util.Log


/**
 * Created by liuguirong on 2018/8/30.
 */
/**
 * 顶级函数 方法可以直接定义在一个包里面，独立于一个类存在
 */
@SuppressLint("LongLogTag")
fun topFunction(str:String) {
    Log.d("VariableActivity===顶级函数varArgFun", "$str ")
}
//08-30 16:43:59.805 29678-29678/com.example.edz.myapplication D/VariableActivity===顶级函数varArgFun: 来自顶层函数的调用