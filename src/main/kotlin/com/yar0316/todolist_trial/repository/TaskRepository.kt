/**
 * データベースへの接続を行う。
 *
 * @author yar0316
 */
package com.yar0316.todolist_trial.repository

import com.yar0316.todolist_trial.entity.Task
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TaskRepository : CrudRepository<Task, Int> {

    /**
     * 作成日(登録日)検索<br>
     *    渡された日付以降に作成されたタスクを検索する。
     *
     * @param date 基準作成日
     * @return <code>data</code>以後に作成されたタスク一覧
     */
    @Query("select * from Task where created_date >= :date")
    fun findByCreatedDate(date: LocalDate): List<Task>

    /**
     * 締め切り検索<br>
     *     指定した日付以前に締め切りが設定されているタスクを取得する。
     *
     * @param date 基準締切日
     * @return 締切日が<code>date</code>以前に設定されているタスク一覧。
     */
    @Query("select * from Task where deadline =< :date")
    fun findByDeadline(date: LocalDate): List<Task>

    /**
     * タイトル検索<br>
     *     指定したタイトルのタスク一覧を取得(あいまい検索)
     *
     * @param title 検索条件タイトル
     * @return 指定したタイトルに近いタイトルのタスク一覧
     */
    @Query("select * from Task where deadline like :date")
    fun findByTitle(title: String): List<Task>
}