package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_control_flow.*

import java.io.Serializable

/***
 * 1.类的创建&属性方法定义以及访问
 * 2.setter和getter
 * 3.主构函数、次构函数、初始化方法
 * 4.嵌套类（内部类）和数据类
 * 5.枚举类和印章类（密封类）
 * 6.类的继承
 * 7.抽象类
 * 8.接口、接口和抽象类的异同
 */
class FunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)
        setSupportActionBar(toolbar)
        createClass()
        setAndGetFun()
        constructorFun()
        innerFun()
        dataFun()
        enumFun()
        sealedFun()
        extendClass()
        abstractClass()
        interfaceClass()
    }


    /***
     * Kotlin默认实现了getter和setter方法，属性和方法的默认修饰符是public。
     * Kotlin创建类对象的时候不需要使用new关键字。类的方法访问和Java访问没区别。类属性访问直接通过点号方式访问，无需在调用getter和setter方法。
     */
    @SuppressLint("LongLogTag") private fun createClass() {
        //创建对象
        val user = User()
        //调用方法
        user.printUser()
        //访问属性
        Log.d("FunctionActivity.printUser访问属性 ", "名字：${user.name}  年龄：${user.age}")
        //08-31 10:37:33.264 26799-26799/com.example.edz.myapplication D/FunctionActivity.printUser访问属性: 名字：xiaoming年龄：18

        //修改属性
        user.age = 20
        user.name = "修改名字了"
        Log.d("FunctionActivity.printUser访问属性 ", "名字：${user.name}  年龄：${user.age}")
        //08-31 10:37:33.264 26799-26799/com.example.edz.myapplication D/FunctionActivity.printUser访问属性: 名字：修改名字了年龄：20
    }

    class User {
        //成员变量
        var name: String? = "xiaoming"
        var age: Int? = 18
        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.printUser ", "$name+$age")
        //08-31 10:37:33.264 26799-26799/com.example.edz.myapplication D/FunctionActivity.printUser: xiaoming+18
    }


    /****
     * 1.Kotlin 默认帮我们实现了setter、getter，算是setter和getter相关的第一个知识点，
     * 2.setter、getter自定义
     * 3.setter、getter私有化。
     */
    @SuppressLint("LongLogTag") private fun setAndGetFun() {
        //自定义
        val userTwo = UserTwo()
        userTwo.printUser()//08-31 11:35:51.767 25062-25062/com.example.edz.myapplication D/FunctionActivity.UserTwo_printUser: 名字age取大于18+30
        //有get的时候只能改变set里面的age
        userTwo.age = 16//08-31 11:35:51.767 25062-25062/com.example.edz.myapplication D/FunctionActivity.UserTwo: 小于18
        userTwo.printUser()//08-31 14:03:49.680 27559-27559/com.example.edz.myapplication D/FunctionActivity.UserTwo_printUser: 名字age取大于18+30
        //有get的时候只能改变set里面的name
        userTwo.name = "重新设置名字"//        08-31 14:03:49.680 27559-27559/com.example.edz.myapplication D/FunctionActivity.UserTwo: 重新设置名字
        userTwo.printUser()//-31 14:03:49.680 27559-27559/com.example.edz.myapplication D/FunctionActivity.UserTwo_printUser: 名字age取大于18+30
        Log.d("FunctionActivity.UserTwo_printUser ", "${userTwo.age}${userTwo.name}  ")//31 14:10:01.158 27559-27559/com.example.edz.myapplication D/FunctionActivity.UserTwo_printUser: 名字age取大于18+30
        //        userTwo.age2=3
        userTwo.chage = "改变变量必须没有get赋值"
        // 08-31 14:03:49.680 27559-27559/com.example.edz.myapplication D/FunctionActivity.UserTwo: 改变变量必须没有get赋值

    }

    class UserTwo {
        //成员变量
        var name: String? = "xiaoming"
                //    自定义
                //get 和set 一定是在当前要设置的变量下方 否则无法识别到
            get() = if (age > 18) "名字age取大于18" else "名字age取小于等于18"//初始化name 果有get方法 age变量的值始终取get的值
            @SuppressLint("LongLogTag") set(value) {
                field = value
                Log.d("FunctionActivity.UserTwo ", "$value")
            }
        var age: Int = 18
                //    自定义
            get() = 30////初始化age 如果有get方法 age变量的值始终取get的值
            @SuppressLint("LongLogTag") set(value) {
                //幕后字段 field
                field = value
                if (value > 18) {
                    Log.d("FunctionActivity.UserTwo ", "大于18")
                } else {
                    Log.d("FunctionActivity.UserTwo ", "小于18")
                }

            }
        var chage = "改变了吗"
            @SuppressLint("LongLogTag") set(value) {
                //幕后字段 field
                field = value
                if (value == "改变变量必须没有get赋值") {
                    Log.d("FunctionActivity.UserTwo ", chage)
                } else {
                    Log.d("FunctionActivity.UserTwo ", "没改")
                }

            }
        //setter私有化
        var age2: Int = 18
            private set(value) {
                field = 3

            }
        //只读还可以这样写
        val isEmpty get() = true

        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.UserTwo_printUser ", "$name+$age")

    }

    /**
     *1.主构函数
     * Kotlin在类定义的时候，在类名后面加上小括号，变成了函数形式。
     * 我们在小括号内就可以直接定义类创建的时候可以接收的值，并可以把接收的值赋值给变量，
     * 达到Java里面构造函数的作用，我们称这样的函数为主构函数
     *2.次构函数
     * Kotlin的主构函数确实给我们带来了很多的方便，在编程开发过程中，能用主构函数，我们优先去用主构函数。
     * 其实Kotlin同样允许你像Java一样在类的内部定义构造函数，我们称这样的构造函数为次构函数。
     *3.初始化方法
     * Java的类里面可以包含静态初始化快、实例初始化快。Kotlin为类提供了初始化方法
     */
    private fun constructorFun() {
        mainConstrctor()
        minorConstructor()
        initFun()
    }


    /**
     * 主构函数
     */
    private fun mainConstrctor() {
        //无默认值
        Person("主构函数", 18).printUser()
        //        08-31 14:52:16.681 24102-24102/com.example.edz.myapplication D/FunctionActivity.Person: 主构函数+18

        //主构函数的参数如果带默认参数之后，我们在调用主构函数的时候，配合命名参数，创建对象的时候，则可以有多种多样的形式
        PersonTwo().printUser()//使用默认值创建对象
        PersonTwo("一个参数的构造形式").printUser()//覆盖name
        PersonTwo(age = 23).printUser()//覆盖age
        PersonTwo("一个参数的构造形式", 2).printUser()//覆盖所有
        //        08-31 14:52:16.681 24102-24102/com.example.edz.myapplication D/FunctionActivity.PersonTwo: 默认值+0
        //        08-31 14:52:16.681 24102-24102/com.example.edz.myapplication D/FunctionActivity.PersonTwo: 一个参数的构造形式+0
        //        08-31 14:52:16.681 24102-24102/com.example.edz.myapplication D/FunctionActivity.PersonTwo: 默认值+23
        //        08-31 14:52:16.681 24102-24102/com.example.edz.myapplication D/FunctionActivity.PersonTwo: 一个参数的构造形式+2
    }

    //没有var
