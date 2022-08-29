data class Chat(
    var id: Int, // Идентификатор чата
    val idAdmin: Int, // Идентификатор пользователя начавшего чат
    val date: Int, // Дата начала чата
    val topic: String, // Общая тема чата
    var messages: ArrayList<Message> // Массив сообщений
)