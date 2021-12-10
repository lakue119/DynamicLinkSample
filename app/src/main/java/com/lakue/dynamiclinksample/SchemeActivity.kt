package com.lakue.dynamiclinksample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class SchemeActivity : AppCompatActivity() {

    val TAG = "SchemeActivity"

    companion object{
        val SCHEME_PHEED = "pheed"
        val PARAM_PHEED = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheme)

        handleDynamicLinks()
    }

    fun handleDynamicLinks(){
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                var deeplink: Uri? = null
                if(pendingDynamicLinkData != null) {
                    deeplink = pendingDynamicLinkData.link
                }

                if(deeplink != null) {
                    val segment: String = deeplink.lastPathSegment!!
                    when(segment){
                        SCHEME_PHEED -> {
                            val code: String = deeplink.getQueryParameter(PARAM_PHEED)!!
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    Log.d(TAG, "getDynamicLink: no link found")
                }
            }
            .addOnFailureListener(this) { e -> Log.e(TAG, "getDynamicLink:onFailure", e) }
    }
}