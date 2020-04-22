package com.yar0316.todolist_trial.form

import com.yar0316.todolist_trial.entity.BaseEntity
import com.yar0316.todolist_trial.entity.Task
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDate

/**
 * 新規作成時のフォーム入力用モデル。
 * 簡易版だけどDBは共有なのでこうなった。
 */
@Component
class TaskForm(var id: String? = null,
               var title: String = "",
               var progressFlag: Boolean = false,
        //完全にフロント用。サーバー側でなにかに使うことはない
               private var editFlag: Boolean = false) : BaseForm, Serializable {


    constructor(
            id: String?,
            title: String,
            iProgressFlag: Int
    ) : this(
            id,
            title,
            when (iProgressFlag) {
                0 -> false
                else -> true
            }
    )

    constructor(
            id: String?,
            title: String,
            progressFlag: Boolean
    ) : this() {
        this.id = id
        this.title = title
        this.progressFlag = progressFlag
    }

    override fun toEntity(): BaseEntity = Task(
            this.id?.toInt(),
            this.title,
            "",
            LocalDate.now(),
            LocalDate.now(),
            this.progressFlag
    )

}