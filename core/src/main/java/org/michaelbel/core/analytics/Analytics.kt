package org.michaelbel.core.analytics

import android.Manifest.permission.*
import android.content.Context
import android.os.Bundle
import androidx.annotation.RequiresPermission
import com.google.firebase.analytics.FirebaseAnalytics

object Analytics {

    const val EVENT_ACTION_CLICK = "event_action_click"

    private var firebaseAnalytics: FirebaseAnalytics? = null

    @RequiresPermission(allOf = [INTERNET, ACCESS_NETWORK_STATE, WAKE_LOCK])
    fun initialize(context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun logEvent(event: String, param: String) {
        val bundle = Bundle()
        bundle.putString(event, param)
        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}