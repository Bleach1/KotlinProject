package com.example.ljn.kotlinproject.base

import io.objectbox.annotation.*
import io.objectbox.relation.ToOne

data class BaseBean<T>(var code: Int, var msg: String, var data: T)
data class Data(var id: Int)

data class TestBean(var errorMsg: String, var name: String, var age: Int)
class Girl {
    //运算符重载  girl1 + girl2   返回值为第一个值
    operator fun plus(girl: Girl): Girl {
        return this
    }
}

open class Person(name: String?) {
    constructor() : this(name = null) {
//二级构造方法需要调用一级
    }

    var age: Int = 0
        get() = field
        set(value) {
            field = value
        }
}


@Entity
//@Uid
class OBEntity {

    //ID必须为long
    @Id
    var id: Long = 0
    //对应数据库中的表
    //@Uid
    @NameInDb("name")
    lateinit var userName: String
    //添加索引
    @Index
    lateinit var gId: String
    //字段不想被持久化
    @Transient
    var level: Int = 0
    //一对多，多对一的注解
    @Backlink
    lateinit var product: List<OBEntity2>


}

@Entity
class OBEntity2 {
    @Id
    var id: Long = 0
    lateinit var customer: ToOne<OBEntity>
}