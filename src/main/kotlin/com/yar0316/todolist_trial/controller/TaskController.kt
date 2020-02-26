package com.yar0316.todolist_trial.controller

import com.yar0316.todolist_trial.repository.TaskRepository
import com.yar0316.todolist_trial.service.TaskManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.config.TaskManagementConfigUtils
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.lang.RuntimeException
import java.time.LocalDate

@Controller
class TaskController {

    @Autowired
    private lateinit var taskManagementService: TaskManagementService

    @RequestMapping(value = ["list"], method = [RequestMethod.GET])
    fun fetchAll(model: Model): String {
        model.addAttribute("tasks", taskManagementService.findAll())
        return "index"
    }

    @RequestMapping(value = ["search/{type}"], method = [RequestMethod.GET])
    fun searchByDate(
            @PathVariable(name = "type", required = true) type: String,
            @RequestParam(name = "value", required = false) value: String?,
            model: Model
    ): String {
        // 検索ワードが入ってなければ全件表示
        if(value.isNullOrEmpty()) { return "redirect:/list" }

        // 登録日検索(指定した日付以降),
        // 期日検索(指定した日付以前),
        // タイトル検索
        val tasks = when(type){
            "registeredDate" -> {taskManagementService.findByCreatedDate(value)}
            "deadline" -> {taskManagementService.findByDeadline(value)}
            "title" -> {taskManagementService.findByTitle(value)}
            else -> throw RuntimeException("type not exists.")
        }

        model.addAttribute("tasks", tasks)
        return ""
    }

    @RequestMapping(value = ["register"], method = [RequestMethod.POST])
    fun register(): String {
        return ""
    }

}