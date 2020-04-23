/**
 * Todoタスクエンティティ。
 * このアプリではID,タイトル,進行度(完了フラグ)しか使っていないが、
 * もう一つ作る予定のちょっと情報量多めなToDoリストとDBを共有するためこうなった。
 *
 * @author yar0316
 */
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
    /**
     * 主にFormからEntityに変換する際利用するコンストラクタ。
     * 進行度フラグはDBには数値で登録し、アプリ側では論理値で扱う。
     */
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

    /**
     * エンティティをフォームオブジェクトに変換する。
     * エンティティのまま使わせてもいいが、使わない情報が多い。
     * @see com.yar0316.todolist_trial.entity.BaseEntity.toForm
     * @return 変換後のフォームオブジェクト
     */
    override fun toForm(): BaseForm = TaskForm(
            this.id?.toString(),
            this.title,
            this.progressFlag
    )
}