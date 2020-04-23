/**
 * 新規作成時のフォーム入力用モデル。
 * 簡易版だけどDBは共有なのでこうなった。
 *
 * @author yar0316
 */
package com.yar0316.todolist_trial.form

import com.yar0316.todolist_trial.entity.BaseEntity
import com.yar0316.todolist_trial.entity.Task
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDate

@Component
class TaskForm(var id: String? = null,
               var title: String = "",
               var progressFlag: Boolean = false,
               private var editFlag: Boolean = false // クライアントサイドで利用する編集用フラグ。サーバサイドでいじることはない
) : BaseForm, Serializable {


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

    /**
     * @see com.yar0316.todolist_trial.form.BaseForm.toEntity
     */
    override fun toEntity(): BaseEntity = Task(
            this.id?.toInt(),
            this.title,
            "",
            LocalDate.now(),
            LocalDate.now(),
            this.progressFlag
    )

}