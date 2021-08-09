package test.android.genesys

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.LinearLayout

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LinearLayout(this).also { root ->
            root.orientation = LinearLayout.VERTICAL
            root.addView(TextView(this).also { it.text = "hello genesys" })
            // todo
        })
    }
}
