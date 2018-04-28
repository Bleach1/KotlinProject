package com.example.ljn.kotlinproject.base

import com.example.ljn.kotlinproject.App
import com.example.ljn.kotlinproject.data.model.OBEntity
import com.example.ljn.kotlinproject.data.model.OBEntity_
import io.objectbox.Box
import io.objectbox.android.AndroidScheduler

class BaseDao {

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

    //6.RX监听


}