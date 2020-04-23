/**
 * 基底エンティティ。
 * 今回はエンティティ1つしか作らなかったので特に必要のないものだが、
 * 複数のエンティティを使う場合共通で持たせたいフォームへの変換機能を定義
 *
 * @author yar0316
 */
package com.yar0316.todolist_trial.entity

import com.yar0316.todolist_trial.form.BaseForm
import org.springframework.stereotype.Component

@Component
interface BaseEntity {
    /**
     * エンティティをフォームに変換する。
     * バリデーションを利用する場合や使用するデータを絞る場合、
     * またアプリケーション側では利用するがDBには登録しないデータが有る場合等、
     * エンティティとフォームが別れていたほうが都合のいい場合は多い。
     *
     * @return 変換後のフォームオブジェクト
     */
    fun toForm(): BaseForm
}