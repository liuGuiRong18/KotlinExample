package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_operator.*

/**
 * 运算符
 * 1.类型的检查is和!is运算符
 * 2.智能转换
 * 3.强制转换:as和as?运算符
 * 4.空安全: ?和?. 和!! 和?:
 */
class OperatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator)
        setSupportActionBar(toolbar)
        typeCheck("自动转化成String 不需要再去像Java一样去强转")
        typeCheck(3)
        autoCast("智能转化")
        typeCast()
        NullMechanism(null)
    }
    /***
     * Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类：
     * 注意：Any 不是 java.lang.Object。
     */


    /**
     * 类型检查
     * is和!is运算符
     * 在Java 中，instanceof 运算符用来在运行时检测对象是否是特定类的一个实例
     */
    @SuppressLint("LongLogTag") private fun typeCheck(int: Any) {
        //1判断是否是 String 类型
        if (int is String) {
            //不用强转 kotlin自动帮我们转成string
            Log.d("OperatorActivity.typeCheck====>>is", int.length.toString())
        } else {
            Log.d("OperatorActivity.typeCheck====>>is", int.toString())
        }
        //2
        if (int !is String) {
            //不用强转 kotlin自动帮我们转成string
            Log.d("OperatorActivity.typeCheck====>>!is", int.toString())
        } else {
            Log.d("OperatorActivity.typeCheck ====>>!is", int.length.toString())
        }

//        08-29 15:20:20.642 13128-13128/com.example.edz.myapplication D/OperatorActivity.typeCast====>>is: 27
//        08-29 15:20:20.642 13128-13128/com.example.edz.myapplication D/OperatorActivity.typeCast ====>>!is: 27
//        08-29 15:20:20.642 13128-13128/com.example.edz.myapplication D/OperatorActivity.typeCast====>>is: 3
//        08-29 15:20:20.642 13128-13128/com.example.edz.myapplication D/OperatorActivity.typeCast====>>!is: 3

    }

    /**
     * 智能转换
     * 在if语句、else语句、逻辑或、逻辑与、when表达式都能感受到is和!is运算符带来的智能转换
     */
    @SuppressLint("LongLogTag") private fun autoCast(obj: Any) {
        //if 条件 智能转化
        if (obj is String) obj.length //  (obj as? String)?.length
        //else 条件 智能转化
        if (obj !is String) {

        } else {
            obj.length
        }
        //逻辑或（||) 右侧智能转化
        //obj !is String -->> false  才会执行右边
        if (obj !is String || obj.length > 0) {

        }
        //逻辑并（&&）
        //obj is String -->> true  才会执行右边
        if (obj is String && obj.length > 0) {

        }
        //when
        when (obj) {
            is Int -> Log.d("OperatorActivity.autoCast ====>>is Int", obj.toString())
            is String -> Log.d("OperatorActivity.autoCast ====>>is Int", obj.length.toString())
            is IntArray -> {
                Log.d("OperatorActivity.autoCast ====>>is Int", obj.size.toString())
                Log.d("OperatorActivity.autoCast ====>>is Int", obj.sum().toString())
            }
        }
    }

    /***
     * 强制转换： as和as?运算符
     */
    @SuppressLint("LongLogTag") private fun typeCast() {
        var a = "String"
        var b = a as String
        //类型转换失败 as? 会转化成"null"
//        var f=a as Int//       Caused by: java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        var f = a as? Int
        Log.d("OperatorActivity.typeCast ====>>a as String", b.toString())
        Log.d("OperatorActivity.typeCast ====>>a as Int", f.toString())

        var c = 5
        var d = c as? String
        Log.d("OperatorActivity.typeCast ====>>a as? String", d?.length.toString())
//        08-29 16:06:05.652 27050-27050/com.example.edz.myapplication D/OperatorActivity.typeCast ====>>a as String: String
//        08-29 16:06:05.652 27050-27050/com.example.edz.myapplication D/OperatorActivity.typeCast ====>>a as Int: null
//        08-29 16:06:05.652 27050-27050/com.example.edz.myapplication D/OperatorActivity.typeCast ====>>a as? String: null
    }

    /**
     * ?和?. 和!! 和?:
     * null机制
     * 变量分为了可空类型和不可空类型
     * 变量类型	           描述	                        限制
     * 非空类型	           变量肯定不会为null	        非空类型变量，不能赋值为null
     * 可空类型	           变量可能为null	            可空类型变量，可空类型的变量不能直接使用，可以通过非空判断 ?. !!.使用
     */
    @SuppressLint("LongLogTag") private fun NullMechanism(num: Number?) {
        // 1. 声明可空变量类型  "?"
        var null_var: String? = null
        //2. 声明非空类型 不可空变量不能赋值为null
        var Non_Null_var: String
        //var Not_Null_var: String=null  //编译的时候错误提示"Null can not be a value of a non-null type String”也就是“非空类型的String不能赋值为null”。

        // null_var.toInt()//编译的时候错误提示“Only safe(?.) or non-null asserted(!!.) calls are allowed ona nullable receiver of type String?”也就是“可以通过安全调用符(?.)或者非空断言(!!.)直接去使用可空类型的变量”
        // java 的用法 非空判断
        if (null_var != null) {
            null_var.toInt()
        }
        //Java的非空判断用法太繁琐 Kotlin相比于Java，代码中减少了非空判断的使用  Kotlin提供的安全调用符或者非空断言

        /***
         * 3.安全调用符 ==>"?."
         * 安全调用符的出现为了解决什么问题？可空类型变量不能直接使用，但是直接使用非空判断又过于复杂，所以可以使用安全调用符。
         * 怎么使用安全调用符？之前的结构是【变量.方法】，现在的结构是【变量?.方法】。
         * 使用了安全调用符，代码执行逻辑是怎样的？变量不会NULL的时候，才去执行方法，所以不会报空指针。变量为NULL的时候，【变量?.方法】的结果为NULL
         * 其实可以把安全调用符看做是if非空判断的简写形式，也就是varresult = if (age != null) age.toInt() else null等同于var result = age?.toInt()。
         */
        Log.d("OperatorActivity.NullMechanism ====>>安全调用符 ==>?. null_var", null_var?.toInt().toString())
        var age: String? = "18"
        Log.d("OperatorActivity.NullMechanism ====>>安全调用符 ==>?. age", age?.toInt().toString())
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>安全调用符 ==>?. null_var: null
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>安全调用符 ==>?. age: 18
        /***
         * 4.非空断言==>"!!."
         * 非空断言的出现为了解决什么问题？可空类型变量不能直接使用，但是直接使用非空判断又过于复杂，所以还可以使用非空断言。
         * 怎么使用非空断言？之前的结构是【变量.方法】，现在的结构是【变量!!.方法】。
         * 使用非空断言，代码执行逻辑是怎样的？变量不为NULL的时候，执行方法，变量为NULL的时候，抛出异常
         */
//        Log.d("OperatorActivity.NullMechanism ====>>非空断言 ==>!!. null_var", null_var!!.toInt().toString())
        //  Caused by: kotlin.KotlinNullPointerException
        Log.d("OperatorActivity.NullMechanism ====>>非空断言 ==>!!. age", age!!.toInt().toString())
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>非空断言 ==>!!. age: 18
        /***
         * 安全调用符和非空断言如何选择？
         * 从安全角度，安全调用符比非空断言更加安全。变量为NULL的时候，使用安全调用符不会抛出异常，使用非空断言，会抛出异常。
         * 非空断言的做法是不推荐的，一个可空类型变量，加上非空断言，可以理解为开发者就是认定一个可空的变量为非空的，这显得有些霸道，不够和谐（大笑）。
         */
        /***
         * 5.?:( Elvis操作符)
         * 针对【变量?.方法】，如果变量为NULL，【变量?.方法】的返回结果是NULL，那我们能不能指定想返回的值呢？答案是肯定的，我们需要配合Elvis操作符，使用方式为【变量?:值 】
         */
        val any = null_var?.toInt() ?: "Elvis操作符"
        val any1 = num?.toInt() ?: "空值"
//        val any1 = num?.toInt() ?:  throw Exception("Hi There!")

        Log.d("OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?: null_var", any.toString())
        Log.d("OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?:  age", age?.toInt().toString())
        Log.d("OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?:  any_two", any1.toString())
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?: null_var: Elvis操作符
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?:  age: 18
//        08-30 13:55:10.790 28581-28581/com.example.edz.myapplication D/OperatorActivity.NullMechanism ====>>Elvis操作符 ==>?:  any_two: 空值
    }


}
