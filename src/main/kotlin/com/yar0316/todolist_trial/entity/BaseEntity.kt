package com.yar0316.todolist_trial.entity

import com.yar0316.todolist_trial.form.BaseForm
import org.springframework.stereotype.Component

@Component
interface BaseEntity {
    fun toForm(): BaseForm
}