package com.yar0316.todolist_trial.repository

import com.yar0316.todolist_trial.entity.Task
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TaskRepository: CrudRepository<Task, Int> {

    @Query("select * from Task where created_date > :date")
    fun findByCreatedDate(date: LocalDate): List<Task>

    @Query("select * from Task where deadline < :date")
    fun findByDeadline(date: LocalDate): List<Task>

    @Query("select * from Task where deadline like :date")
    fun findByTitle(title: String): List<Task>
}