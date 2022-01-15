package test.android.genesys

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.genesys.gms.mobile.client.ChatClient
import com.genesys.gms.mobile.client.ChatHandler
import com.genesys.gms.mobile.data.api.pojo.ChatV2
import com.genesys.gms.mobile.data.api.pojo.EntryType
import java.lang.Exception

class ChatActivity : Activity() {
    private var chatClient: ChatClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chatHandler: ChatHandler = object : ChatHandler {
            override fun onMessage(message: ChatV2?) {
                if (message == null) return // todo
                when (message.type) {
                    EntryType.MESSAGE -> {
                        showToast("from ${message.from.nickname}: ${message.text}")
                    }
                    EntryType.PARTICIPANT_JOINED -> TODO()
                    EntryType.PARTICIPANT_LEFT -> TODO()
                    EntryType.TYPING_STARTED -> TODO()
                    EntryType.TYPING_STOPPED -> TODO()
                    EntryType.FILE_UPLOADED -> TODO()
                    null -> TODO()
                }
            }

            override fun onFileDownloaded(p0: String?) {
                TODO()
            }

            override fun onFileUploaded(p0: Uri?) {
                TODO()
            }

            override fun onError(p0: Exception?) {
                TODO()
            }
        }
        val chatClient = ChatClient(this, chatHandler)
        setContentView(LinearLayout(this).also { root ->
            root.orientation = LinearLayout.VERTICAL
            val editText = EditText(this)
            root.addView(editText)
            root.addView(Button(this).also {
                it.text = "send"
                it.setOnClickListener {
                    chatClient.sendMessage(editText.text.toString())
                    editText.setText("") // todo
                }
            })
        })
        chatClient.connect()
        this.chatClient = chatClient
    }

    override fun onDestroy() {
        super.onDestroy()
        requireNotNull(chatClient).disconnect()
    }
}
