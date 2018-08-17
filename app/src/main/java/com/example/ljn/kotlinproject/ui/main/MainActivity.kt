package com.example.ljn.kotlinproject.ui.main

import com.example.ljn.kotlinproject.R
import com.example.ljn.kotlinproject.base.BaseActivity
import com.example.ljn.kotlinproject.base.BaseBean
import com.example.ljn.kotlinproject.ui.AnkoActivity
import com.safframework.log.L
import io.objectbox.internal.JniTest
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.singleTop

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {

    private var msg: String = "SUCCESS"
    override fun showError(msg: String) {
        L.i("sssssss4")
    }

    override fun showMsg() {
        L.i("msg")
    }

    override fun printMsg() {
        L.i("msg")
    }

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val list2 = listOf(0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0)
    private val list3 = listOf(0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, -1)
    private val list4 = listOf(0, 1, 2, 3, 4, 5)
    private val list5 = listOf(4, 5, 2, 1, 5)
    override fun initEventAndData() {

        //区间的定义
        val range = 1..100
        val range2 = IntRange(1, 100)
        val range3 = 1.rangeTo(100)

        val add: (Int, Int) -> Int = { a, b -> a + b }
        add(1, 2)
        add.invoke(1, 2)
        mPresenter?.getData("")
        text_ljn.text = msg
        //点击事件
        text_ljn.onClick {
            //传参数+flags
            startActivity(intentFor<AnkoActivity>("id" to 5).singleTop())
            // startActivity<AnkoActivity>()
        }

        functionTest()
        //collectionUse()
    }

    private fun collectionUse() {
        //https://www.jianshu.com/p/8f32de00c5dc
        //总数操作符
        //any 只要有一个符合就返回true
        val any = list.any { it > 8 }
        L.i(any.toString())
        //all 所有条件符合才返回true
        val all = list.all { it > 0 }
        L.i(all.toString())
        //count 返回符合条件的数目
        val count = list.count { it > 5 }
        L.i(count.toString())
        //none 如果没有任何元素与给定的函数匹配，则返回true
        val none = list.none { it > 10 }
        L.i(none.toString())
        /***********************************我是分割线*********************************************************/
        //fold 在一个初始值的基础上 从第一项到最后一项通过 一个函数操作所有的元素。
        //下面是初始值4 每项进行累加 4+0.....9
        val fold = list.fold(4) { total, next -> total + next }
        L.i(fold.toString())
        //foldRight与fold一样，但是顺序是从最后一项到第一项。注意与fold的区别，参数位置调过来了
        val foldRight = list.foldRight(4) { next, total -> total + next }
        L.i(foldRight.toString())

        //reduce 从第一项到最后一项通过 一个函数操作 所有的元素，相对于fold，没有初始值
        //reduceRight 是从后到前
        val reduce = list.reduce { acc, i -> acc + i }
        L.i(reduce.toString())
        //forEach 遍历每个元素并且进行操作
        val foreach = list.forEach { println(it) }
        //forEachIndexed 与foreach相同，但是可以得到index
        val forEachIndexed = list.forEachIndexed { index, value -> println("$index -> $value") }

        //max 返回最大的值，如果没有则返回null min同
        val max = list.max()
        L.i(max.toString())
        //maxBy 根据指定的函数返回最大值 minBy同
        val maxBy = list.maxBy { -it }
        L.i(maxBy.toString())
        //sumBy 每项经过函数转换后的和
        val sumBy = list.sumBy { it + 9 }
        L.i(sumBy.toString())

        //过滤操作符

        /**
         * drop 返回包含去掉前n个元素的所有元素的列表
         * Returns a list containing all elements except first [n] elements.
         * 返回[4, 5, 6, 5, 4, 3, 2, 1, 0]
         */
        val drop = list2.drop(4)
        L.i(drop.toString())

        /**
         * dropwhile 根据特定的函数 从第一项开始 直到不满足条件后返回 列表
         * Returns a list containing all elements except first elements that satisfy the given [predicate].
         * 返回[0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0]
         */
        val dropwhile = list2.dropWhile { it > 1 }
        L.i(dropwhile.toString())

        /**
         * dropLastWhile 返回根据特定的函数 从最后一项开始 直到不满足条件后返回 列表
         * Returns a list containing all elements except last elements that satisfy the given [predicate].
         * 返回[0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0]
         */
        val dropLastWhile = list2.dropLastWhile { it > 4 }
        L.i(dropLastWhile.toString())


        /**
         *filter 返回所有符合给定函数条件的元素。
         * Returns a list containing only elements matching the given [predicate].
         * [5, 6, 5]
         */
        val filter = list2.filter { it > 4 }
        L.i(filter.toString())

        /**
         * filterNot 返回所有不符合给定函数条件的元素
         * Returns a list containing all elements not matching the given [predicate].
         * [0, 1, 2, 3, 4, 4, 3, 2, 1, 0]
         */
        val filterNot = list2.filterNot { it > 4 }
        L.i(filterNot.toString())

        /**
         * 返回满足该ranger的元素集合
         * Returns a list containing elements at indices in the specified [indices] range.
         * [0, 1, 2, 3, 4, 5, 6]
         */
        val slice = list2.slice(0..6)
        L.i(slice.toString())


        /**
         * listOf(0,4,7)是集合list的坐标
         * Returns a list containing elements at specified [indices].
         * [0, 4, 5]
         */
        val slice2 = list2.slice(listOf(0, 4, 7))
        L.i(slice2.toString())

        /**
         *返回前n项
         * Returns a list containing first [n] elements.
         * [0, 1, 2, 3]
         */
        val take = list2.take(4)
        L.i(take.toString())
        /**
         * 返回后n项
         * Returns a list containing last [n] elements.
         * [3, 2, 1, 0]
         */
        val takeLast = list2.takeLast(4)
        L.i(takeLast.toString())
        /**
         * 从第一项开始判断，直到不符合就返回，返回符合的前几项数据
         * Returns a list containing first elements satisfying the given [predicate].
         * []
         */
        val takeWhile = list2.takeWhile { it > 4 }
        L.i(takeWhile.toString())

        //映射操作符

        /**
         * 返回满足条件的集合
         * Returns a list containing the results of applying the given [transform] function
         * to each element in the original collection.
         * [false, false, false, true, true, true, true, true, false, false, false, false]
         */
        val map = list3.map { it > 2 }
        L.i(map.toString())

        /**
         * 返回特定函数后的集合，参数是Iterable类型，
         * Returns a single list of all elements yielded from results of [transform]
         * function being invoked on each element of original collection.
         * [0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 4, 5, 3, 4, 2, 3, 1, 2, 0, 1, -1, 0]
         * 0,1,2,3,4,5,4,3,2,1,0,-1  it=0....-1
         */
        val flatMap = list3.flatMap { listOf(it, it + 1) }
        L.i(flatMap.toString())

        /**
         * 根据函数将集合分组，返回map类型对象
         * Groups elements of the original collection by the key returned by the given [keySelector] function
         * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
         *
         * The returned map preserves the entry iteration order of the keys produced from the original collection.
         * {false=[0, 1, 2, 3, 3, 2, 1, 0, -1], true=[4, 5, 4]}
         *
         * @sample samples.collections.Collections.Transformations.groupBy
         */
        val groupBy = list3.groupBy { value -> value > 3 }
        L.i(groupBy.toString())


        /**
         * 返回一个集合，通过 角标和值 来生成
         * Returns a list containing the results of applying the given [transform] function
         * to each element and its index in the original collection.
         * @param [transform] function that takes the index of an element and the element itself
         * and returns the result of the transform applied to the element.
         * [0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, -1]
         */
        val mapIndexed = list3.mapIndexed { index, value -> value }
        L.i(mapIndexed.toString())

        //元素操作符

        //如果指定元素可以在集合中找到，则返回true。
        val contains = list.contains(2)
        /**
         * 返回给定index对应的元素，如果index数组越界则会 抛出IndexOutOfBoundsException
         * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this collection.
         * 2
         */
        val elementAt = list.elementAt(2)

        /**
         * 返回给定index对应的元素，如果index数组越界则会根据给定函数返回默认值,第二个参数default，lamdba表达式
         * Returns an element at the given [index] or the result of calling the [defaultValue]
         * function if the [index] is out of bounds of this collection.
         * 2
         */
        val elementAtOrElse = list.elementAtOrElse(2) { "error" }

        /**
         * 返回给定index对应的元素，如果index数组越界则会 返回null
         * Returns an element at the given [index] or `null` if the [index] is out of bounds of this list.
         * null
         */
        val elementAtOrNull = list.elementAtOrNull(19)

        /**
         * Returns first element.
         * @throws [NoSuchElementException] if the list is empty.
         * 0
         */
        val first = list.first()

        /**
         * 返回符合给定函数条件的第一个元素,没有回抛异常
         * Returns the first element matching the given [predicate].
         * @throws [NoSuchElementException] if no such element is found.
         * 4
         */
        val first2 = list.first { it > 3 }


        /**
         * 返回符合给定函数条件的第一个元素，如果没有符合则返回null
         * Returns the first element matching the given [predicate], or `null` if element was not found.
         * null
         */
        val firstOrNull = list.firstOrNull { it > 9 }

        /**
         * 返回指定元素的第一个index，如果不存在，则返回-1
         * Returns the index of the first occurrence of the specified element in the list, or -1 if the specified
         * element is not contained in the list.
         * 3
         */
        val indexOf = list.indexOf(3)

        /**
         * 返回第一个符合给定函数条件的元素的index，如果没有符合则返回-1
         * Returns index of the first element matching the given [predicate], or -1 if the list does not contain such element.
         * 0
         */
        val indexOfFirst = list.indexOfFirst { it % 3 == 0 }

        /**
         * 返回最后一个符合给定函数条件的元素的index，如果没有符合则返回-1
         * Returns index of the last element matching the given [predicate], or -1 if the list does not contain such element.
         * 11
         */
        val indexOfLast = list.indexOfLast { it % 3 == 0 }

        /**
         * 返回符合给定函数条件的最后一个元素,没有抛异常
         * Returns the last element matching the given [predicate].
         * @throws [NoSuchElementException] if no such element is found.
         * 6
         */
        val last = list.last { it > 4 }

        /**
         * 返回指定元素的最后一个index，如果不存在，则返回-1
         * Returns the index of the last occurrence of the specified element in the list, or -1 if the specified
         * element is not contained in the list.
         * 8
         */
        val lastIndexOf = list.lastIndexOf(3)

        /**
         * 返回符合给定函数条件的最后一个元素，如果没有符合则返回null
         * Returns the last element matching the given [predicate], or `null` if no such element was found.
         * null
         */
        val lastOrNull = list.lastOrNull { it > 8 }


        /**
         * 返回符合给定函数的单个元素，如果没有符合或者超过一个，则抛出异常
         * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
         * 6
         */
        val single = list.single { it > 5 }


        /**
         * 返回符合给定函数的单个元素，如果没有符合或者超过一个，则返回null
         * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
         * null
         */
        val singleOrNull = list.singleOrNull { it > 8 }

        //生产操作符
        /**
         * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
         * [(0, 4), (1, 5), (2, 2), (3, 1), (4, 5)]
         */
        val zip = list4.zip(list5)

        /**
         * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
         * [4, 6, 4, 4, 9]
         */
        val zip2 = list4.zip(list5) { it1, it2 -> it1 + it2 }

        //[(0, 0), (1, 1), (2, 2), (3, 3), (4, 4), (5, 5)]
        val zip4 = list4.zip(list4 + list5)

        //[0, 2, 4, 6, 8, 10]
        val zip3 = list4.zip(list4 + list5) { it1, it2 -> it1 + it2 }

        /**
         * Returns a pair of lists, where
         * *first* list is built from the first values of each pair from this collection,
         * *second* list is built from the second values of each pair from this collection.
         * ([1, 3, 7], [3, 4, 8])
         */
        val unzip = listOf(Pair(1, 3), Pair(3, 4), Pair(7, 8)).unzip()

        /**
         * 把一个给定的集合分割成两个，第一个集合是由原集合每一项元素匹配给定函数条件返回true的元素组成，
         * 第二个集合是由原集合每一项元素匹配给定函数条件返回false的元素组成
         * Splits the original collection into pair of lists,
         * where *first* list contains elements for which [predicate] yielded `true`,
         * while *second* list contains elements for which [predicate] yielded `false`.
         * ([0, 2, 4], [1, 3, 5])  value
         */
        val partition = list4.partition { it % 2 == 0 }

        /**
         * 返回一个包含原集合和给定集合中所有元素的集合，因为函数的名字原因，我们可以使用+操作符。
         * Returns a list containing all elements of the original collection and then all elements of the given [elements] sequence.
         * [0, 1, 2, 3, 4, 5, 4, 5, 2, 1, 5]
         */
        val plus = list4.plus(list2)

        // 顺序操作符

        /**
         * 返回一个与指定list相反顺序的list
         * Returns a list with elements in reversed order.
         */
        val reverse = list.reversed()

        /**
         * 返回一个自然排序后的list
         * Returns a list of all elements sorted according to their natural sort order.
         */
        val sort = list.sorted()

        /**
         * 返回一个根据指定函数排序后的list
         * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
         */
        val sortBy = list.sortedBy { it > 3 }

        /**
         * 返回一个降序排序后的List
         * Returns a list of all elements sorted descending according to their natural sort order.
         */
        val sortDescending = list.sortedDescending()

        /**
         * 返回一个根据指定函数降序排序后的list
         * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
         */
        val sortDescendingBy = list.sortedByDescending { it > 4 }
    }


    private fun functionTest() {
        //1.run 仅仅用于执行一个代码块
        val x = kotlin.run {
            L.i("Hello world")
            return@run 1
        }
        L.i(x.toString())

//可以用做类型转换
        val y = "A:B:C:D:E:F".run {
            substring(2)
            return@run split(substring(2))
        }

        L.i(y.toString())

        //2. with 传递的是对象直接操作属性 函数等
        val a = A()
        with(a) {
            sayHello()
        }
        //3.apply
        a.apply {
            println("This is a block")
            sayHello()
        }.other()
        //4.also 通with
        //5.let 相当于 RxJava  map 可以转换类型
        "A:B:C:D:E:F".let {
            it.substring(5)
            return@let A()
        }

        a?.let {
            //表示object不为null的条件下，才会去执行let函数体
            it.sayHello()
        }
        //6.takeIf 筛选集合中某个数据是否符合要求
        val arr = listOf(1, 2, 3)
        arr.forEach {
            println("$it % 2 == 0 => ${it.takeIf { it % 2 == 0 }}")
            println("$it % 2 == 0 => ${it.takeUnless { it % 2 == 0 }}")
        }
        //7.takeUnless 如果符合条件返回null，不符合条件返回对象本身
        //8.repeat 将一个动作重复指定的次数
        repeat(3) {
            println("Just repeat, index: $it")
        }
    }


    class A {
        fun sayHello() {}
        fun other() {
            println("Other function...")
        }
    }

    /**
     * 定义二元元祖  三元元祖 triple
     */
    private fun pair() {
        val pair = Pair("ljn", 30)
        val pair2 = "ljn" to 27
        print(pair.first + pair.second)
    }

    /**
     * 递归
     */
    private fun sum(n: Int): Int {
        return if (n == 1) {
            1
        } else {
            n + sum(n - 1)//做了+n操作 不是尾递归
        }
    }

    /**
     * 尾递归优化 原理:转化为迭代
     */
    private tailrec fun tailSum(n: Int, result: Int = 0): Int {
        return if (n == 1) {
            result + 1
        } else {
            tailSum(n - 1, result + n)
        }
    }


    /**
     * 解决泛型擦除
     */

    private inline fun <reified T> parseType(t: T) {
        T::class.java
    }
}
