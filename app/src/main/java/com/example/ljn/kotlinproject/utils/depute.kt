package com.example.ljn.kotlinproject.utils

import kotlin.reflect.KProperty

/**
@description:  委托
@author:  ljn
@time:  2018/8/13
 */
//能力
interface WashPower {
    fun wash()
}

class BigHeadSon : WashPower {
    override fun wash() {
        print("儿子洗完")
    }
}

//洗碗委托给 儿子
class SmallHeadFather : WashPower by BigHeadSon()

//2
class SmallHeadFather2(var washPower: WashPower) : WashPower by washPower {
    override fun wash() {
        print("委托加强")
        washPower.wash()
        print("委托加强")
    }
}


//委托属性
class Son {
    //钱被你妈保管啦
    var money: Int by Mother()
}

class Mother {
    //给我压岁钱
    operator fun getValue(son: Son, property: KProperty<*>): Int {
        return sonMoney
    }

    //存储压岁钱 i设置的值 ex:100
    operator fun setValue(son: Son, property: KProperty<*>, i: Int) {
        //各存50
        sonMoney += 50
        mySelfMoney += i - 50
    }

    var sonMoney = 0
    var mySelfMoney = 0
}