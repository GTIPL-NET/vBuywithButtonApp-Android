package com.gtipl.popupadmeet

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.gtipl.meetadlink.MeetLinkModel

class MainActivity : AppCompatActivity() {

    var meetLinkView: MeetLinkModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onPause() {
        super.onPause()
        meetLinkView!!.hidePopView()
    }

    override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            AlertDialog.Builder(this)
                .setTitle("Permission Required..")
                .setMessage("Keep widget calls upfront while using other apps. Simply give permission to draw over other apps")
                .setPositiveButton("Grant Permission", { dialog, which ->

                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivityForResult(intent, 8888)
                })
                .show()
        } else {

            if (meetLinkView == null) {
                meetLinkView = MeetLinkModel(this, windowManager, "https://vbuywith.com/Customer/widget/20")
            }
            meetLinkView!!.showPopView()

        }
    }
}
