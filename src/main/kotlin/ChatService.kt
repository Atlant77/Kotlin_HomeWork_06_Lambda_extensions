object ChatService {
    private var chats = mutableListOf<Chat>()
    var uniqueIdChat: Int = 0

    fun getAll(): MutableList<Chat> = chats //Возвращает список чатов, созданных пользователем.

    // 1. Получить информацию о количестве непрочитанных чатов (например, service.getUnreadChatsCount) -
    // это количество чатов, в каждом из которых есть хотя бы одно непрочитанное сообщение.
    fun getUnreadChatsCount(userId: Int): Int {
        var unreadChats = 0
        chats.forEach {
            if (it.messages.filter { it.idReader == userId }.filter { !it.ifRead }.isNotEmpty()) {
                unreadChats += 1
            }
        }
        return unreadChats
    }

    // 2. Получить список чатов (например, service.getChats) - где в каждом чате есть последнее сообщение (если нет, то пишется "нет сообщений").
    fun getChats(userId: Int): List<Chat> {
        val userChats = chats.filter {
            it.idAdmin == userId
        }
        return userChats
    }

    // 3. Получить список сообщений из чата, указав (после того, как вызвана данная функция, все отданные сообщения автоматически считаются прочитанными):
    //id чата;
    //id последнего сообщения, начиная с которого нужно подгрузить более новые;
    //количество сообщений.
    fun getChatMessages(idChat: Int): String {
        var chatMessages = "Chat ID is $idChat, "
        chatMessages += "last message ID is ${chats.filter { it.id == idChat }[0].messages.lastIndex}, "
        chatMessages += "qt.ty messages is ${chats.filter { it.id == idChat }[0].messages.size} \n"
        chats.filter{ it.id == idChat }.forEach { chat ->
            chat.messages.forEach{ message ->
                message.ifRead = true
                chatMessages += "${message.id} writes ${message.idWriter} to ${message.idReader}, text: ${message.text}, read: ${message.ifRead} \n"
            }
        }
        return chatMessages
    }

    // 4. Создать новое сообщение.
    fun addMessage(userId: Int, idChat: Int, message: Message): Boolean {
        message.ifRead = false // Новое сообщение всегда непрочитано
        chats.forEach {
            if (it.id == idChat) {
                message.id = it.messages.size
                it.messages.add(message)
            }
        }
        return true
    }

    // 5. Удалить сообщение (при удалении последнего сообщения в чате весь чат удаляется).
    @Throws(ChatNotFoundException::class, MessageNotFoundException::class)
    fun delMessage(userId: Int, idChat: Int, idMessage: Int): Boolean {
        if (idChat in chats.indices) {
            if (idMessage in chats[idChat].messages.indices) {
                chats[idChat].messages.removeAt(idMessage)

            } else {
                throw MessageNotFoundException("Message with ID = $idMessage in chat ID = $idChat not found!")
            }
            if (chats[idChat].messages.isEmpty()) {
                chats.removeAt(idChat)
                println("Chat without any messages ID = $idChat was deleted!")
            }
            renewIdOfMessages(idChat)
            return true
        } else {
            throw ChatNotFoundException("Chat with ID = $idChat not found!")
        }
    }

    // 6. Создать чат (чат создаётся тогда, когда пользователю, с которым до этого не было чата, отправляется первое сообщение).
    fun addChat(userId: Int, chat: Chat): Chat {
        chat.id = uniqueIdChat
        if (!chat.messages.isEmpty()) {
            chat.messages[0].id = 0
            chat.messages[0].ifRead = false
        }
        chats.add(chat)
        uniqueIdChat += 1
        renewIdOfMessages(chat.id)
        return chat
    }

    // 7. Удалить чат (целиком удаляется все переписка).
    @Throws(ChatNotFoundException::class)
    fun delChat(userId: Int, id: Int): Boolean {
        if (id in chats.indices) {
            chats.removeAt(id)
//            println("Chat with ID = $id was found and deleted!")
            return true
        } else {
            throw ChatNotFoundException("Chat with ID = $id not found!")
        }
    }

    fun printListOfChats(): String {
        var listOfChats = ""
        chats.forEach {
            listOfChats += "ID = ${it.id}, admin: ${it.idAdmin}, topic: ${it.topic}, messages: \n"
            it.messages.forEach{
                listOfChats += "     ID = ${it.id}, sender: ${it.idWriter}, reader: ${it.idReader}, messages: ${it.text}, read: ${it.ifRead} \n"
            }
        }
        return listOfChats
    }

    // Функция обновляет id сообщений приравнивая индексу в массиве
    fun renewIdOfMessages(chatId: Int){
        chats[chatId].messages.forEachIndexed { i, message ->
            message.id = i
        }
    }
}