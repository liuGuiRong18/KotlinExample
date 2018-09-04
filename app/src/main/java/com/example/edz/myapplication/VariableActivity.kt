package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.edz.myFunction.*


import kotlinx.android.synthetic.main.activity_variable.*

/***
 * 1.定义变量
 * 2.定义函数:函数一共就有4种类型，无参无返回值、无参有返回值、有参无返回值、有参有返回值
 * 3.字符串模板
 * 4.函数加强
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
    @SuppressLint("LongLogTag") override fun onCreate(savedInstanceState: Bundle?) {
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
        stringType("字符串模板")
        enhancedVersionFun()
    }

    /**
     * 函数 带有两个 Int 参数、返回 Int 的函数：
     */
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }


    /***
     * 表达式函数体作用
     * 表达式函数体作用是什么呢？
     * 针对函数体只有一行代码的情况，多了一种表达形式。
     * 针对函数体只有一行代码的情况，代码显得更加清爽，没有了大括号。
     * 表达式函数体支持返回类型自动推断。显得比较智能。
     */
    /***
     * 将表达式作为函数体、返回值类型⾃动推断的函数
     */
    private fun sumNoType(a: Int, b: Int) = a + b

    private fun sumNoType(a: Boolean) = a
    private fun sumNoType(a: String) = a

    /***
     * 无返回值 void
     */
    @SuppressLint("LongLogTag") private fun sunVoid(str: String): Unit {
        Log.d("VariableActivity===sunVoid", "sunVoid 值 $str")
    }

    /***
     * Unit 返回类型可以省略：
     * 将表达式作为函数体
     */
    @SuppressLint("LongLogTag") private fun sunNoVoid(str: String) = Log.d("VariableActivity===sunNoVoid", "sunNoVoid 值 $str")

    /***
     * 字符串模板
     */
    @SuppressLint("LongLogTag") private fun stringType(str: String) {
        Log.d("VariableActivity===sunNoVoid", "stringType 值 $str")
        Log.d("VariableActivity===sunNoVoid", "stringType 值 ${str.length}")//{}里面可以是表达式
        Log.d("VariableActivity===sunNoVoid", "stringType 值 ${"$"}9.9")
    }

    /***
     * 函数加强
     */
    private fun enhancedVersionFun() {
        enhancedVersionFunOne()
        enhancedVersionFunTwo()
    }


    /***
     *  函数加强_上
     * 1.命名参数：命令参数是指在方法调用的时候，传入实参的时候，可以按照特定格式传入形参名字。命令参数的第一个作用就是提高代码的可读性。命名参数的使用格式如下：methodName(形参名字=实参, 形参名字=实参, 形参名字=实参…)
     * 2.默认参数:什么是默认参数？就是，我们在函数定义的时候，可以给每一个形参，选择性的给一个默认实参。默认参数定义格式如：personDefault()
     */
    private fun enhancedVersionFunOne() {
        person("小李", true, true, "15", 30)//不用命名参数高 版本的IDEA即使你不写命名参数
        person(name = "小红", sex = true, isStudy = true, age = "20", high = 190)//命名参数 这样参数稍微明显一点可读性 加强(什么时候使用：1.低版本可以这样使用，2.一个方法多次调用如下面例子)
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===Person: ===name:小李===sex:true===isStudy:true====age:15===high:30
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===Person: ===name:小红===sex:true===isStudy:true====age:20===high:190
        //有默认参数
        //【Kotlin相比于Java，通过默认参数加命名参数，减少了方法的重载，写一个方法，可以有多种调用形式】。
        //默认顺序的 1 12 123 1234 12345 如下：
        personDefault("小猪")
        personDefault("晓东", true)
        personDefault("晓东", true, false, "17")
        personDefault("晓东", true, false, "17", 23)
        //不按默认顺序 带默认参数的方法，某一个参数使用了命名参数，后面的参数赋值都需要使用命名参数
        personDefault("小宝", sex = true)
        personDefault(sex = true, high = 3, isStudy = true)
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:小猪===sex:false===isStudy:false====age:30===high:5
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:晓东===sex:true===isStudy:false====age:30===high:5
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:晓东===sex:true===isStudy:false====age:17===high:5
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:晓东===sex:true===isStudy:false====age:17===high:23
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:小宝===sex:true===isStudy:false====age:30===high:5
        //        08-30 15:05:11.978 14245-14245/com.example.edz.myapplication D/VariableActivity===personDefault: ===name:默认值===sex:true===isStudy:true====age:30===high:3
    }

    /***
     * 第一，如果方法定义的时候，没有默认参数，方法调用的时候必须传递具体的实参。
     * 第二，如果方法带有默认参数，方法调用的时候可以不用传递具体实参，不传递实参的情况下，方法使用默认参数。
     */
    @SuppressLint("LongLogTag") private fun person(name: String, sex: Boolean, isStudy: Boolean, age: String, high: Int) = Log.d("VariableActivity===Person", "===name:$name===sex:$sex===isStudy:$isStudy====age:$age===high:$high")

    @SuppressLint("LongLogTag") private fun personDefault(name: String = "默认值", sex: Boolean = false, isStudy: Boolean = false, age: String = "30", high: Int = 5) = Log.d("VariableActivity===personDefault", "===name:$name===sex:$sex===isStudy:$isStudy====age:$age===high:$high")

    /***
     * kotlin函数加强_中
     * 1.可变参数：vararg关键字 java 中 int... args
     * Java中可变参数规则：可变参数前面可以有其他参数，但是可变参数只能出现在参数列表的最后
     * 用...代表可变参数，...位于变量类型和变量名之间
     * 调用含有可变参数的方法时，编译器为该可变参数隐式创建一个数组，在方法体中以数组的形式访问可变参数
     * 先来看看Java中的剩余参数是咋用的
    //    public class TestVariableParemeters {
    //
    //        public static void main(String[] args) {
    //            new TestVariableParemeters().addNumbers("liuliqianxiao", 1, 2, 3, 4, 5);
    //        }
    //
    //        public int addNumbers(String name, int... args) {
    //            int result = 0;
    //            for (int i = 0; i < args.length; i++) {
    //                result += args[i];
    //            }
    //            return result;
    //        }
    //
    //    }
     * 2.顶层函数
     *顶层函数也可以称为包级别函数，就是一个函数，可以直接放到某一个包里面，而不用一定需要放到某一个类里面。
     * 在Java里面一个方法，必须属于一个类，在Kotlin中，方法可以独立存在。其实我们之前写的很多函数都是顶层函数，
     *
     * 3.嵌套函数
     * 方法里面还可以还可以定义方法。这样的方法称为嵌套方法。
     * 4.中缀函数
     * 一个函数为成员函数或者拓展函数，并且只有一个参数，并且使用infix修饰。在进行函数调用的时候可以使用中缀方式调用，
     * 标有 infix 关键字的函数也可以使⽤中缀表⽰法（忽略该调⽤的点与圆括号）调⽤。
     * 中缀函数必须满⾜以下要求：
     * 它们必须是成员函数或成员函数；
     * 它们必须只有⼀个参数；
     * 其参数不得接受可变数量的参数且不能有默认值。
     */
    private fun enhancedVersionFunTwo() {
        varArgFun(3, 1, 2, str = "用vararg")
        //顶层函数
        topFunction("来自顶层函数的调用")
        //嵌套函数
        nestedFunction(User("xiaoming", "123456"))
        //中缀函数
        val fixFunction = FixFunction()

        fixFunction fixOne "中缀函数==成员函数"
        fixFunction.fixOne("中缀函数==成员函数")
        fixFunction fixTwo "中缀函数==拓展函数"
    }


    /**
     *  Kotlin中可变参数规则：
     * 可变参数不必是函数的参数列表中的最后一个,但必须使用命名参数
     * 用vararg paramName: paramType格式申明一个可变参数
     * 和Java一样，在函数体内部，可以以数组的形式使用这个可变参数的形参变量
     */
    @SuppressLint("LongLogTag") private fun varArgFun(a: Int, vararg any: Any, str: String) {
        for (i in any) {
            Log.d("VariableActivity===varArgFun", "$str + $i")
        }
//        08-30 15:42:15.788 2254-2254/com.example.edz.myapplication D/VariableActivity===varArgFun: 用vararg + 1
//        08-30 15:42:15.788 2254-2254/com.example.edz.myapplication D/VariableActivity===varArgFun: 用vararg + 2
    }


}
