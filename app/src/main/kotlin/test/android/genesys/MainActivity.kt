package test.android.genesys

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import com.genesys.gms.mobile.client.SettingsHandler
import com.genesys.gms.mobile.data.api.pojo.DialogResponse
import com.genesys.gms.mobile.utils.Settings
import com.genesys.gms.mobile.utils.SettingsManager
import java.lang.Exception

class MainActivity : Activity() {
    private val settingsHandler: SettingsHandler = object : SettingsHandler {
        override fun onError(p0: Exception?) {
            TODO()
        }

        override fun dial(p0: DialogResponse?) {
            TODO()
        }

        override fun availableTimeslots(p0: MutableMap<String, String>?) {
            TODO()
        }
    }
    private var settingsManager: SettingsManager? = null

    private fun onChat() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(this, ChatActivity::class.java))
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LinearLayout(this).also { root ->
            root.orientation = LinearLayout.VERTICAL
            root.addView(TextView(this).also { it.text = "hello genesys" })
            root.addView(Button(this).also {
                it.text = "chat"
                it.setOnClickListener {
                    onChat()
                }
            })
            // todo
        })
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            writeSettings()
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }

    private fun getSettingsManager(): SettingsManager {
        return settingsManager ?: SettingsManager.createInstance(this, settingsHandler).also {
            settingsManager = it
        }
    }

    private fun writeSettings() {
        val settingsManager = getSettingsManager()
        val settings = Settings()
        settings.firstName = "firstName"
        settings.lastName = "lastName"
        settings.userName = "userName"
        settings.displayName = "displayName"
        settings.email = "email"
        settings.subject = "subject"
        settings.hostname = "hostname"
        settings.port = 8080
        settings.apiKey = "apiKey"
        settings.app = "apiKey"
        settings.serviceName = "serviceName"
        settings.serviceNameV2 = "serviceNameV2"
        settings.phoneNumber = "phoneNumber"
        settings.gcmSenderId = "gcmSenderId"
        settings.simpleServiceParam = "simpleServiceParam"
        settings.isSecureProtocol = false
        settings.isRequestCode = false
        settings.isPushNotification = false
        settings.fcmToken = "fcmToken"
        settings.desiredTime = "desiredTime"
        settingsManager.saveDatas(settings)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    writeSettings()
                }
            }
        }
    }
}
