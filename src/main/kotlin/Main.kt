const val userIdWriter: Int = 1
const val userIdReader: Int = 2

fun main() {
    // Функция автозаполнения данных чатов
    autoFillData()

    //  Вывод списка чатов на экран
    println(ChatService.printListOfChats())

    // 1. Вывод количества чатов с не прочтенными сообщениями пользователя
    println(
        "Qy.ty of chats were is unread messages of user $userIdReader is ${
            ChatService.getUnreadChatsCount(
                userIdReader
            )
        }"
    )
    println()

    // 2. Выводит список чатов пользователя как администратора
    println(ChatService.getChats(userIdWriter))
    println()

    // 3. Выводит список сообщений из чата (подтверждая прочтение), с данными: id чата,
    // id последнего сообщения, кол-во сообщений
    println(ChatService.getChatMessages(1))
    println()

    // 4. Добавляет новое сообщение
    println(
        "Message is added: ${
            ChatService.addMessage(
                userIdWriter,
                2, Message(1, 112222334, userIdWriter, userIdReader, "Ответ на сообщение третьего чата", true)
            )
        }"
    )
    println()

    // 5. Удаление сообщения (при удалении последнего сообщения в чате весь чат удаляется)
    println("User id $userIdWriter deleted message id 1 in chat 0 is ${ChatService.delMessage(userIdWriter, 0, 1)}")
    println()

    // 6. Создание чата (чат создаётся тогда, когда пользователю, с которым до этого не было чата, отправляется первое сообщение).
    ChatService.addChat(
        userIdWriter,
        Chat(
            1,
            userIdWriter,
            18555555,
            "Это очередной чат",
            arrayListOf(Message(0, 112222334, userIdWriter, userIdReader, "Сообщение в новом чате.", false))
        )
    )
    println()

    // 7. Удаление чата (целиком удаляется все переписка).
    println("Admin user id $userIdWriter deleted chat id 0 is ${ChatService.delChat(userIdWriter, 0)}")
    println()

    // Вывод списка чатов на экран итоговый
    println(ChatService.printListOfChats())

    // 1. Вывод количества чатов с не прочтенными сообщениями пользователя
    println(
        "Qy.ty of chats were is unread messages of user $userIdReader is ${
            ChatService.getUnreadChatsCount(
                userIdReader
            )
        }"
    )
    println()
}

fun autoFillData() {

    // Добавление первого чата
    ChatService.addChat(
        userIdWriter,
        Chat(
            1,
            userIdWriter,
            18555555,
            "Это первый чат",
            arrayListOf(Message(0, 112222334, userIdWriter, userIdReader, "Сообщение в первом чате.", false))
        )
    )
    // Добавление сообщений в первый чат
    ChatService.addMessage(
        userIdReader,
        0,
        Message(1, 114222734, userIdReader, userIdWriter, "Ответ на первое сообщение.", false)
    )
    ChatService.addMessage(
        userIdWriter,
        0,
        Message(1, 115226334, userIdWriter, userIdReader, "Ответ на ответ первого чата.", false)
    )

    // Добавление второго чата
    ChatService.addChat(
        userIdWriter,
        Chat(
            1,
            userIdWriter,
            18555675,
            "Это второй чат",
            arrayListOf(
                Message(0, 115522334, userIdWriter, userIdReader, "Сообщение во втором чате.", true)
            )
        )
    )

    // Добавление сообщений во второй чат
    ChatService.addMessage(
        userIdWriter,
        1,
        Message(0, 116622334, userIdReader, userIdWriter, "Ответ на сообщение во втором чате", false)
    )
    ChatService.addMessage(
        userIdWriter,
        1,
        Message(0, 117722334, userIdWriter, userIdReader, "Ответ на ответ во втором чате.", true)
    )
    ChatService.addMessage(
        userIdWriter,
        1,
        Message(0, 117822334, userIdReader, userIdWriter, "Последнее сообщение второго чата.", false)
    )

    // Добавление третьего чата
    ChatService.addChat(
        userIdReader,
        Chat(
            1,
            userIdReader,
            18555555,
            "Это третий чат",
            arrayListOf(
                Message(1, 112222334, userIdReader, userIdWriter, "Сообщение третьего чата", false)
            )
        )
    )
    // Добавление сообщений в третий чат
    ChatService.addMessage(
        userIdWriter,
        2, Message(1, 112222334, userIdWriter, userIdReader, "Ответ на сообщение третьего чата", true)
    )

    // Добавление четвертого чата
    ChatService.addChat(
        userIdWriter,
        Chat(
            1,
            userIdWriter,
            18555555,
            "Это четвертый чат",
            arrayListOf(
                Message(1, 112222334, userIdWriter, userIdReader, "Сообщение четвертого чата.", false)
            )
        )
    )

    // Добавление сообщений в четвертый чат
    ChatService.addMessage(
        userIdWriter,
        3, Message(1, 112222334, userIdReader, userIdWriter, "Ответ на сообщение четвертого чата.", false)
    )
    ChatService.addMessage(
        userIdWriter,
        3, Message(1, 112222334, userIdWriter, userIdReader, "Ответ на ответ четвертого чата.", false)
    )
}

