package com.lakue.dynamiclinksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnShareMain).setOnClickListener {
            DynamicLinkUtils.onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED
            )
        }

        findViewById<Button>(R.id.btnSharePheed1).setOnClickListener {
            DynamicLinkUtils.onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED,
                SchemeActivity.PARAM_ID,
                "1"
            )
        }

        findViewById<Button>(R.id.btnSharePheed2).setOnClickListener {
            DynamicLinkUtils.onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED,
                SchemeActivity.PARAM_ID,
                "2"
            )
        }

        findViewById<Button>(R.id.btnShareComment1).setOnClickListener {
            DynamicLinkUtils.onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_COMMENT,
                SchemeActivity.PARAM_ID,
                "1000"
            )
        }

        findViewById<Button>(R.id.btnShareComment2).setOnClickListener {
            DynamicLinkUtils.onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_COMMENT,
                SchemeActivity.PARAM_ID,
                "1001"
            )
        }

    }


}