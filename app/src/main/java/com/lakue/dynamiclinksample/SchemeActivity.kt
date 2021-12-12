package com.lakue.dynamiclinksample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class SchemeActivity : AppCompatActivity() {

    val TAG = "SchemeActivity"

    companion object{
        val SCHEME_PHEED = "pheed"
        val SCHEME_MAIN = "main"
        val SCHEME_COMMENT = "comment"

        val PARAM_ID = "id"
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
                            //공유 타입이 pheed으로 들어왔을 때 처리
                            val code: String = deeplink.getQueryParameter(PARAM_ID)!!
                            Toast.makeText(this, "SCHEME_PHEED 타입 id : $code 데이터 보여주기", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@SchemeActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        SCHEME_COMMENT -> {
                            //공유 타입이 comment로 들어왔을 때 처리
                            val code: String = deeplink.getQueryParameter(PARAM_ID)!!
                            Toast.makeText(this, "SCHEME_COMMENT 타입 id : $code 데이터 보여주기", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@SchemeActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        SCHEME_MAIN -> {
                            //공유 타입이 main으로 들어왔을 때 처리
                            Toast.makeText(this, "메인 이동", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@SchemeActivity, MainActivity::class.java)
                            startActivity(intent)
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