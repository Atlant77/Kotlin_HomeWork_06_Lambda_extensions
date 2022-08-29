data class Message (
    var id: Int, // Идентификатор сообщения
    val date: Int, // Время отправки в Unixtim
    val idWriter: Int, // Идентификатор отправителя
    val idReader: Int, // Идентификатор получателя
    val text: String, // Текст сообщения
    var ifRead: Boolean = false
)


