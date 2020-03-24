package com.yar0316.todolist_trial

/**
 * Webシステム系の体験で見てもらうためのシステム。
 * 規模の小さなTodoリスト。サーバーサイドを知ってもらうのがメインなのでフロントは控えめに作る予定
 * ただしクライアントサイドとサーバーサイドがどう違うのかみたいな話ができる人向けに、JSを使って見やすいものを作るくらいはする?
 * 理想はリアクティブなサイトだけど正直そこまで作り込まなくていいと思う
 * 受講生さんにも「このくらいなら作れそう」って思わせられるものにしよう
 */
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoListForTrialClassApplication

fun main(args: Array<String>) {
	runApplication<TodoListForTrialClassApplication>(*args)
}
