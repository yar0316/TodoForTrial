/**
 * 簡易版TodoリストのREST API。
 * @TODO 現在はフィールドに直接リポジトリを持ってエンティティ直接受け渡してるけど、簡易版ではそんなにたくさんプロパティがいらない。よってServiceをもたせてFormで受け渡しを行う。
 */
package com.yar0316.todolist_trial.controller

import com.yar0316.todolist_trial.form.TaskForm
import com.yar0316.todolist_trial.service.TaskManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
class TaskRestController {
    @Autowired
    private lateinit var taskManagementService: TaskManagementService

    @ResponseBody
    @RequestMapping(value = ["tasks"], method = [RequestMethod.GET])
    fun get(): List<TaskForm> = taskManagementService.findAll()

    @ResponseBody
    @RequestMapping(value = ["insertUpdate"], method = [RequestMethod.POST])
    fun insertUpdate(@RequestBody task: TaskForm): TaskForm {
        return taskManagementService.insertUpdate(task)
    }

    @ResponseBody
    @RequestMapping(value = ["delete/{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable("id") id: Int): String{
        return taskManagementService.delete(id)
    }
}