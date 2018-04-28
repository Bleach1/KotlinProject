package com.example.ljn.kotlinproject.data.model

import io.objectbox.annotation.*

@Entity
@Uid
class OBEntity {

    //ID必须为long
    @Id
    var id: Long = 0
    //对应数据库中的表
    @Uid
    @NameInDb("name")
    lateinit var userName: String
    //添加索引
    @Index
    lateinit var gId: String
    //字段不想被持久化
    @Transient
    var level: Int = 0
    //做一对多，多对一的注解
    /* @Backlink
     lateinit var product: List<T>*/


}