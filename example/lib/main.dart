import 'package:flutter/material.dart';
import 'package:mfilterit_sdk_flutter/mfilterit_sdk_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    Mfilterit.sdkInit("vendorId", Mfilterit.DATAPOINTS_LEVEL_1);
    Mfilterit.setApplicationData("Sample Application Data");
    Mfilterit.sendEvent(
        "vendorId",
        "{ 'event1' : 'value1' , 'event2' : 'value2'}",
        Mfilterit.NEW_USER_EVENT);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: Android'),
        ),
      ),
    );
  }
}
