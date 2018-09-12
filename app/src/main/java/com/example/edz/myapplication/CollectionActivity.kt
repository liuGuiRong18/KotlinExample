package com.example.edz.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_collection.*
import java.util.TreeSet

/***
 * 1.区间介绍&表示
 * 2.区间遍历
 * 3.区间常见方法和属性
 * 4.数组介绍&创建&遍历
 * 5.数组常见方法和属性
 * 6.数组变化
 * 7.集合（list set map）概述
 */
class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setSupportActionBar(toolbar)
        rangeDetails()
        rangeTraverse()
        rangeFunAndVariable()
        arrayDetails()
        arrayFunAndVariable()
        arrayChange()
        collectionDetails()
        listCollectionDetails()
        setCollectionDetails()
        mapCollectionDetails()

    }


    /***
     * 1.区间的介绍
     */
    @SuppressLint("LongLogTag") private fun rangeDetails() {
        var range1 = 1..10
        var range2 = 1 until 10
        var range3 = 1.rangeTo(10)
        var range4 = IntRange(1, 10)
        Log.d("CollectionActivity。rangeDetials   range1", range1.toList().toString())
        Log.d("CollectionActivity。rangeDetials   range2", range2.toList().toString())
        Log.d("CollectionActivity。rangeDetials   range3", range3.toList().toString())
        Log.d("CollectionActivity。rangeDetials   range4", range4.toList().toString())
//        09-07 10:24:50.651 23815-23815/com.example.edz.myapplication D/CollectionActivity。rangeDetials   range1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//        09-07 10:24:50.651 23815-23815/com.example.edz.myapplication D/CollectionActivity。rangeDetials   range2: [1, 2, 3, 4, 5, 6, 7, 8, 9]
//        09-07 10:24:50.651 23815-23815/com.example.edz.myapplication D/CollectionActivity。rangeDetials   range3: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//        09-07 10:24:50.651 23815-23815/com.example.edz.myapplication D/CollectionActivity。rangeDetials   range4: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    /***
     * 2. 区间遍历
     */
    @SuppressLint("LongLogTag") private fun rangeTraverse() {
        var range = 'a'..'d'
        for (char in range) {
            Log.d("CollectionActivity。rangeTraverse   range", char.toString())
        }
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: a
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: b
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: c
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: d
        for (char in range step 2) {
            Log.d("CollectionActivity。rangeTraverse   range step", char.toString())
        }
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range step: a
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range step: c
        for (withIndex in range.withIndex()) {
            Log.d("CollectionActivity。rangeTraverse   range", withIndex.index.toString())
            Log.d("CollectionActivity。rangeTraverse   range", withIndex.value.toString())
        }
        for ((index, value) in range.withIndex()) {
            Log.d("CollectionActivity。rangeTraverse   range", index.toString())
            Log.d("CollectionActivity。rangeTraverse   range", value.toString())
        }

//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: 0
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: a
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: 1
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: b
//        09-07 10:36:10.241 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: 2
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: c
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: 3
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   range: d
        //高阶函数
        range.forEach { Log.d("CollectionActivity。rangeTraverse   forEach", it.toString()) }
        range.forEachIndexed { index, c ->
            Log.d("CollectionActivity。rangeTraverse   forEachIndexed", "$index==>$c")
        }
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEach: a
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEach: b
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEach: c
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEach: d
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEachIndexed: 0==>a
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEachIndexed: 1==>b
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEachIndexed: 2==>c
//        09-07 10:36:10.242 4876-4876/com.example.edz.myapplication D/CollectionActivity。rangeTraverse   forEachIndexed: 3==>d
    }

    /****
     * 3.区间常见方法和属性
     */
    @SuppressLint("LongLogTag") private fun rangeFunAndVariable() {
        var range = 'a'..'d'
        Log.d("CollectionActivity。rangeFunAndVariable   first", "${range.first}")
        Log.d("CollectionActivity。rangeFunAndVariable   start", "${range.start}")
        Log.d("CollectionActivity。rangeFunAndVariable   last", "${range.last}")
        Log.d("CollectionActivity。rangeFunAndVariable   contains", "${range.contains('a')}")
        Log.d("CollectionActivity。rangeFunAndVariable   contains", "${range.toList()}")
    }

    /****
     * 4.数组介绍&创建&遍历
     *通过Array类的构造方法创建数组的情况还比较少，主要创建一些有规律性的元素。比如，想创建一个集合长度为5，集合元素包含[0,2,4,6,8,10]或者[0,3,6,9,12,15]这样规律性的元素的时候，用Array类的构造方法就显得方便一些。
     */
    @SuppressLint("LongLogTag") private fun arrayDetails() {
//        arrayOf方法创建数组
        var intArray = arrayOf(1, 2, 3)//int 数组
        var strArray = arrayOf("a", "b")//String 数组
        var studentArray = arrayOf(Student(1, "小米"), Student(2, "晓东"))//对象数组
        var anyArray = arrayOf("a", "b", Student(2, "晓东"))//any 数组

        Log.d("CollectionActivity。arrayDetails   anyArray", "${anyArray.toList()}")
        Log.d("CollectionActivity。arrayDetails   anyArray", "${anyArray.size}")
        Log.d("CollectionActivity。arrayDetails   anyArray", "${anyArray.get(1)}")
//        09-07 10:53:59.456 30631-30631/com.example.edz.myapplication D/CollectionActivity。arrayDetails   anyArray: [a, b, com.example.edz.myapplication.CollectionActivity$Student@ac6abdc]
//        09-07 10:53:59.456 30631-30631/com.example.edz.myapplication D/CollectionActivity。arrayDetails   anyArray: 3
//        09-07 10:53:59.456 30631-30631/com.example.edz.myapplication D/CollectionActivity。arrayDetails   anyArray: b
        //Array 创造一些有规律的数组
        var arr1 = Array(5) {
            2 * it
        }
        var arr2 = Array(5) {
            3 * it
        }
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: [0, 2, 4, 6, 8]
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr2: [0, 3, 6, 9, 12]
        Log.d("CollectionActivity。arrayDetails   arr1", "${arr1.toList()}")
        Log.d("CollectionActivity。arrayDetails   arr2", "${arr2.toList()}")
        //遍历多一种 indices
        for (index in arr1.indices) {
            Log.d("CollectionActivity。arrayDetails   arr1", "$index==>${arr1[index]}")
        }
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: 0==>0
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: 1==>2
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: 2==>4
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: 3==>6
//        09-07 11:03:03.727 4735-4735/com.example.edz.myapplication D/CollectionActivity。arrayDetails   arr1: 4==>8
    }

    class Student(var age: Int, var name: String)

    /***
     * 5.数组常见方法和属性
     * 集 合可以理解为是一个容器，容器操作我们自然想到“增删改查”，
     * 集合、数组元素是定长的，所以没有“增”和“删”，我们来看看“改”和“查”。
     */
    @SuppressLint("LongLogTag") private fun arrayFunAndVariable() {
        var strArray = arrayOf("a", "b", "c")
        //修改1.下标修改元素2.set修改元素
        strArray[0] = "通过下标修改了"
        strArray.set(2, "通过set修改了")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray", "${strArray.toList()}")
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray: [通过下标修改了, b, 通过set修改了]

// 数组获取指定位置元素可以用下标获取、get方法获取、elementAt方法获取。如果是特殊位置的获取，
// 比如第一个位置，最后一个位置，我们可以直接使用提供好的first、last方法。
// 为了安全起见，还可以使用getOrNull去获取元素，防止出现数组越界。
        Log.d("CollectionActivity。arrayFunAndVariable   strArray[2]", "${strArray[2]}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.get(2)", "${strArray.get(2)}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.elementAt(2)", "${strArray.elementAt(2)}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.getOrNull(10)", "${strArray.getOrNull(10)}")//防止 下标越界返回null
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.last()", "${strArray.last()}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.first()", "${strArray.first()}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.contains(\"b\")", "${strArray.contains("b")}")
        strArray.reverse()//反转元素：reverse方法
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.toList(", "${strArray.toList()}")
        val indexOf = strArray.indexOf("2")//indexOf()获取元素对应的位置，如果数组中不存在该元素返回-1
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.size", "${strArray.size}")
        Log.d("CollectionActivity。arrayFunAndVariable   strArray.count()", "${strArray.count()}")
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray[2]: 通过set修改了
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.get(2): 通过set修改了
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.elementAt(2): 通过set修改了
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.getOrNull(10): null
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.last(): 通过set修改了
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.first(): 通过下标修改了
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.contains("b"): true
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.toList(: [通过set修改了, b, 通过下标修改了]
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.size: 3
//        09-07 11:23:06.621 15469-15469/com.example.edz.myapplication D/CollectionActivity。arrayFunAndVariable   strArray.count(): 3
    }

    /****
     * 6.数组变化
     * 1.追加复制集合：plusElement一个数组的长度是固定的，我们没法会其添加元素，
     * 但是，kotlin提供了一个方法plusElement，可以对原集合添加元素之后，变成一个新的集合，这样的操作在java里面是没有的
     * 2.一个数组的长度是固定的，我们没法删除其元素，
     * 但是，kotlin提供了2个方法drop和dropLast。
     * drop方法接收一个Int类型参数，表示可以指定从数组开始位置移除原数组的几个元素。
     * dropLast，方法接收一个Int类型参数，表示可以指定从数组末尾位置移除原数组的几个元素
     * 3.分割：slice方法
     * slice英语单词是“切分”的意思，sliceArray ()方法位表示可以对原数组进行切分，返回一个新的数组。
     */
    @SuppressLint("LongLogTag") private fun arrayChange() {
        var intArr: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)
        val plusElement = intArr.plusElement(7)
        Log.d("CollectionActivity。arrayChange   plusElement", "${plusElement.toList()}")
//        09-07 14:11:17.629 6901-6901/com.example.edz.myapplication D/CollectionActivity。arrayChange   plusElement: [1, 2, 3, 4, 5, 6, 7]
        val drop = plusElement.drop(1)
        val dropLast = plusElement.dropLast(2)
        Log.d("CollectionActivity。arrayChange   drop", "${drop.toList()}")
        Log.d("CollectionActivity。arrayChange   dropLast", "${dropLast.toList()}")
//        09-07 14:11:17.630 6901-6901/com.example.edz.myapplication D/CollectionActivity。arrayChange   drop: [2, 3, 4, 5, 6, 7]
//        09-07 14:11:17.630 6901-6901/com.example.edz.myapplication D/CollectionActivity。arrayChange   dropLast: [1, 2, 3, 4, 5]
        val sliceArray = intArr.sliceArray(0..4)
        val sliceArray2 = intArr.sliceArray(2..4)
        Log.d("CollectionActivity。arrayChange   sliceArray", "${sliceArray.toList()}")
        Log.d("CollectionActivity。arrayChange   sliceArray2", "${sliceArray2.toList()}")
//        09-07 14:11:17.630 6901-6901/com.example.edz.myapplication D/CollectionActivity。arrayChange   sliceArray: [1, 2, 3, 4, 5]
//        09-07 14:11:17.630 6901-6901/com.example.edz.myapplication D/CollectionActivity。arrayChange   sliceArray2: [3, 4, 5]
    }

    /****
     * 7.集合分类
     * 同Java，集合分为三种类型，分别是List、Set和Map集合。分别对应了List、Set和Map三个接口。其中List、Set接口默认实现了Collection接口,Map接口没有实现任何其他接口。
     * List、Set和Map集合各自特点和区别是什么呢？我们通过一个表列举下：
     * List（1）实现Collection接口 （2）存储的元素有序可重复 （3）常用子类ArrayList、LinkedList等
     * Set （1）实现Collection接口 （2）存储的元素有序不重复 （3）常用子类HashSet、LinkedSet
     * Map （1）不实现Collection接口 （2）存储的是键值对key-value （3）存储的元素无序可重复 （4）常用子类HashMap、LinkedHashMap
     * 集合可写&不可写
     * Java中集合和Kotlin中的集合大同小异，
     * 两者有一个非常大的区别就是，Java中的List、Set、Map集合都是可读可写的。
     * Kotlin中List、Set、Map的集合在读方面都是可读的，在写方面则分为可写和不可写集合。
     * Kotlin中的List、Set集合都实现了Collection接口，但Kotlin中的Collection接口并没有包含add方法
     * 那add方法定义到哪里去了呢？Kotlin为Collection新增一个子接口MutableCollection，add方法定义到了MutableCollection中
     * 使用MutableList、MutableSet、MutableMap创建的集合都是可写的， Mutable的意思本来也是“易变的，性情不定的”。
     */
    private fun collectionDetails() {
    }

    /*****
     * list
     * List接口常用的实现类有哪些？ ArrayList、LinkedList和MutableList。
     * 集合可以理解为是一个容器，容器操作我们自然想到“增删改查”，但是需要注意可写集合才有“增删改查”，不可写集合只有“查”。
     *
     */
    @SuppressLint("LongLogTag") private fun listCollectionDetails() {
        //创建list四种方法
        var listOne = List(3, { it * 3 })//read-only 不可写
        var listTwo = listOf(1, 2, 3)//read-only 不可写
        var listThree = arrayListOf(1, 2, 3)// 可写
        var listFour = mutableListOf(1, 2, 3, 4)// 可写

        listThree.add(4)//可写
        listFour.add(5)//可写
        var add = listOne.toMutableList()//转为可写
        var add1 = listTwo.toMutableList()//转为可写
        add.add(3)
        add1.add(3)
        Log.d("CollectionActivity。arrayChange   listCollectionDetails listOne", "${listOne.toList()}")
        Log.d("CollectionActivity。arrayChange   listCollectionDetails listTwo", "${listTwo.toList()}")
        Log.d("CollectionActivity。arrayChange   listCollectionDetails listThree", "${listThree.toList()}")
        Log.d("CollectionActivity。arrayChange   listCollectionDetails listFour", "${listFour.toList()}")
        Log.d("CollectionActivity。arrayChange   listCollectionDetails add", "${add.toList()}")
        Log.d("CollectionActivity。arrayChange   listCollectionDetails add1", "${add1.toList()}")
//        09-07 14:48:07.175 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails listOne: [0, 3, 6]
//        09-07 14:48:07.175 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails listTwo: [1, 2, 3]
//        09-07 14:48:07.175 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails listThree: [1, 2, 3, 4]
//        09-07 14:48:07.175 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails listFour: [1, 2, 3, 4, 5]
//        09-07 14:48:07.176 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails add: [0, 3, 6, 3]
//        09-07 14:48:07.176 29708-29708/com.example.edz.myapplication D/CollectionActivity。arrayChange   listCollectionDetails add1: [1, 2, 3, 3]
//        list集合常见方法和属性
//       增删改查
        var list = mutableListOf(1, 2, 3, 4)//可读 可写
        list.add(0, 0)
        list.addAll(0, arrayListOf(1, 2, 3))
//        list.removeAt(0)
//        list.removeAll(arrayListOf(1, 2, 3))
        list.set(0, 1)
        list[2] = 2
        val get2 = list[2]
        val get = list.get(2)
        list.getOrNull(99)
        list.indexOf(2)
        list.contains(2)
        list.size

//        可以通过截取slice、subList、删除drop、dropLast对集合进行变换，产生一个新的集合。
    }

    /***
     *  set集合存储的元素无序不可重复
     *  Set接口常用的实现类有哪些？HashSet、TreeSet和MutableSet。
     *
     */
    @SuppressLint("LongLogTag") private fun setCollectionDetails() {
        var setOne = setOf(1, 2, 3)// 不可写 无序
        var setTwo = hashSetOf(1, 2, 3) // 可写 无序
        var setThree = mutableSetOf(1, 2, 3) // 可写 无序
        var setFour = TreeSet<Int>()// 可写 无序
        setFour.add(2)
        setFour.add(1)
        setFour.add(3)
        setTwo.add(4)
        setThree.add(4)
        val toMutableSet = setOne.toMutableSet()
        toMutableSet.add(4)
        Log.d("CollectionActivity。arrayChange   setCollectionDetails setOne", "${setOne.toList()}")
        Log.d("CollectionActivity。arrayChange   setCollectionDetails setTwo", "${setTwo.toList()}")
        Log.d("CollectionActivity。arrayChange   setCollectionDetails setThree", "${setThree.toList()}")
        Log.d("CollectionActivity。arrayChange   setCollectionDetails setFour", "${setFour.toList()}")
        Log.d("CollectionActivity。arrayChange   setCollectionDetails toMutableSet", "${toMutableSet.toList()}")
//        09-07 15:15:01.631 17761-17761/com.example.edz.myapplication D/CollectionActivity。arrayChange   setCollectionDetails setOne: [1, 2, 3]
//        09-07 15:15:01.631 17761-17761/com.example.edz.myapplication D/CollectionActivity。arrayChange   setCollectionDetails setTwo: [4, 1, 3, 2]
//        09-07 15:15:01.631 17761-17761/com.example.edz.myapplication D/CollectionActivity。arrayChange   setCollectionDetails setThree: [1, 2, 3, 4]
//        09-07 15:15:01.631 17761-17761/com.example.edz.myapplication D/CollectionActivity。arrayChange   setCollectionDetails setFour: [1, 2, 3]
//        09-07 15:15:01.631 17761-17761/com.example.edz.myapplication D/CollectionActivity。arrayChange   setCollectionDetails toMutableSet: [1, 2, 3, 4]
        //增删改查
        //增删和list一样
//        修改元素：不支持
//        查询元素 set集合根据索引位置获取元素，不是通过get方法，通过elementAt方法和elementAtOrNull方法。
        val elementAt = setThree.elementAt(0)
        val elementAtOrNull = setThree.elementAtOrNull(99)
//        set集合变换
//        set集合的变换，如果是HashSet，有一个drop方法。
//       如果是TreeSet可以使用headSet、subSet、tailSet三个方法进行变换。
//        headSet(EtoElement)，对集合截取，返回一个新的集合，截取范围[开始，toElement)
//        subSet(EfromElement,E toElement)，对集合截取，返回一个新的集合，截取范围[fromElement，toElement)
//        tailSet(EfromElement)，对集合截取，返回一个新的集合，[fromElement,结尾]
    }

    /**
     * map集合介绍
     * Map接口常用的实现类有哪些？ HashMap、LinkedHashMap、TreeMap和MutableHashMap。
     * HashMap，基于Map接口哈希表实现，通过哈希表对其内部的映射关系快速查找，存取效率高，迭代无序。
     * LinkedHashMap，基于Map接口哈希表实现和链接列表实现，链接列表定义了迭代顺序，该迭代顺序可以是插入顺序或者是访问顺序。
     * TreeMap ，基于红黑树实现的，迭代根据其键的自然顺序进行排序，或者可以自行指定比较器。
     */
    private fun mapCollectionDetails() {
      //  mapOf()、hashMapOf ()、mutableMapOf ()中包含的Pair类型的元素，**除了通过Pair的构造方法创建，还可以通过to方法。
    }


}
