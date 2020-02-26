package com.yar0316.todolist_trial.form

import com.yar0316.todolist_trial.entity.BaseEntity
import org.springframework.stereotype.Component

@Component
interface BaseForm{
    fun toEntity(): BaseEntity
}
