package com.cossul.todo.controllers

import com.cossul.todo.models.Todo
import com.cossul.todo.repositories.TodoRepository
import com.cossul.todo.requests.TodoRequest
import com.cossul.todo.services.TodoService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception

@RestController
@RequestMapping("/todos")
class TodoController(val todoService: TodoService) {

    @GetMapping("")
    fun getAll(): MutableIterable<Todo> = todoService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = todoService.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Todo $id not found.") }

    @PostMapping("")
    fun create(@RequestBody todoRequest: TodoRequest): Todo = todoService.save(Todo(title = todoRequest.title))

    @PutMapping("/{id}")
    fun editById(@RequestBody todoRequest: TodoRequest, @PathVariable id: Long): Todo {
        val todo = todoService.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Todo $id not found.") }
        todo.title = todoRequest.title
        todo.completed = todoRequest.completed
        return todoService.save(todo)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        try {
            todoService.deleteById(id)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Todo $id not found.")
        }
    }
}