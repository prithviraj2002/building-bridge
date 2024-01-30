package com.example.building_bridge

import android.os.Build
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

class SystemInfoHandler(private val registrar: Registrar) : MethodChannel.MethodCallHandler {

    companion object {
        private const val CHANNEL_NAME = "native_device_feature/systemData"

        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), CHANNEL_NAME)
            val handler = SystemInfoHandler(registrar)
            channel.setMethodCallHandler(handler)
        }
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getSystemData" -> {
                val systemData = getSystemData()
                result.success(systemData)
            }
            else -> {
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