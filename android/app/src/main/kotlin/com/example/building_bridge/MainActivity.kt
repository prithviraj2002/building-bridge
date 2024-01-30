package com.example.building_bridge

import android.os.Build
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class MainActivity: FlutterActivity() {
    private val CHANNEL = "native_device_feature/systemData"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call: MethodCall, result: Result ->
                if (call.method == "getSystemData") {
                    // Call your native Android method here
                    // For example: nativeMethodImplementation()
                    val response = getSystemData();
                    result.success(response);
                } else {
                    result.notImplemented()
                }
            }
    }

    private fun getSystemData(): Map<String, String> {
        val systemData = mutableMapOf<String, String>()
        systemData["androidVersion"] = Build.VERSION.RELEASE
        systemData["deviceModel"] = Build.MODEL
        // Add more system data as needed

        return systemData
    }
}
