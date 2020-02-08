package com.cossul.todo.services

import com.cossul.todo.models.Todo
import com.cossul.todo.repositories.TodoRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(val todoRepository: TodoRepository) {
    fun findAll(): MutableIterable<Todo> = todoRepository.findAll()

    fun findById(id: Long): Optional<Todo> = todoRepository.findById(id)

    fun save(todo: Todo) = todoRepository.save(todo)

    fun deleteById(id: Long) = todoRepository.deleteById(id)
}