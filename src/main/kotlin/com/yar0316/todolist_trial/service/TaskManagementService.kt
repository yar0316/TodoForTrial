package com.yar0316.todolist_trial.service

/**
 * タスクの取り扱い
 * Repositoryだけでもよさそうなくらいシンプルな作りだがちょっと考えるべきことがあるので作ってある。
 * 考えるべきこと：新規登録・更新のときのみフォーム用オブジェクトを使用するか、表示に関してもフォーム用に変換して行うか。
 * 数が増えるほど変換のコストは掛かるので、できれば検索と表示の際には変換を掛けたくない。ただ型このままでちゃんと表示できるか不明。
 *
 * 2020/2/26 完了フラグのこと忘れてたのでviewに持ってくときも変換するのは確定
 * 追加：BaseService的なものを使ってエンティティ変換、フォーム変換を作っておく→このクラスでオーバーライドする形にしたほうがいいか検討
 * もしくはFormオブジェクトとEntityのベースを作ってそれぞれに変換用メソッドを定義しておくか
 * 感覚的にはサービスにやらせるよりはこっちのほうがいいかな？
 * ユーザー関連のテーブルも使うようになったらやったほうがいい
 *
 * TODO:EntityのリストをFormのリストに、FormのリストをEntityのリストに変換するメソッド定義(こっちは処理内容的にServiceに据え置き)
 *
 *
 * @author yar0316
 */

import com.yar0316.todolist_trial.entity.Task
import com.yar0316.todolist_trial.form.TaskForm
import com.yar0316.todolist_trial.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.Normalizer
import java.time.LocalDate

const val PAST = 1
const val FUTURE = 2
const val MSG_CREATE_COMPLETED = "作成しました。"
const val MSG_CREATE_FAILED = "作成に失敗しました。"
const val DEFAULT_DATE_FORMAT = "yyyy/MM/dd"

@Service
class TaskManagementService {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    fun findAll(): List<TaskForm> = toFormList(taskRepository.findAll().toList())

    fun findByTitle(title: String): List<TaskForm> = toFormList(taskRepository.findByTitle("*${title}*"))

    fun findByCreatedDate(inputDate: String): List<TaskForm>{
        // 作成日は今日以前の過去でなければ不正なので探さずに空っぽで返す
        val date = chkAndParseDate(inputDate, PAST) ?: return mutableListOf()
        return toFormList(taskRepository.findByCreatedDate(date))
    }

    fun findByDeadline(inputDate: String): List<TaskForm> {
        //　締切なので今日以降の未来でなければ不正なので探さずに空っぽで返す
        val date = chkAndParseDate(inputDate, FUTURE) ?: return mutableListOf()
        return toFormList(taskRepository.findByDeadline(date))
    }

    fun createTask(taskForm: TaskForm): String{
        val task = taskForm.toEntity()
        taskRepository.save(task as Task)
        return MSG_CREATE_COMPLETED
    }

    /**
     * 日付の書式についてチェックを行い、LocalDateに変換する。
     *
     * @param dateStr 変換する文字列
     * @param tense 時制
     * @return LocalDate? 日付として解析できない場合、または指定された時制(過去、未来、今日)から外れた日付だった場合、null
     */
    private fun chkAndParseDate(dateStr: String, tense: Int): LocalDate?{
            // 日付に変換できなかった場合この時点で例外
            val date = LocalDate.parse(dateStr)
            val checker = supplyChecker(tense)

            // 指定された時制から外れていないかチェック
            if (!checker(date)){
                return null
            }
            return date

    }

    /**
     * 特定の範囲に収まる日付かどうかを確かめるための関数を提供する。
     * 特定の範囲は時制によって決まる。
     * 過去：今日以前の日付かどうか確かめる
     *
     * @param tense 時制
     * @return 日付を受け取って時制のチェックを行う関数
     */
    private fun supplyChecker(tense: Int): (LocalDate) -> Boolean {
        return when(tense){
            PAST -> {date -> date < LocalDate.now().plusDays(1L)}
            FUTURE -> {date -> date > LocalDate.now().minusDays(1L)}
            else -> throw RuntimeException("時制が不明です")
        }
    }

    /**
     * エンティティのリストをフォームのリストに変換
     * @param entityList 変換元
     * @return 変換されたフォームのリスト
     */
    fun toFormList(entityList: List<Task>): List<TaskForm> {
        val formList = ArrayList<TaskForm>()

        entityList.forEach{ formList.add(it.toForm() as TaskForm) }
        return formList
    }

    /**
     * フォームのリストをエンティティのリストに変換
     * 使用未定
     * @param formList 変換元
     * @return 変換されたエンティティのリスト
     */
    fun toEntityList(formList: List<TaskForm>): List<Task> {
        val entityList = ArrayList<Task>()

        formList.forEach { entityList.add(it.toEntity() as Task) }
        return entityList
    }
}