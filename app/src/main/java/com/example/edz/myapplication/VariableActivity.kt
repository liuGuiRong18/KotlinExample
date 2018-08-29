package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


import kotlinx.android.synthetic.main.activity_variable.*

/***
 * 1.定义变量
 * 2.定义函数
 * 3.字符串模板
 *
 */
class VariableActivity : AppCompatActivity() {

    /**
     * kotlin变量的声明方式与Java中声明变量有很大的区别，而且必须使用var或val关键字。其中：
     * var: 用此关键字声明的变量表示可变变量，即可读且可写。相当于Java中普通变量
     * val: 用此关键字声明的变量表示不可变变量，即可读且不可写。相当于Java中用final修饰的变量
     */
    /**
     * 定义格式： 关键字 变量名: 数据类型 = xxx
     */
    //立即初始化
    var a: Int = 10
    // ⾃动推断出 `Int` 类型
    var b = "100"
    var c = true
    var v: Float = 0f
    val val_a: Int = 100
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variable)
        setSupportActionBar(toolbar)
        Log.d("VariableActivity", "" + a)
        Log.d("VariableActivity", b)
        Log.d("VariableActivity", "" + c)

        Log.d("VariableActivity==函数sum", sum(10, 30).toString())
        Log.d("VariableActivity===函数sumNoType", sumNoType(100, 30).toString())
        Log.d("VariableActivity===函数sumNoType", sumNoType(true).toString())
        Log.d("VariableActivity===函数sumNoType", sumNoType("字符串").toString())
        v = 10f
        sunVoid("方法无返回值")
        sunNoVoid("方法无返回值")
        StringType("字符串模板")
    }

    /**
     * 函数 带有两个 Int 参数、返回 Int 的函数：
     */
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /***
     * 将表达式作为函数体、返回值类型⾃动推断的函数
     */
    private fun sumNoType(a: Int, b: Int) = a + b
    private fun sumNoType(a: Boolean) = a
    private fun sumNoType(a: String) = a

    /***
     * 无返回值 void
     */
    @SuppressLint("LongLogTag")
    private fun sunVoid(str: String): Unit {
        Log.d("VariableActivity===sunVoid", "sunVoid 值 $str")
    }

    /***
     * Unit 返回类型可以省略：
     */
    @SuppressLint("LongLogTag")
    private fun sunNoVoid(str: String) {
        Log.d("VariableActivity===sunNoVoid", "sunNoVoid 值 $str")
    }
    /***
     * 字符串模板
     */
    @SuppressLint("LongLogTag")
    private  fun StringType(str: String){
        Log.d("VariableActivity===sunNoVoid", "StringType 值 $str")
        Log.d("VariableActivity===sunNoVoid", "StringType 值 ${str.length}")
        Log.d("VariableActivity===sunNoVoid", "StringType 值 ${"$"}9.9")
    }
}
