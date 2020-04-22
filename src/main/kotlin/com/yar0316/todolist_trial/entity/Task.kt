package com.yar0316.todolist_trial.entity

import com.yar0316.todolist_trial.form.BaseForm
import com.yar0316.todolist_trial.form.TaskForm
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(value = "Task")
data class Task(
        @Id
        @Column(value = "task_id")
        val id: Int?,
        val title: String,
        @Column(value = "body")
        val body: String,
        @Column(value = "create_date")
        val createdDate: LocalDate,
        val deadline: LocalDate,
        // 0:未完、1:完了
        val progressFlag: Int
) : BaseEntity {
    constructor(id: Int?, title: String, body: String, createdDate: LocalDate, deadline: LocalDate, progressFlag: Boolean) : this(
            id,
            title,
            body,
            createdDate,
            deadline,
            when (progressFlag) {
                true -> 1
                else -> 0
            }
    )

    override fun toForm(): BaseForm = TaskForm(
            this.id?.toString(),
            this.title,
            this.progressFlag
    )
}