//    class Person( name: String, age: Int) {
//        var name = ""
//        var age = 0
//        //成员方法
//        @SuppressLint("LongLogTag")
//        fun printUser() = Log.d("FunctionActivity.Person ", "$name+$age")
//    }
    //有var就变成成员变量
    class Person(var name: String, private var age: Int) {
        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.Person ", "$name+$age")
    }

    class PersonTwo(var name: String = "默认值", var age: Int = 0) {
        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.PersonTwo ", "$name+$age")
    }

    /***
     *  次构函数
     *  使用次构函数的时候，分两种情况，一种是类不存在主构函数，一种是类存在主构函数。
     */
    private fun minorConstructor() {
        PersonMinor("次构函数不存在主构函数", 1).printUser()
        PersonMinorTwo("次构函数存在主构函数", 1).printUser()
//        08-31 15:23:39.125 12793-12793/com.example.edz.myapplication D/FunctionActivity.personMinor: 次构函数不存在主构函数+1
//        08-31 15:23:39.126 12793-12793/com.example.edz.myapplication D/FunctionActivity.PersonMinorTwo: 次构函数存在主构函数+1
    }

    class PersonMinor {
        var name = ""
        var age = 0

        constructor(name: String, age: Int) {
            this.name = name
            this.age = age
        }

        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.personMinor ", "$name+$age")
    }

    class PersonMinorTwo(var name: String="") {

        private var age = 0

        //存在主构函数，所以我们通过“：this（name）”对主构函数成员变量进行了赋值
        // 注意次构函数不能像主构函数那样，通过加上var或者val修饰符，让方法参数变成类的成员属性，次构函数只能接收值
        constructor(name: String, age: Int) : this(name) {
            this.age = age
        }

        //成员方法
        @SuppressLint("LongLogTag")
        fun printUser() = Log.d("FunctionActivity.PersonMinorTwo ", "$name+$age")
    }

    /***
     * 初始化方法
     */
    private fun initFun() {
        //初始化init 比构造函数 早执行
        PersonMinorInit("构造函数", 1)
//        08-31 15:37:49.510 18303-18303/com.example.edz.myapplication D/FunctionActivity.PersonMinorInit: 我是初始化init的方法
//        08-31 15:37:49.510 18303-18303/com.example.edz.myapplication D/FunctionActivity.PersonMinorInit: 构造函数+1
    }

    @SuppressLint("LongLogTag")
    class PersonMinorInit {
        var name = ""
        var age = 0

        init {
            Log.d("FunctionActivity.PersonMinorInit ", "我是初始化init的方法")
        }

        constructor(name: String, age: Int) {
            this.name = name
            this.age = age
            printUser()
        }

        //成员方法
        @SuppressLint("LongLogTag") private fun printUser() = Log.d("FunctionActivity.PersonMinorInit ", "$name+$age")
    }

    /****
     * 嵌套类
     * Kotlin内部还可以定义类，这样的类，我们可以称为嵌套类(nestedclasses)。
     * 嵌套类分成两种类型，一种是不通过Inner关键字修饰的嵌套类，一种是通过Inner关键字修饰的嵌套类。
     * 两者的区别主要体现在类的创建以及对外部内属性的访问上。
     */
    private fun innerFun() {
        //不通过Inner关键字 嵌套类的调用 Outer.Inter()
        val inter = Outer.Inter()
        inter.printUser()
//        08-31 16:27:18.451 3086-3086/com.example.edz.myapplication D/FunctionActivity.Outer: 内部类

        //inner关键字修饰的嵌套类 能访问外部类的成员属性 内部类创建的时候，需要先创建外部类对象 Outer().InterTwo()
        val interTwo = Outer().InterTwo()
        interTwo.printUser()

//        08-31 16:27:18.452 3086-3086/com.example.edz.myapplication D/FunctionActivity.InterTwo: 内部类==>inner 能访问外部类成员属性

    }

    @SuppressLint("LongLogTag")
    class Outer {
        var outerVar = "inner 能访问外部类成员属性"

        class Inter {
            fun printUser() = Log.d("FunctionActivity.Outer ", "内部类")
            //嵌套类不能访问外部类成员属性
//            fun printUser() = Log.d("FunctionActivity.PersonMinorInit ", "内部类$outerVar")
        }

        inner class InterTwo {
            //inner 能访问外部类成员属性
            fun printUser() = Log.d("FunctionActivity.InterTwo ", "内部类==>$outerVar")
        }
    }

    /****
     * 数据类
     * Kotlin在类创建方面花了很多心思，让我们创建类变的非常方便，简化了代码。Kotlin还为我们提供了一种特定的类，数据类。
     * 在类定义的时候，通过在最前面加上data关键字，就可以标明这个类是一个数据类。
     * 如果一个类被标明为数据类，编译器在编译的时候会自动帮我们覆写常用的toString、hashCode、equals、copy、comopinentX方法。
     * toString方法，方便做日志输出，非常方便。
     * hashCode、equals方法，方便对象比较，非常方便。
     * copy方法，方便做对象复制，非常方便。
     * comopinentX方法，可以很方面的解析对象包含的变量，非常强大。
     */
    @SuppressLint("LongLogTag") private fun dataFun() {
        val dataExample = DataExample("西门吹雪", 10, 23)
        val dataExample2 = DataExample("西东", 10, 23)
        val dataExample3 = DataExample("西东", 10, 23)
        val dataExample4 = DataExample(high = 23)

        Log.d("FunctionActivity.dataFun ", "$dataExample")
        Log.d("FunctionActivity.dataFun ", "${dataExample == dataExample2}")
        Log.d("FunctionActivity.dataFun ", "${dataExample3 === dataExample3}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.copy()}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.copy(name = "xiaoming")}")
//        08-31 17:28:21.046 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: DataExample(name=西门吹雪, age=10, high=23)
//        08-31 17:28:21.046 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: false
//        08-31 17:28:21.046 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: true
//        08-31 17:28:21.046 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: DataExample(name=西门吹雪, age=10, high=23)
//        08-31 17:28:21.047 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: DataExample(name=xiaoming, age=10, high=23)
        //这种做法，称作Kotlin的解析析构，可以把一个对象的值赋值给多个变量，要求这个类中包含comopinentX方法，比如，我们这里的dataExample包含了component1方法和component2方法component3方法。
        val (name, age, high) = dataExample3
        Log.d("FunctionActivity.dataFun ", "$name$age$high")
        Log.d("FunctionActivity.dataFun ", "${dataExample.component1()}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.component2()}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.component3()}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.name}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.age}")
        Log.d("FunctionActivity.dataFun ", "${dataExample.high}")
//        08-31 17:28:21.047 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: 西东1023
//        08-31 17:28:21.047 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: 西门吹雪
//        08-31 17:28:21.047 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: 10
//        08-31 17:28:21.047 23535-23535/com.example.edz.myapplication D/FunctionActivity.dataFun: 23
//        08-31 17:33:20.314 26137-26137/com.example.edz.myapplication D/FunctionActivity.dataFun: 西门吹雪
//        08-31 17:33:20.314 26137-26137/com.example.edz.myapplication D/FunctionActivity.dataFun: 10
//        08-31 17:33:20.314 26137-26137/com.example.edz.myapplication D/FunctionActivity.dataFun: 23
    }

    data class DataExample(var name: String = "", var age: Int = 2, var high: Int = 2) : Serializable

    /***
     * 枚举
     * 枚举类遍历&枚举常量常用属性
     * 枚举类自定义属性
     *
     */
    @SuppressLint("LongLogTag") private fun enumFun() {
        isWeek(Week.MON)
        //        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.isWeek: 今天是工作日
//        每一个枚举常量具有name属性和ordinal属性，name表示表示枚举常量的名字，ordinal表示枚举常量的顺序，顺序从0开始
        for (value in Week.values()) {
            Log.d("FunctionActivity.Week name ", value.name)
            Log.d("FunctionActivity.Week ordinal ", value.ordinal.toString())
        }
        Log.d("FunctionActivity.Week ordinal ", Week.MON.name)
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: MON
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 0
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: TUE
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 1
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: WED
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 2
//        09-03 16:18:06.301 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: THU
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 3
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: FRI
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 4
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: SAT
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 5
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week name: SUN
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: 6
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.Week ordinal: MON
        val mon = WeekTwo.MON
        val sat = WeekTwo.SAT
        Log.d("FunctionActivity.WeekTwo mon name ", mon.name)
        Log.d("FunctionActivity.WeekTwo mon ordinal ", mon.ordinal.toString())
        Log.d("FunctionActivity.WeekTwo mon what ", mon.what)
        Log.d("FunctionActivity.WeekTwo mon doSomething ", mon.doSomething)
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo mon name: MON
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo mon ordinal: 0
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo mon what: 周一
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo mon doSomething: 上班
        Log.d("FunctionActivity.WeekTwo sat name ", sat.name)
        Log.d("FunctionActivity.WeekTwo sat ordinal ", sat.ordinal.toString())
        Log.d("FunctionActivity.WeekTwo sat what ", sat.what)
        Log.d("FunctionActivity.WeekTwo sat doSomething ", sat.doSomething)

//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo sat name: SAT
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo sat ordinal: 5
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo sat what: 周六
//        09-03 16:18:06.302 14826-14826/com.example.edz.myapplication D/FunctionActivity.WeekTwo sat doSomething: 放假
    }

    /**
     * 常规枚举的写法
     */
    enum class Week {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    /**
     * 枚举类自定义属性
     */
    enum class WeekTwo(var what: String, var doSomething: String) {
        MON("周一", "上班"), TUE("周二", "上班"), WED("周三", "上班"), THU("周四", "上班"), FRI("周五", "上班"), SAT("周六", "放假"), SUN("周天", "放假")
    }

    @SuppressLint("LongLogTag") private fun isWeek(week: Week) = if (week == Week.SAT || week == Week.SUN) Log.d("FunctionActivity.isWeek ", "今天是周末") else Log.d("FunctionActivity.isWeek ", "今天是工作日")


    /***
     * 印章类 kotlin新属性
     *  通过enum关键字可以定义一个枚举类，枚举类让一个类拥有了有限多个常量。
     * 通过sealed关键字，则可以定义一个印章类，印章类让一个类拥有了有限多个子类。
     * 印章类甚至可以理解为一个特殊的枚举类。印章类本身不能被实例化。
     *
     */
    private fun sealedFun() {
        //印章类包含的类可以被实例化
        val add: Operation.add = Operation.add()
        val sub: Operation.sub = Operation.sub()
        //印章类本身不能被实例化
//        val operation = Operation()

        //分支操作
        printOperationTwo(operation = OperationTwo.add())
        //        09-03 17:55:50.041 23951-23951/com.example.edz.myapplication D/FunctionActivity。 printOperationTwo: OperationTwo.add
//        携带自定义属性
        printOperationThree(OperationThree.add(2, 1))
        printOperationThree(OperationThree.sub(2, 1))
        printOperationThree(OperationThree.multiply(2, 1))
        printOperationThree(OperationThree.divide(2, 1))

//        09-03 17:55:50.041 23951-23951/com.example.edz.myapplication D/FunctionActivity。 printOperationThree: 2+1=3
//        09-03 17:55:50.041 23951-23951/com.example.edz.myapplication D/FunctionActivity。 printOperationThree: 2-1=1
//        09-03 17:55:50.042 23951-23951/com.example.edz.myapplication D/FunctionActivity。 printOperationThree: 2*1=2
//        09-03 17:55:50.042 23951-23951/com.example.edz.myapplication D/FunctionActivity。 printOperationThree: 2/1=2
    }

    sealed class Operation {
        class add
        class sub
        class multiply
        class divide
    }

    sealed class OperationTwo {
        class add : OperationTwo()//这里是继承
        class sub : OperationTwo()
        class multiply : OperationTwo()
        class divide : OperationTwo()
    }

    @SuppressLint("LongLogTag") private fun printOperationTwo(operation: OperationTwo) {
        when (operation) {
            is OperationTwo.add -> Log.d("FunctionActivity。 printOperationTwo ", "OperationTwo.add")
            is OperationTwo.sub -> Log.d("FunctionActivity。 printOperationTwo ", "OperationTwo.sub")
            is OperationTwo.multiply -> Log.d("FunctionActivity。 printOperationTwo ", "OperationTwo.multiply")
            is OperationTwo.divide -> Log.d("FunctionActivity。 printOperationTwo ", "OperationTwo.divide")
        }
    }

    sealed class OperationThree {
        class add(var num1: Int, var num2: Int) : OperationThree()//这里是继承
        class sub(var num1: Int, var num2: Int) : OperationThree()
        class multiply(var num1: Int, var num2: Int) : OperationThree()
        class divide(var num1: Int, var num2: Int) : OperationThree()
    }

    @SuppressLint("LongLogTag") private fun printOperationThree(operation: OperationThree) {
        when (operation) {
            is OperationThree.add -> Log.d("FunctionActivity。 printOperationThree ", "${operation.num1}+${operation.num2}=${operation.num1 + operation.num2}")
            is OperationThree.sub -> Log.d("FunctionActivity。 printOperationThree ", "${operation.num1}-${operation.num2}=${operation.num1 - operation.num2}")
            is OperationThree.multiply -> Log.d("FunctionActivity。 printOperationThree ", "${operation.num1}*${operation.num2}=${operation.num1 * operation.num2}")
            is OperationThree.divide -> Log.d("FunctionActivity。 printOperationThree ", "${operation.num1}/${operation.num2}=${operation.num1 / operation.num2}")
        }
    }

    /***
     * 类的继承
     * 1.继承的表现：第一，Kotlin中的继承关系，通过冒号去表示。第二，父类需要通过open关键字表示，才可以被继承。
     * 2.子类可以拥有父类的方法和属性
     * 3.子类覆写父类的方法,成员变量
     *
     */
    @SuppressLint("LongLogTag") private fun extendClass() {
        val manTwo = ManTwo("次构函数 给父类赋值")
        val woManTwo = WoManTwo("主构函数 给父类赋值")
        Log.d("FunctionActivity。 extendClass manTwo", "${manTwo.name2 + manTwo.setName()}")
        Log.d("FunctionActivity。 extendClass manTwo", "${woManTwo.name + woManTwo.setName()}")
//        09-04 10:44:36.411 15081-15081/com.example.edz.myapplication D/FunctionActivity。 extendClass manTwo: 次构函数 给父类赋值来自父类的方法
//        09-04 10:44:36.411 15081-15081/com.example.edz.myapplication D/FunctionActivity。 extendClass manTwo: 主构函数 给父类赋值来自父类的方法

        var manFour = ManFour("重写父类的方法")
        var manfive :PersonFour= ManFour("重写父类的方法")//注意和扩展函数对比
        Log.d("FunctionActivity。 extendClass manFour", "${manFour.age.toString() + manFour.setName()}")
        Log.d("FunctionActivity。 extendClass manfive", "${manfive.age.toString() + manfive.setName()}")
//        09-05 14:01:31.130 17940-17940/com.example.edz.myapplication D/FunctionActivity。 extendClass manFour: 20来自子类ManFour的方法
//        09-05 14:01:31.130 17940-17940/com.example.edz.myapplication D/FunctionActivity。 extendClass manFour: 20来自子类ManFour的方法

    }

    /****
     * 最基本的继承关系表现
     * 1.open
     * 2.":"
     */
    open class PersonOne

    class ManOne : PersonOne()
    class WoManOnew : PersonOne()
    /****
     *子类可以拥有父类的方法和属性
     */
    open class PersonThree(var name: String = "", var name2: String = "") {
        fun setName() = "来自父类的方法"
    }

    class ManTwo : PersonThree {
        //通过次构函数 给父类赋值
        constructor(name: String) : super(name2 = name)//因为父类定义了name属性，子类可以通过次构函数，接收外界传入的name属性，然后通过super关键字，传递给了父类。
    }

    //通过主构函数 给父类赋值
    class WoManTwo(name: String) : PersonThree(name)//，因为父类定义了name属性，子类也可以通过主构函数，接收外界传入的name属性，通过父类的主构函数传递给父类。


    /****
     * 子类重写方法和变量open关键字标记。
     */
    open class PersonFour(var name: String = "", var name2: String = "") {
        open var age = 15
        open fun setName() = "来自父类的方法"
    }

    class ManFour : PersonFour {
        // override 重写方法 和变量
        override var age: Int = 20

        override fun setName(): String {
            return "来自子类ManFour的方法"
        }

        constructor(name: String) : super(name2 = name)
    }

    /***
     * 抽象类
     * 抽象类，还可以包含抽象属性，也就是通过**abstract**关键字修饰的属性，
     * 如果抽象类中含有抽象属性，子类中必须将抽象属性初始化，除非子类也为抽象类。
     */
    @SuppressLint("LongLogTag") private fun abstractClass() {
        val abatractA= AbatractA()
        Log.d("FunctionActivity。 abstractClass ", "${abatractA.A() + abatractA.B}")
        //abstractClass: 来自子类AbatractA的方法10

    }

    /**
     * 抽象类可包含方法实现，不能被实例化
     * 子类必须实现抽象方法或者变成抽象类
     * 抽象类 抽象方法 抽象变量 可以不写open
     * 抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public
     */
    abstract class Abatract {
        abstract fun A(): String//抽象方法和抽象变量不可实例化
        abstract var B: Int
        open var C: Int = 3
    }

    class AbatractA : Abatract() {
        override fun A(): String {
            return "来自子类AbatractA的方法" + C
        }

        override var B: Int = 10

    }

    abstract class AbatractB : Abatract() {}

    /*****
     *  接口
     * 编程世界里，可以把一些独立的功能、模块、能力，定义为一个一个的接口。比如“可点击的”、“可触摸的”、“可滑动的”、“可吃的”
     * Java中的接口中的方法，不能有方法体（JDK1.8之后可以有），Kotlin中的接口方法，可以带有方法体（学习了jdk1.8的做法）
     * 不用open关键字修饰带有方法体的方法，接口的实现类也可以重写带有方法体的方法
     * Java接口中可以包含成员变量，属性必须被成员变量。Kotlin中的接口中也可以包含成员变量，
     * 但是成员变量不能被初始化，需要交给子类初始化，子类如果不进行初始化，那么将变成抽象类。
     */
    @SuppressLint("LongLogTag") private fun interfaceClass() {
        val button= Button()
        Log.d("FunctionActivity。 interfaceClass ", "${button.click()+"======="+button.touch() +"======="+ button.printInfo()}")
//        09-04 14:56:35.440 23718-23718/com.example.edz.myapplication D/FunctionActivity。 interfaceClass: 子类实现方法click=======子类实现方法touch=======接口可以带方法体Clickable
    }

    interface Clickable {
        fun click(): String
        fun printInfo():String {
          return  "接口可以带方法体Clickable"
        }
        var age: Int
    }

    interface Touchable {
        fun touch(): String
        fun printInfo() :String {
            return  "接口可以带方法体Touchable"
        }
    }
    //实现多个接口用逗号去分割
    class Button : Clickable, Touchable {
        override var age: Int = 3
        override fun click(): String {
            return "子类实现方法click"
        }

        override fun touch(): String {
            return "子类实现方法touch"
        }
        //相同方法名的调用
        override fun printInfo(): String {
            //调用Clickable
            return super<Clickable>.printInfo()
            // 调用Touchable
            return super<Touchable>.printInfo()
        }
    }


}





