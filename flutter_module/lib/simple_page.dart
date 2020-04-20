

import 'package:flutter/material.dart';
import 'package:flutter_boost/container/boost_container.dart';
import 'package:flutter_boost/flutter_boost.dart';

class FirstRouteWidget extends StatelessWidget {
  static const KEY_FLUTTER_BOOST_FIRST_ROUTE = "flutterbus://flutterWidget_FirstPage";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('First Route'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('open amap widget'),
          onPressed: () {
//            print("open second page!");
            FlutterBoost.singleton.open("flutterbus://flutternativePage", urlParams: {"test": "flutter to flutter "})
                .then((Map value) {print(
                "call me when page is finished. did recieve second route result $value");});
//            BoostContainerSettings settings = BoostContainer.of(context).settings;
//            FlutterBoost.singleton.close(settings.uniqueId, result: {"result": "data from second"});
          },
        ),
      ),
    );
  }
}

class SecondRouteWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Route"),
      ),
      body: Center(
        child: RaisedButton(
          onPressed: () {
            // Navigate back to first route when tapped.
            BoostContainerSettings settings =
                BoostContainer.of(context).settings;
            FlutterBoost.singleton.close(settings.uniqueId,
                result: {"result": "data from second"});
          },
          child: Text('Go back with result!'),
        ),
      ),
    );
  }
}
