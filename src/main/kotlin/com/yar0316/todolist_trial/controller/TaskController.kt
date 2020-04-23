/**
 * 通常のコントローラ
 * このアプリで使われるのは最初にページにアクセスするときのみ。
 *
 * @author yar0316
 */
package com.yar0316.todolist_trial.controller

import com.yar0316.todolist_trial.service.TaskManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class TaskController {

    @Autowired
    private lateinit var taskManagementService: TaskManagementService

    /**
     * トップページへのリクエスト
     * ブラウザ側でAPIを叩かせているため、ここでデータを渡すことはしていない。
     * 最初に通信をしないといけない分表示が遅くなるけど、向こう側でリクエストオブジェクトを取得する方法知らなかったのでこうした
     */
    @RequestMapping(value = ["/", "list"], method = [RequestMethod.GET])
    fun fetchAll(): String {
        return "index"
    }

}