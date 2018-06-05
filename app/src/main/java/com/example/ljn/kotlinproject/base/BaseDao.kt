package com.example.ljn.kotlinproject.base

import com.example.ljn.kotlinproject.App

import io.objectbox.Box
import io.objectbox.android.AndroidScheduler

class BaseDao {
    //https://www.aliyun.com/jiaocheng/2492.html?spm=5176.100033.1.20.vDXGOM
    //http://objectbox.io/files/objectbox-java/current/io/objectbox/Box.html
    private val box: Box<OBEntity> = App.instance.getBoxStore().boxFor(OBEntity::class.java)
    private val entity = OBEntity()
    //1.
    fun addData() {
        box.put(entity)
    }

    //2.
    fun removeData() {
        //id=2
        box.remove(2)
        //clear al
        box.removeAll()
    }

    //3.
    fun update() {
        val query = box.query().equal(OBEntity_.userName, "ljn").build()
        query.subscribe()
                .on(AndroidScheduler.mainThread())
                .observer {
                    // adapter

                }
//事物
        App.instance.getBoxStore().runInReadTx { }
    }
    //4.Transition
    /* runInTx:在给定的runnable 中运行的事务。
       runInReadTx:只读事务，不同于runInTx，允许并发读取。
       runInTxAsync:运行在一个单独的线程中执行，执行完成后，返回callback。
       callInTx:与runInTx 相似，不同的是可以有返回值。*/
//5.Upgrade
    //1、在你要修改的表添加@Uid注解。  实体类上
    //2.编译
    //3.直接修改
    //在新增和删除字段的时候，基于NoSql的特性ObjectBox会自动的升级你的数据库。
    //所以你数据库的实体类不用做任何改动 只需删除或增加类的属性即可

    //6.RX监听


}