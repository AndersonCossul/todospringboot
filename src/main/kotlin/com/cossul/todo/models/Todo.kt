package com.cossul.todo.models

import javax.persistence.*

@Entity
class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        @Column(nullable = false)
        var title: String = "",
        @Column
        var completed: Boolean = false
)