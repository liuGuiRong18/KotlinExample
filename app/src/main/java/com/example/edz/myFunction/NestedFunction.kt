package com.example.edz.myFunction

import android.annotation.SuppressLint
import android.util.Log

/**
 * Created by liuguirong on 2018/8/30.
 */
/**
 * 嵌套函数
 */
class User(var name: String, var passWord: String)

@SuppressLint("LongLogTag")
fun nestedFunction(user: User) {
    @SuppressLint("LongLogTag")
    fun set(str: String) {
        Log.d("VariableActivity===nestedFunction", "$str ")
    }
    set(user.name)
    set(user.passWord)
}
//08-30 16:43:59.806 29678-29678/com.example.edz.myapplication D/VariableActivity===nestedFunction: xiaoming
//08-30 16:43:59.806 29678-29678/com.example.edz.myapplication D/VariableActivity===nestedFunction: 123456