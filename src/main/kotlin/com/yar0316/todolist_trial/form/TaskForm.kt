package com.yar0316.todolist_trial.form

import com.yar0316.todolist_trial.entity.BaseEntity
import com.yar0316.todolist_trial.entity.Task
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDate

/**
 * 新規作成時のフォーム入力用モデル。
 * Entityと違って全部文字列で取り扱いたいのでこうなった
 */
@Component
class TaskForm() : BaseForm, Serializable {

    var id: String? = null
    var title: String = ""
    var createdDate: String = ""
    var deadLine: String = ""
    var progressFlag: Boolean = false

    constructor(
            id:String?,
            title: String,
            createdDate: String,
            deadLine: String,
            iProgressFlag: Int
    ) : this(
            id,
            title,
            createdDate,
            deadLine,
            when(iProgressFlag){
                0 -> false
                else -> true
            }
    )

    constructor(
            id:String?,
            title: String,
            createdDate: String,
            deadLine: String,
            progressFlag: Boolean
    ) : this() {
        this.id = id
        this.title = title
        this.createdDate = createdDate
        this.deadLine = deadLine
        this.progressFlag = progressFlag
    }

    override fun toEntity(): BaseEntity = Task(
    this.id?.toInt(),
    this.title,
    LocalDate.parse(this.createdDate),
    LocalDate.parse(this.deadLine),
    this.progressFlag
    )

}