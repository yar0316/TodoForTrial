/**
 * 基底フォーム
 * Viewとのデータバインディングに利用するフォームクラスのベース。
 * 今回はタスク以外のエンティティを扱っていないため特に必要ないが、
 * 複数のエンティティを扱う場合に共通で持たせたいフォーム->エンティティの変換メソッドを定義
 *
 * @author yar0316
 */
package com.yar0316.todolist_trial.form

import com.yar0316.todolist_trial.entity.BaseEntity
import org.springframework.stereotype.Component

@Component
interface BaseForm{
    fun toEntity(): BaseEntity
}
