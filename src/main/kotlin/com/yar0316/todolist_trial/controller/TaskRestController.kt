package com.yar0316.todolist_trial.controller

import com.yar0316.todolist_trial.entity.Task
import com.yar0316.todolist_trial.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class TaskRestController {
    @Autowired
    private lateinit var taskRepository: TaskRepository

    @ResponseBody
    @RequestMapping(value = ["tasks"], method = [RequestMethod.GET])
    fun get(): List<Task> = taskRepository.findAll().toList()

    @ResponseBody
    @RequestMapping(value = ["edit"], method = [RequestMethod.POST])
    fun edit(@RequestBody task: Task): Unit {
        taskRepository.save(task)
    }


}