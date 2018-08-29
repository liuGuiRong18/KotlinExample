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
 * 4.?和?. 和!! 和?:
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
    @SuppressLint("LongLogTag")
    private fun typeCheck(int: Any) {
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
    @SuppressLint("LongLogTag")
    private fun autoCast(obj: Any) {
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
    @SuppressLint("LongLogTag")
    private fun typeCast() {
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


}
