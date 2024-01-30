import 'package:building_bridge/system_info_hanlder/system_info_handler.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Building Bridge'),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text('Getting system data from Android!', style: TextStyle(fontSize: 18),),
          FutureBuilder(
              future: SystemInfo.getSystemData(),
              builder: (BuildContext context, AsyncSnapshot snapshot) {
                if (snapshot.hasData) {
                  final Map<String, String> systemData = snapshot.data;
                  return Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      const Text('Android version:', style: TextStyle(fontSize: 16),),
                      const SizedBox(width: 4,),
                      Text(systemData['androidVersion']!, style: const TextStyle(fontSize: 16),),
                      const SizedBox(width: 8),
                      const Text('Device model:', style: TextStyle(fontSize: 16),),
                      const SizedBox(width: 4,),
                      Text(systemData['deviceModel']!, style: const TextStyle(fontSize: 16),),
                    ],
                  );
                } else if (snapshot.hasError) {
                  return Center(child: Text(snapshot.error.toString()));
                } else {
                  return const Center(child: CircularProgressIndicator());
                }
              })
        ],
      ),
    );
  }
}
