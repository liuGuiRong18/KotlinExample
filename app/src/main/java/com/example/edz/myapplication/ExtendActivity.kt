package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_extend.*

/*****
 * 1.拓展函数&拓展属性
 * 2.委托类
 */
class ExtendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extend)
        setSupportActionBar(toolbar)
        extendFun()
    }

    /***
     * 扩展函数,扩展成员变量
     * 定义：Kotlin的标准库给我们提供了大量的类，类里面提供了大量的方法。但是，实际开发过程中，具体的某一个类，仅仅通过系统提供的方法远远不能满足需求。
     * 使用Java开发的时候，我们可以通过继承类或者通过代理模式，可以对某一个类做增强。使用Kotlin开发，这一切变的简单。
     * Kotlin允许拓展方法，只需要按照给定的语法格式，可以很方便的对某一个类添加方法，和变量。
     */
    //扩展成员变量 必须用val声明   扩展快捷输入方式 exv和exf
    val String.extendVariable: String
        get() = this.substring(this.length - 1)


    @SuppressLint("LongLogTag") private fun extendFun() {
        //扩展方法的调用
        AnyClass().extendName("")
        Log.d("ExtendActivity。 extendFun ", "扩展函数".getLastChar())
//        09-04 16:14:20.796 663-663/com.example.edz.myapplication D/ExtendActivity。 extendFun: 数
//         拓展函数使用注意    java里面，父类和子类有相同方法的时候，子类方法优先。这是多态的表现，
//         Kotlin的拓展函数则不存在子类优先的原则。Kotlin的拓展函数是静态解析的，完全由当前变量的类型决定
        val button = Button()
        val button1: View = Button()
        val button2: Button = Button()//button 可以省略
        Log.d("ExtendActivity。 extendFun button ", button.PrintInfo())
        Log.d("ExtendActivity。 extendFun button1 ", button1.PrintInfo())
        Log.d("ExtendActivity。 extendFun button2 ", button2.PrintInfo())
//        09-04 16:15:19.651 2606-2606/com.example.edz.myapplication D/ExtendActivity。 extendFun button: 我是Button的扩展方法方法
//        09-04 16:15:19.651 2606-2606/com.example.edz.myapplication D/ExtendActivity。 extendFun button1: 我是View的扩展方法方法
//        09-04 16:15:19.651 2606-2606/com.example.edz.myapplication D/ExtendActivity。 extendFun button2: 我是Button的扩展方法方法

//          kotlin里面，类本身的成员函数和拓展函数同名会怎样呢？Kotlin的拓展函数针对成员函数和拓展函数同名问题，遵循成员函数优先的原则
        val simpleName = simpleName()
        Log.d("ExtendActivity。 extendFun simpleName ", simpleName.PrintInfo())
//        09-04 16:23:38.522 10104-10104/com.example.edz.myapplication D/ExtendActivity。 extendFun simpleName: 我是simpleName的成员方法


//        扩展变量调用方式
        var string = "扩展变量"
        Log.d("ExtendActivity。 extendFun extendVariable ", string.extendVariable)
//09-04 16:44:38.408 28148-28148/com.example.edz.myapplication D/ExtendActivity。 extendFun extendVariable: 量
    }


    /***
     * 扩展函数写法
     */
    class AnyClass

    fun AnyClass.extendName(params: String) {}
    private fun String.getLastChar(): String {
        return this@getLastChar.substring(this.length - 1)
    }

    open class View
    class Button : View()

    fun View.PrintInfo() = "我是View的扩展方法方法"
    fun Button.PrintInfo() = "我是Button的扩展方法方法"
    class simpleName {
        fun PrintInfo() = "我是simpleName的成员方法"
    }

    fun simpleName.PrintInfo() = "我是simpleName的扩展方法"

}
