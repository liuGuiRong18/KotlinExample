package com.example.edz.myFunction

import android.annotation.SuppressLint
import android.util.Log

/**
 * Created by liuguirong on 2018/8/30.
 */
/**
 * 中缀函数
 * 中缀函数调用的优先级低于算术操作符、类型转换以及 rangeTo 操作符。
 * 以下表达式是等价的： 1 shl 2 + 3 与 1 shl (2 + 3)
 * 0 until n * 2 与 0 until (n * 2)
 * xs union ys as Set<*> 与 xs union (ys as Set<*>)
 * 另⼀⽅⾯，中缀函数调⽤的优先级⾼于布尔操作符 && 与 ||、 is- 与 in- 检测以及其他⼀些操作符。
 * 这些表达式也是等价的： a && b xor c 与 a && (b xor c)
 * a xor b in c 与 (a xor b) in c 完整的优先级层次结构请参⻅其语法参考。
 */
class FixFunction {
    //成员方法
    @SuppressLint("LongLogTag") infix fun fixOne(string: String) {
        Log.d("VariableActivity=== 中缀函数 fixOne", "$string ")

    }
}

//扩展方法
@SuppressLint("LongLogTag") infix fun FixFunction.fixTwo(string: String) {
    Log.d("VariableActivity=== 中缀函数 扩展方法", "$string ")
    //这里面可以扩展很多的方法
}
//08-30 16:43:59.806 29678-29678/com.example.edz.myapplication D/VariableActivity=== 中缀函数 fixOne: 中缀函数==成员函数
//08-30 16:43:59.806 29678-29678/com.example.edz.myapplication D/VariableActivity=== 中缀函数 fixOne: 中缀函数==成员函数
//08-30 16:43:59.806 29678-29678/com.example.edz.myapplication D/VariableActivity=== 中缀函数 扩展方法: 中缀函数==成员函数