package com.yar0316.todolist_trial.entity

import com.fasterxml.jackson.databind.ser.Serializers
import com.yar0316.todolist_trial.form.BaseForm
import com.yar0316.todolist_trial.form.TaskForm
import com.yar0316.todolist_trial.service.DEFAULT_DATE_FORMAT
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Table(value = "Task")
data class Task(
        @Id
        @Column(value = "task_id")
        val id: Int?,
        val title: String,
        @Column(value = "create_date")
        val createdDate: LocalDate,
        val deadline: LocalDate,
        // 0:未完、1:完了
        val progressFlag: Int
): BaseEntity {
        constructor(id: Int?, title: String, createdDate: LocalDate, deadline: LocalDate, progressFlag: Boolean) : this(
                id,
                title,
                createdDate,
                deadline,
                when(progressFlag){
                        true -> 1
                        else -> 0
                }
        )

    override fun toForm(): BaseForm {
        val form = TaskForm()
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)

        form.id = this.id?.toString()
        form.title = this.title
        form.createdDate = this.createdDate.format(formatter)
        form.deadLine = this.deadline.format(formatter)

        return form
    }
}