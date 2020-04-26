import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:fluttermodule/simple_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyHomePage> {
  @override
  void initState() {
    super.initState();
    FlutterBoost.singleton.registerPageBuilders({
      //fisrt widget
      'flutterbus://flutterWidget_FirstPage': (pageName, params, _) {
        debugPrint("params :${params.toString()}");
        return FirstRouteWidget();
      },
      //second widget
      'flutterbus://flutterSecondPage': (pageName, params, _) {
        debugPrint("params :${params.toString()}");
        return SecondRouteWidget();
      },
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Boost example',
        //初始化FlutterBoost
        builder: FlutterBoost.init(postPush: _onRoutePushed),
        home: Container());
  }

  void _onRoutePushed(
      String pageName, String uniqueId, Map params, Route route, Future _) {
    debugPrint("pageName :${pageName}" + "params :${params.toString()}");
  }
}



