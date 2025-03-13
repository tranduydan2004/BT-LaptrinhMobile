import 'package:flutter/material.dart';

void main() {
  runApp(AppBaiTap3());
}

class AppBaiTap3 extends StatelessWidget {
  const AppBaiTap3({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Profile App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ProfileScreen(),
    );
  }
}

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0, // Loại bỏ bóng dưới AppBar
        leading: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Container(
            decoration: BoxDecoration(
              border: Border.all(color: const Color.fromARGB(255, 233, 230, 230), width: 1),
              borderRadius: BorderRadius.circular(4), // Góc bo tròn nhẹ
            ),
            child: IconButton(
              icon: Icon(Icons.arrow_back, color: Colors.grey[600]),
              onPressed: () {
                // Xử lý quay lại
              },
            ),
          ),
        ),
        actions: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Container(
              decoration: BoxDecoration(
                border: Border.all(color: const Color.fromARGB(255, 233, 230, 230), width: 1),
                borderRadius: BorderRadius.circular(4), // Góc bo tròn nhẹ
              ),
              child: IconButton(
                icon: Icon(Icons.edit, color: Colors.green[600]),
                onPressed: () {
                  // Xử lý hành động edit
                },
              ),
            ),
          ),
        ],
      ),
      body: Container(
        color: Colors.grey[200], // Màu nền nhạt
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                decoration: BoxDecoration(
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black26,
                      blurRadius: 10,
                      offset: Offset(0, 4),
                    ),
                  ],
                  border: Border.all(color: const Color.fromARGB(255, 233, 230, 230), width: 2),
                  shape: BoxShape.circle,
                ),
                child: CircleAvatar(
                  radius: 60, // Tăng kích thước ảnh
                  backgroundImage: AssetImage('assets/hinh_1.jpg'), // Thay đổi ảnh đại diện
                ),
              ),
              SizedBox(height: 20),
              Text(
                'Johan Smith',
                style: TextStyle(
                  fontSize: 26,
                  fontWeight: FontWeight.bold,
                  color: Colors.black87,
                ),
              ),
              SizedBox(height: 10),
              Text(
                'California, USA',
                style: TextStyle(
                  fontSize: 18,
                  color: Colors.grey[600],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}