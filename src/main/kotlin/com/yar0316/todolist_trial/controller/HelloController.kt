package com.yar0316.todolist_trial.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {

    @RequestMapping(value = ["hello"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello(): String{
        return "Hello!"
    }

}