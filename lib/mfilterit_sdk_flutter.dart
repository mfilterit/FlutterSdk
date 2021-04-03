import 'dart:developer';

import 'package:flutter/services.dart';

class Mfilterit {
  static const MethodChannel _channel =
      const MethodChannel('mfilterit_sdk_flutter');

  static const DATAPOINTS_LEVEL_0 = 0;
  static const DATAPOINTS_LEVEL_1 = 1;
  static const DATAPOINTS_LEVEL_3 = 3;
  static const REFERRAL_EVENT = "ReferralEvent";
  static const UNINSTALL_EVENT = "UninstallEvent";
  static const NEW_USER_EVENT = "NewUserEvent";

  static void sdkInit(String vendorId, int extDataPoints) {
    _channel.invokeMethod("sdkInit", <String, dynamic>{
      'vendorId': vendorId,
      'extDataPoints': extDataPoints
    });
  }

  static void setApplicationData(String applicationData) {
    _channel.invokeMethod("setApplicationData", applicationData);
  }

  static void sendEvent(String vendorId, String eventArray, String eventType) {
    _channel.invokeMethod("sendEvent", <String, dynamic>{
      'vendorId': vendorId,
      'eventArray': eventArray,
      'eventType': eventType
    });
  }
}
