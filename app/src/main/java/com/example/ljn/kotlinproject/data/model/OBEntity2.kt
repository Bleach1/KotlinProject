package com.example.ljn.kotlinproject.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class OBEntity2 {
    @Id
    var id: Long = 0
}