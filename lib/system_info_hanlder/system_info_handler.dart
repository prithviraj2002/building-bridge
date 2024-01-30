import 'package:flutter/services.dart';

class SystemInfo {
  static const MethodChannel _channel = MethodChannel('native_device_feature/systemData');
  static const String methodCall = 'getSystemData';

  static Future<Map<String, String>?> getSystemData() async {
    try {
      final Map<String, String>? systemData = await _channel.invokeMapMethod(methodCall);
      return systemData;
    } catch (e) {
      print('Error getting system data: $e');
      return {};
    }
  }
}