package com.lakue.dynamiclinksample

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

object DynamicLinkUtils {
    val TAG = DynamicLinkUtils::class.java.name

    private fun getDeepLink(scheme: String, key: String?, pheedId: String?): Uri {
        return if (key == null) {
            Uri.parse("https://lakue.com/${scheme}")
        } else {
            Uri.parse("https://lakue.com/${scheme}/?${key}=$pheedId")
        }
    }

    fun onDynamicLinkClick(
        activity: Activity,
        scheme: String,
        key: String? = null,
        pheedId: String? = null
    ) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(getDeepLink(scheme, key, pheedId))
            .setDynamicLinkDomain("lakue.page.link")
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(activity.packageName)
                    .setMinimumVersion(1)
                    .build()
            )
            .buildShortDynamicLink()
            .addOnCompleteListener(
                activity
            ) { task ->
                if (task.isSuccessful) {
                    val shortLink: Uri = task.result.shortLink!!
                    try {
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                        sendIntent.type = "text/plain"
                        activity.startActivity(Intent.createChooser(sendIntent, "Share"))
                    } catch (ignored: ActivityNotFoundException) {
                    }
                } else {
                    Log.i(TAG, task.toString())
                }
            }
    }


}