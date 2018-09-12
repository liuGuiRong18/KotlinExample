package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_extend.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

@SuppressLint("LongLogTag")
/*****
 * 1.拓展函数&拓展属性
 * 2.委托类
 * 3.委托属性
 * 4.kotlin 5大内置委托
 * 5.kotlin Object关键
 */
class ExtendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extend)
        setSupportActionBar(toolbar)
        extendFun()
        entrustFun()
        entrustVariableFun()
        entrustPropertyFun()
        objectFun()
    }


    /***
     * 扩展函数,扩展成员变量
     * 定义：Kotlin的标准库给我们提供了大量的类，类里面提供了大量的方法。但是，实际开发过程中，具体的某一个类，仅仅通过系统提供的方法远远不能满足需求。
     * 使用Java开发的时候，我们可以通过继承类或者通过代理模式，可以对某一个类做增强。使用Kotlin开发，这一切变的简单。
     * Kotlin允许拓展方法，只需要按照给定的语法格式，可以很方便的对某一个类添加方法，和变量。
     */

    @SuppressLint("LongLogTag") private fun extendFun() {
        //扩展方法的调用
        AnyClass().extendName()
        Log.d("ExtendActivity。 extendFun ", "扩展函数".getLastChar())
//        09-04 16:14:20.796 663-663/com.example.edz.myapplication D/ExtendActivity。 extendFun: 数
//         拓展函数使用注意:java里面，父类和子类有相同方法的时候，子类方法优先。这是多态的表现，
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
        var stringq = "扩展变量w"
        Log.d("ExtendActivity。 extendFun extendVariable ", string.extendVariable)
        Log.d("ExtendActivity。 extendFun extendVariable ", stringq.extendVariable)
//09-04 16:44:38.408 28148-28148/com.example.edz.myapplication D/ExtendActivity。 extendFun extendVariable: 量
//        09-04 17:00:31.406 12715-12715/com.example.edz.myapplication D/ExtendActivity。 extendFun extendVariable: w
    }


    /***
     * 扩展函数写法
     */
    class AnyClass

    fun AnyClass.extendName() {}

    open class View
    class Button : View()

    fun View.PrintInfo() = "我是View的扩展方法方法"
    fun Button.PrintInfo() = "我是Button的扩展方法方法"
    class simpleName {
        fun PrintInfo() = "我是simpleName的成员方法"
    }

    fun simpleName.PrintInfo() = "我是simpleName的扩展方法"

    private fun String.getLastChar(): String {
        return this@getLastChar.substring(this.length - 1)
    }

    //扩展成员变量 必须用val声明   扩展快捷输入方式 exv和exf
    private val String.extendVariable: String
        get() = this.substring(this.length - 1)


    /****
     * 委托类
     * 委托模式也叫代理模式，是最常用的设计模式的一种。
     * 在委托模式中，有两个对象参与处理同一个请求，接受请求的对象将请求委托给另一个对象来处理。
     * 委托模式已证明是实现继承的一个很好的替代方式。
     * 委托在C#中是一个语言级特性。在Kotlin 也直接支持委托模式，更加优雅，简洁。
     * Kotlin 通过关键字 by 实现委托。
     * Kotlin中委托分为类委托和委托属性，Kotlin官方库也封装了一些常用的委托。
     *
     *
     */
    private fun entrustFun() {
        val dog = Dog()
        val pig = Pig(dog)
        val cat = Cat(dog)
        pig.eat()
        cat.eat()
//        09-05 14:54:23.355 8170-8170/com.example.edz.myapplication D/ExtendActivity。entrustFun Dog: Dog委托模式
//        09-05 14:54:23.355 8170-8170/com.example.edz.myapplication D/ExtendActivity。entrustFun Dog: Dog委托模式
//        09-05 14:54:23.355 8170-8170/com.example.edz.myapplication D/ExtendActivity。entrustFun Dog: Cat不写委托by


    }

    interface Animal {
        fun eat()
    }

    class Dog : Animal {
        @SuppressLint("LongLogTag") override fun eat() {
            Log.d("ExtendActivity。entrustFun Dog", "Dog委托模式")
        }
    }

    //委托写法 不需要实现eat
    class Pig(animal: Animal) : Animal by animal

    class Cat(var animal: Animal) : Animal {
        @SuppressLint("LongLogTag") override fun eat() {
            animal.eat()
            Log.d("ExtendActivity。entrustFun Dog", "Cat不写委托by")
        }
    }

    /***
     *  委托属性
     *  属性委托是指一个类的某个属性值不是在类中直接进行定义，而是将其委托给一个代理类，从而实现对该类的属性统一管理。
     *  属性委托，提供了一种属性操作的新形式，而不仅仅局限于自身的setter和gette
     *
     */
    @SuppressLint("LongLogTag") private fun entrustVariableFun() {
        val son = son()
        Log.d("ExtendActivity。entrustVariableFun", son.money.toString())//会新触发 father类里面的getValue
        son.money = 50//会新触发 father类里面的setvalue
        son.name = "修改名字"
//        09-05 15:38:20.830 1242-1242/? D/ExtendActivity。entrustVariableFun  getValue property: money
//        09-05 15:38:20.830 1242-1242/? D/ExtendActivity。entrustVariableFun  getValue property: 名字
//        09-05 15:38:20.830 1242-1242/? D/ExtendActivity。entrustVariableFun: 0
//        09-05 15:38:20.830 1242-1242/? D/ExtendActivity。entrustVariableFun  setValue money: 50
//        09-05 15:38:20.830 1242-1242/? D/ExtendActivity。entrustVariableFun  setValue property: money


    }

    class son {
        var money: Int by Father()//委托变量 目前知道只能一个
        var name: String = "名字"
    }

    @SuppressLint("LongLogTag")
    class Father {
        var sonMoney: Int = 0
        //setValue方法和getValue方法需要用operator关键字修饰
        //father setValue方法和getValue方法可以通过idea提示自动完成。我们只需要让属性by另一个对象，就会提示我们生成getValue和setValue。
        operator fun getValue(son: ExtendActivity.son, property: KProperty<*>): Int {        //operator 运算符 property 属性
            Log.d("ExtendActivity。entrustVariableFun  getValue property", property.name)
            Log.d("ExtendActivity。entrustVariableFun  getValue property", son.name)
            return sonMoney
        }

        operator fun setValue(son: ExtendActivity.son, property: KProperty<*>, i: Int) {
            Log.d("ExtendActivity。entrustVariableFun  setValue money", i.toString())
            Log.d("ExtendActivity。entrustVariableFun  setValue property", property.name)
            sonMoney = i
        }
    }

    /***
     * kotlin 5大内置委托
     * 1.延迟加载(Lazy) lazy()是一个函数, 接受一个Lambda表达式作为参数, 返回一个Lazy类型的实例,这个实例可以作为一个委托,
     * 实现延迟加载属性(lazy property):
     * 第一次调用 get() 时, 将会执行 lazy() 函数受到的Lambda 表达式,然后会记住这次执行的结果,
     * 以后所有对 get() 的调用都只会简单地返回以前记住的结果。
     * 2.可观察属性(Observable)
     * Delegates.observable()函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
     * 这种形式的委托，采用了观察者模式，其会检测可观察属性的变化，当被观察属性的setter()方法被调用的时候，
     * 响应器(handler)都会被调用(在属性赋值处理完成之后)并自动执行执行的lambda表达式，
     * 同时响应器会收到三个参数：被赋值的属性, 赋值前的旧属性值, 以及赋值后的新属性值。
     * 3.Vetoable（能投票的；有投票权的）
     * Delegates.vetoable()函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler),
     * 是可观察属性(Observable)的一个特例，
     * 不同的是在响应器指定的自动执行执行的lambda表达式中在保存新值之前做一些条件判断，来决定是否将新值保存。
     * 4.notNull
     * 对于一个不可为“non-null”的变量，我们需要保证它被正确的赋值。
     * 赋值操作可以在变量定义的时候，也可以后续代码里面进行赋值。我们只需要在变量后面使用notNull属性委托，编译器就允许我们后续进行赋值。
     * 加了notNull的属性委托，编译器允许我们后续赋值，那我们也有可能忘记赋值，这样使用变量的时候就会报空指针。
     * 那有的同学可能会说，我已经养成了良好的习惯，在使用变量之前会进行非空判断，这样的做法会导致有大量的非空判断，这是不美观的。
     * 其实这个时候即使做非空判断也会抛出异常(UninitializedPropertyAccessException)。因为notNull的属性委托，要求变量被引用的时候被赋值。
     *
     * 5.将多个属性保存在一个map内
     * Kotlin的map委托，提供了map和对象一一绑定关系，就是map的值可以决定对象的值，
     * 修改map的值也可以影响对象的值。但是这一切需要满足，map的key和属性的名称保证一致。
     */
    private fun entrustPropertyFun() {
        /**
         *   1 延迟加载(Lazy)
         */
        Log.d("ExtendActivity。entrustPropertyFun   lazyValue", normalValue)
        Log.d("ExtendActivity。entrustPropertyFun   lazyValue", lazyValue)
        Log.d("ExtendActivity。entrustPropertyFun   lazyValue", lazyValue)
//        09-06 13:59:04.563 8283-8283/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   lazyValue: Kotlin
//        09-06 13:59:04.563 8283-8283/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   lazyValue: 第一次调用输出 第二次调用不输出
//        09-06 13:59:04.563 8283-8283/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   lazyValue: 初始化lazy
//        09-06 13:59:04.563 8283-8283/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   lazyValue: 初始化lazy
        /**
         *   2.可观察属性(Observable)
         */
        Log.d("ExtendActivity。entrustPropertyFun   userName", "userName的初始值：$userName")
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   userName: userName的初始值：初始值
        userName = "userName第一次赋值"
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   userName: 属性变化事件的响应器被触发
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   property: userName
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   oldValue: 初始值
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   newValue: userName第一次赋值
        userName = "userName第二次赋值"
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   userName: 属性变化事件的响应器被触发
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   property: userName
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   oldValue: userName第一次赋值
//        09-06 14:15:19.233 22343-22343/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   newValue: userName第二次赋值
        /**
         *  3.Vetoable
         */
        Log.d("ExtendActivity。entrustPropertyFun   voteName", "voteName：$voteName")
        // 09-06 14:32:01.757 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun         voteName: voteName：voteable初始值
        voteName = "userName第一次赋值"
        Log.d("ExtendActivity。entrustPropertyFun   voteName", "voteName：$voteName")//判断为false修改失败
//        09-06 14:32:01.757 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   voteName: 属性变化事件的响应器被触发
//        09-06 14:32:01.757 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   property: voteName
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   oldValue: voteable初始值
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   newValue: userName第一次赋值
        //        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   voteName: voteName：voteable初始值
        voteName = "判断可以多种多样"
        Log.d("ExtendActivity。entrustPropertyFun   voteName", "voteName：$voteName")//判断为true修改成功
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   voteName: 属性变化事件的响应器被触发
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   property: voteName
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   oldValue: voteable初始值
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   newValue: 判断可以多种多样
//        09-06 14:32:01.758 3507-3507/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   voteName: voteName：判断可以多种多样
        /**
         * 4.notNull
         * 即使进行非空判断 还是会报错 一定需要赋值
         *  Caused by: java.lang.IllegalStateException: Property nonNullName should be initialized before get.
         */
//        if (nonNullName!=null){
//            Log.d("ExtendActivity。entrustPropertyFun   nonNullName", "nonNullName：$nonNullName")
//        }

        /***
         * 5.将多个属性保存在一个map内
         */

        var map: MutableMap<String, Any?> = mutableMapOf("name" to "百度", "url" to "www.baidu.com")
        val site = Site(map)
        Log.d("ExtendActivity。entrustPropertyFun   map", "name：${site.name}")//判断为true修改成功
        Log.d("ExtendActivity。entrustPropertyFun   map", "url：${site.url}")//判断为true修改成功
        map.put("name", "Google")
        map.put("url", "www.google.com")
        Log.d("ExtendActivity。entrustPropertyFun   map", "name：${site.name}")//判断为true修改成功
        Log.d("ExtendActivity。entrustPropertyFun   map", "url：${site.url}")//判断为true修改成功
//        09-06 17:20:34.678 12235-12235/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun    map: name：百度
//        09-06 17:20:34.678 12235-12235/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   map: url：www.baidu.com
//        09-06 17:20:34.678 12235-12235/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   map: name：Google
//        09-06 17:20:34.678 12235-12235/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   map: url：www.google.com
    }

    /******
     * 针对延迟加载lazy，我们需要知道两个结论。
     * 延迟属性只有在访问的时候才会被初始化。
     * 延迟属性只会初始化一次。
     */
    //一定是val
    val lazyValue: String by lazy {
        onlyOne()
        "初始化lazy"//变量被使用的时候 会执行初始化
    }

    private fun onlyOne() = Log.d("ExtendActivity。entrustPropertyFun   lazyValue", "第一次调用输出 第二次调用不输出")
    var normalValue = "Kotlin"

    var userName: String by Delegates.observable("初始值") { property, oldValue, newValue ->
        Log.d("ExtendActivity。entrustPropertyFun   userName", "属性变化事件的响应器被触发")
        Log.d("ExtendActivity。entrustPropertyFun   property", property.name)
        Log.d("ExtendActivity。entrustPropertyFun   oldValue", oldValue)
        Log.d("ExtendActivity。entrustPropertyFun   newValue", newValue)
    }
    var voteName: String by Delegates.vetoable("voteable初始值") { property, oldValue, newValue ->
        Log.d("ExtendActivity。entrustPropertyFun   voteName", "属性变化事件的响应器被触发")
        Log.d("ExtendActivity。entrustPropertyFun   property", property.name)
        Log.d("ExtendActivity。entrustPropertyFun   oldValue", oldValue)
        Log.d("ExtendActivity。entrustPropertyFun   newValue", newValue)
        newValue.contains("判断可以多种多样")//判断值true 变量会被修改 false 不被修改
//        return@vetoable  true
    }
    //不可为空的变量 两种表达方式
    private var nonNullName: String by Delegates.notNull<String>()
    private lateinit var nonNullNameTwo: String

    //
    class Site(val map: MutableMap<String, Any?>) {
        val name: String by map
        val url: String by map
    }

    /****
     * object
     * 1.创建单例
     * Object关键字用来修饰需要单例化的类
     * object声明的单例对象不能声明构造函数，因为单例对象只有一个实例，无需我们手动将它创建出来，因此自然不需要构造函数。
     * 2.创建匿名内部类
     * 3.object继承的类
     * 4.object声明的对象
     * 5.创建伴生对象
     * 我们知道Kotlin中成员方法的调用形式是【对象.方法()】。
     * 包级别函数的调用形式是【方法()】。那有没有【类.方法()】的形式呢？
     * 也就是类似java里面的静态方法的调用。答案是有的，通过companion(陪伴) object关键字申明的伴生对象，方法或者属性调用的时候就是【类.方法（）】、【类.属性】。
     * 什么是伴身对象呢？也就是伴随在某一个类身上。定义的时候，定义在一个类的内部，
     */

    private fun objectFun() {
        /***
         *  创建单例对象的时候使用的是【One】，而不是【One（）】
         */
        val one = One
        val two = One
        Log.d("ExtendActivity。entrustPropertyFun   objectFun", one.toString())
        Log.d("ExtendActivity。entrustPropertyFun   objectFun", two.toString())
//        09-06 15:17:09.350 21623-21623/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   objectFun: com.example.edz.myapplication.ExtendActivity$One@ac6abdc
//        09-06 15:17:09.350 21623-21623/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   objectFun: com.example.edz.myapplication.ExtendActivity$One@ac6abdc
        //one 和 two 是同一个对象,那么后面的属性 会覆盖前面的属性如下
        //设置属性
        one.name = "单例模式第一个名字"
        two.name = "单例模式第二个名字"
        //也可以用这样调用{类.变量}
        One.name
        Log.d("ExtendActivity。entrustPropertyFun    one.name", one.name)
        Log.d("ExtendActivity。entrustPropertyFun    two.name", two.name)
//        09-06 15:17:09.350 21623-21623/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun    one.name: 单例模式第二个名字
//        09-06 15:17:09.350 21623-21623/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun    two.name: 单例模式第二个名字
        /**
         * object创建匿名内部类
         */
//        TODO()目前还不知道 为什么自己定义的匿名内部类不能用lambda表达式
        var buttonView = ButtonView()
        buttonView.setOnclick(object : OnclickListen {
            override fun onclick() {
            }
        })


        /***
         * object继承的类
         * 可以写成变量的方式
         */
        val value: ExtendActivity.ObjectClass = object : ObjectClass("object继承") {
            override fun setFirstName() {
                Log.d("ExtendActivity。entrustPropertyFun    setFirstName", "子类")
            }
        }
        val value2: ExtendActivity.ObjectClass = object : ObjectClass("object继承") {
            override fun setFirstName() {
                Log.d("ExtendActivity。entrustPropertyFun    setFirstName", "子类")
            }
        }
        Log.d("ExtendActivity。entrustPropertyFun   value", value.toString())
        Log.d("ExtendActivity。entrustPropertyFun   value2", value2.toString())
        value.setFirstName()
//        09-06 15:53:50.388 9122-9122/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   value: com.example.edz.myapplication.ExtendActivity$objectFun$value$1@deef4b0
//        09-06 15:53:50.388 9122-9122/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   value2: com.example.edz.myapplication.ExtendActivity$objectFun$value2$1@d6bff29
//        09-06 15:53:50.388 9122-9122/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun    setFirstName: 子类
        /***
         * object声明的对象，除了实现某一个接口、继承某一个类，还可以既不实现接口，也不继承类
         * 局部变量声明为一个对象
         */
        //person 理解为一个匿名类的对象
        var peron = object {
            var name = ""
            var age = 19
        }
        Log.d("ExtendActivity。entrustPropertyFun   peron name", peron.name)
        Log.d("ExtendActivity。entrustPropertyFun   peron age", peron.age.toString())
//        09-06 15:58:50.108 13647-13647/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun    setFirstName: 子类
//        09-06 15:58:50.108 13647-13647/com.example.edz.myapplication D/ExtendActivity。entrustPropertyFun   peron age: 19
        /***
         * 创建伴生对象
         * 不是静态
         * 可以继承和实现接口
         */
//        伴身对象内的方法访问可以有【外部类.内部类.方法()】的形式以及【外部类.方法()】
//        伴身对象内的属性访问可以有【外部类.内部类.属性】的形式以及【外部类.属性】
        CompanionClass().comFun()
        CompanionClass.age
        CompanionClass.name
        CompanionClass.staticName

        CompanionClass.B.age
        CompanionClass.B.name
        CompanionClass.B.staticName
        CompanionClass.onclick()

    }

    object One {
        var name = "单例模式"
    }

    interface OnclickListen {
        fun onclick()
    }

    class ButtonView {
        fun setOnclick(listen: OnclickListen) {
            listen.onclick()
        }
    }

    open class ObjectClass(var name: String = "") {
        open fun setFirstName() {
            Log.d("ExtendActivity。entrustPropertyFun    setFirstName", "父类")
        }
    }

    class CompanionClass {
        //伴生对象可以不用命名 ==B可以省略
        companion object B : OnclickListen {
            override fun onclick() {
                Log.d("ExtendActivity。entrustPropertyFun    companion object", "伴身对象可以有自己的方法和属性，甚至可以实现接口，继承类")
            }

            var age = 20
            var name = "非静态"
            //静态变量的声明方式
            @JvmStatic
            var staticName = "静态变量"
        }

        fun comFun() = name
    }


}



