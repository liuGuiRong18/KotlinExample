package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.SparseIntArray

import kotlinx.android.synthetic.main.activity_control_flow.*

/***
 * 控制流
 * 1.if
 * 2.when
 * 3.区间
 * 4.while
 * 5.return/break/continue
 */
class ControlFlowActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_flow)
        setSupportActionBar(toolbar)
        IfFlow(10, 20)
        for (i in 1..10) {
            WhenFlow(i)
        }
        section()
        doWhileExample()
        controlExample()
    }

    /***
     * If 表达式
     * 在 Kotlin 中， if是⼀个表达式，即它会返回⼀个值。 因此就不需要三元运算符（条件 ? 然后 : 否则） ，因为普通的 if 就能胜任这个⻆⾊。
     */
    @SuppressLint("LongLogTag")
    private fun IfFlow(a: Int, b: Int) {
        var max: Int
        //1.传统用法
        if (a > b) max = a
        //2.with else
        if (a > b) {
            max = a
        } else {
            max = b
        }
        Log.d("ControlFlowActivity.If 表达式", max.toString())
        //3.作为表达式
        max = if (a > b) a else b
        Log.d("ControlFlowActivity.If 表达式", max.toString())
        //4.if的分⽀可以是代码块，最后的表达式作为该块的值
        var num = if (a > b) {
            a + 5
        } else {
            b + 10
        }
        Log.d("ControlFlowActivity.If 表达式", num.toString())
    }

    fun maxOf(a: Int, b: Int) = if (a > b) a else b
    /***
     * when表达式
     * 取代 Java中的switch
     */
    @SuppressLint("LongLogTag")
    private fun WhenFlow(a: Int) {
        when (a) {
        //满足前面的条件 就不会执行直接break
        // 范围 in 1.2.3.4
            in 1..4 -> Log.d("ControlFlowActivity==when表达式 in", a.toString())
        // 不在范围 5和6
            !in 4..6 -> Log.d("ControlFlowActivity==when表达式 !in", a.toString())
        //多个条件可以用逗号 区别
            4, 5 -> Log.d("ControlFlowActivity==when表达式 4,5", a.toString())
            6 -> Log.d("ControlFlowActivity==when表达式 6", a.toString())
            else -> {
                Log.d("ControlFlowActivity==when表达式 else", a.toString())
            }
        }
    }



    /***
     *
     * 区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
     * 区间是为任何可⽐较类型定义的，但对于整型原⽣类型，它有⼀个优化的实现。
     * 以下是使⽤区间的⼀些⽰例
     */
    @SuppressLint("LongLogTag")
    private fun section() {
        /***
         * 区间[1,5]
         */
        for (i in 1..5) { // 等同于 1 <= i && i <= 5
            Log.d("ControlFlowActivity==in", i.toString())
        }
        // 08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==in: 1
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==in: 2
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==in: 3
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==in: 4
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==in: 5
        /***
         * 倒序错误示范
         */
        for (i in 5..1) {
            Log.d("ControlFlowActivity==倒序错误示范", i.toString())
        }

        /**
         * 倒序迭代数字
         * 10..1 不行
         * downTo
         */
        for (i in 5 downTo 1) {
            Log.d("ControlFlowActivity==  倒序迭代数字", i.toString())
        }
        //        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  倒序迭代数字: 5
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  倒序迭代数字: 4
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  倒序迭代数字: 3
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  倒序迭代数字: 2
//        08-27 15:41:48.922 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  倒序迭代数字: 1
        /***
         * 任意步⻓迭代数字
         * step
         */
        for (i in 1..10 step 3) {
            Log.d("ControlFlowActivity==  step倒序迭代数字3", i.toString())
        }
        //        08-27 15:41:48.923 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 1
//        08-27 15:41:48.923 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 4
//        08-27 15:41:48.923 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 7
//        08-27 15:41:48.923 31352-31352/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 10
        for (i in 5 downTo 1 step 3) {
            Log.d("ControlFlowActivity==  step倒序迭代数字3", i.toString())
        }

//        08-27 15:47:52.594 5179-5179/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 5
//        08-27 15:47:52.594 5179-5179/com.example.edz.myapplication D/ControlFlowActivity==  step倒序迭代数字3: 2
        /**
         * 要创建⼀个不包括其结束元素的区间，可以使⽤ until 函数
         * [1,5)
         */
        for (i in 1 until 5) {
            Log.d("ControlFlowActivity==  until", i.toString())
        }
//        08-27 15:50:47.779 8503-8503/com.example.edz.myapplication D/ControlFlowActivity==  until: 1
//        08-27 15:50:47.779 8503-8503/com.example.edz.myapplication D/ControlFlowActivity==  until: 2
//        08-27 15:50:47.779 8503-8503/com.example.edz.myapplication D/ControlFlowActivity==  until: 3
//        08-27 15:50:47.779 8503-8503/com.example.edz.myapplication D/ControlFlowActivity==  until: 4


        var a = IntProgression.fromClosedRange(0, 5, 2)
        for (i in a) {
            Log.d("ControlFlowActivity==  IntProgression.fromClosedRange", i.toString())
        }
        //IntRange、 LongRange、 CharRange有⼀个额外的特性：它们可以迭代。
        //定义数字范围的方法
        var rangeInt: IntRange = 3..5
        var rangeA = IntRange(3, 5)
        var rangeB = 3.rangeTo(5)
        var rangeC = LongRange(3, 5)
        var rangeD = 5 downTo 1

        for (i in rangeInt) {
            Log.d("ControlFlowActivity==  rangeInt", i.toString())
        }
        for (i in rangeA) {
            Log.d("ControlFlowActivity==  rangeA", i.toString())
        }
        for (i in rangeB) {
            Log.d("ControlFlowActivity==  rangeB", i.toString())
        }
        for (i in rangeC) {
            Log.d("ControlFlowActivity==  rangeC", i.toString())
        }
        /**
         * for 循环 打印下标
         */
        for ((index, value) in rangeA.withIndex()) {
            Log.d("ControlFlowActivity==  withIndex", "the element at $index is $value")
        }

        //        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeA: 3
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeA: 4
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeA: 5
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeB: 3
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeB: 4
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeB: 5
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeC: 3
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeC: 4
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==  rangeC: 5
        Log.d("ControlFlowActivity==     4 in rangeInt", (4 in rangeInt).toString())
        Log.d("ControlFlowActivity==      3 !in rangeInt", (3 !in rangeInt).toString())
        Log.d("ControlFlowActivity==       rangeInt.contains(3)", (rangeInt.contains(3)).toString())
        Log.d("ControlFlowActivity==         rangeInt.count()", (rangeInt.count()).toString())
        Log.d("ControlFlowActivity==          rangeInt.reversed()", (rangeInt.reversed()).toString())
        Log.d("ControlFlowActivity==          rangeInt.last", (rangeInt.last).toString())
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==     4 in rangeInt: true
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==      3 !in rangeInt: false
//        08-27 16:47:30.250 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==       rangeInt.contains(3): true
//        08-27 16:47:30.251 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==         rangeInt.count(): 3
//        08-27 16:47:30.251 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==          rangeInt.reversed(): 5 downTo 3 step 1
//        08-27 16:47:30.251 28246-28246/com.example.edz.myapplication D/ControlFlowActivity==          rangeInt.last: 5

    }

    /**
     * 流程控制之do..while语句
     */
    @SuppressLint("LongLogTag")
    fun doWhileExample() {

        /*
          Kotlin中的while循环同Java中的一样，其定义格式为：
           do{...}while(exp)
         */
        var num = 5
        var count = 1
        do {
            Log.d("ControlFlowActivity==  doWhileExample", "num => $num")
            Log.d("ControlFlowActivity==  doWhileExample", "循环了$count 次")
            count++
            num++
        } while (num < 10)

        // do{...}while(exp)与while(exp){...}最大的区别是do{...}while(exp)最少执行一次

        num = 5
        count = 1
        do {
            Log.d("ControlFlowActivity==  doWhileExample", "num => $num")
            Log.d("ControlFlowActivity==  doWhileExample", "循环了$count 次")
            count++
            num++
        } while (num < 5)
    }

    /**
     * 流程控制之控制语句
     * 1. return
     * 2. break
     * 3. continue
     */
    fun controlExample() {
        /*
            1. return语句,同Java中的return语句一样
            默认情况下，从最近的封闭函数或匿名函数返回。
         */
        returnExample()

        /*
            2. break语句，同Java中的break语句一样
            终止最近的闭合循环。
         */
        breakExample()

        /*
            3. continue语句,同Java中的continue语句一样
            前进到最近的封闭循环的下一个步骤(迭代)。
         */
        continueExample()
    }

    @SuppressLint("LongLogTag")
    fun continueExample() {
        for (i in 1 until 10) {
            if (i == 5) {
                Log.d("ControlFlowActivity==  continueExample", "我跳过了第$i 次循环")
                continue
            }
            Log.d("ControlFlowActivity==  continueExample", "i => $i")
        }
    }

    @SuppressLint("LongLogTag")
    fun breakExample() {
        var count: Int = 1
        for (i in 1 until 10) {
            if (i == 5) {
                Log.d("ControlFlowActivity==  breakExample", "我在第$i 次退出了循环")
                break
            }
            count++
        }
        Log.d("ControlFlowActivity==  breakExample", "我循环了多少次：count => $count")
    }

    @SuppressLint("LongLogTag")
    fun returnExample() {
        var str: String = ""
        if (str.isBlank()) {
            Log.d("ControlFlowActivity==  returnExample", "我退出了该方法")
            return
        }
    }
}
