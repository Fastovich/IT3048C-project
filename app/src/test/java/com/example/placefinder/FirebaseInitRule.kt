package com.example.placefinder

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class FirebaseInitRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val context = ApplicationProvider.getApplicationContext<Context>()
                try {
                    FirebaseApp.initializeApp(context)
                } catch (e: IllegalStateException) {
                    // FirebaseApp is already initialized, so we can ignore this exception.
                }
                base.evaluate()
            }
        }
    }
}
