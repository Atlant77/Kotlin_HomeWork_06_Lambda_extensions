import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun getUnreadChatsCount() {
        // arrange
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

        // act
        val unreadChat = ChatService.getUnreadChatsCount(2)
        // assert
        assertNotNull(unreadChat)
    }

    @Test
    fun addMessage() {
        // arrange
        val chat = ChatService.addChat(
            1, Chat(
                1,
                1,
                12233444,
                "Top chat",
                arrayListOf(
                    Message(
                        0,
                        12233444,
                        1,
                        2,
                        "Message text",
                        false
                    ),
                    Message(
                        1,
                        12233444,
                        1,
                        2,
                        "Message text",
                        false
                    ),
                    Message(
                        1,
                        12233444,
                        1,
                        2,
                        "Message text",
                        false
                    )
                )
            )
        )
        // act
        val checkId = chat.messages[0].id
        // assert
        assert(checkId == 0)
//        assertNotNull(checkId)
    }

    @Test
    fun addChat() {
        // arrange
        val chat = ChatService.addChat(
            1, Chat(
                1,
                1,
                12233444,
                "Top chat",
                arrayListOf(
                    Message(
                        1,
                        12233444,
                        1,
                        2,
                        "Message text",
                        false
                    )
                )
            )
        )
        // act
        val checkId = chat.id
        // assert
        assertNotNull(checkId)
    }

    @Test(expected = ChatNotFoundException::class)
    //Функция выкидывает исключение, если была попытка удалить несуществующий чат.
    fun delChatIdException() {
        // arrange
        ChatService.addChat(
            1, Chat(
                1,
                1,
                12233444,
                "Top chat",
                arrayListOf(
                    Message(
                        1,
                        12233444,
                        1,
                        2,
                        "Message text",
                        false
                    )
                )
            )
        )
        // act
        ChatService.delChat(1, 5)
        // assert
    }

}