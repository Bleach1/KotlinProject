package com.example.ljn.kotlinproject.base

import io.objectbox.annotation.*
import io.objectbox.relation.ToOne

data class BaseBean<T>(var code: Int = 0, var msg: String, var data: T)
data class Data(var id: Int)
data class TestBean(var errorMsg: String, var name: String, var age: Int)

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