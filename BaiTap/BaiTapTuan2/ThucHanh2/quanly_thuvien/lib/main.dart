import 'package:flutter/material.dart';
import 'managers/library_manager.dart';
import 'screens/home_screen.dart';
import 'screens/book_list_screen.dart';
import 'screens/add_book_screen.dart';
import 'screens/staff_list_screen.dart';

void main() {
  runApp(const LibraryApp());
}

class LibraryApp extends StatelessWidget {
  const LibraryApp({super.key});

  @override
  Widget build(BuildContext context) {
    final libraryManager = LibraryManager();
    return MaterialApp(
      title: 'Hệ thống Quản lý Thư viện',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: LibraryHomePage(manager: libraryManager),
    );
  }
}

class LibraryHomePage extends StatefulWidget {
  final LibraryManager manager;

  const LibraryHomePage({super.key, required this.manager});

  @override
  State<LibraryHomePage> createState() => _LibraryHomePageState();
}

class _LibraryHomePageState extends State<LibraryHomePage> {
  int _currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    final List<Widget> screens = [
      HomeScreen(manager: widget.manager),
      BookListScreen(manager: widget.manager),
      AddBookScreen(manager: widget.manager),
      StaffListScreen(manager: widget.manager),
    ];

    return Scaffold(
      appBar: AppBar(
        title: const Center( // Căn giữa tiêu đề
          child: Text(
            'Hệ thống Quản lý Thư viện',
            style: TextStyle(
              fontWeight: FontWeight.bold, // In đậm
              fontSize: 20, // Tùy chỉnh kích thước chữ (tùy chọn)
            ),
          ),
        ),
      ),
      body: screens[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        onTap: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        backgroundColor: Colors.blueAccent,
        selectedItemColor: Colors.white,
        unselectedItemColor: Colors.white70,
        elevation: 8.0,
        type: BottomNavigationBarType.fixed,
        selectedFontSize: 14.0,
        unselectedFontSize: 12.0,
        iconSize: 28.0,
        selectedLabelStyle: const TextStyle(fontWeight: FontWeight.bold),
        unselectedLabelStyle: const TextStyle(fontWeight: FontWeight.normal),
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Quản lý', tooltip: 'Quản lý chung'),
          BottomNavigationBarItem(icon: Icon(Icons.list), label: 'DS Sách', tooltip: 'Danh sách sách'),
          BottomNavigationBarItem(icon: Icon(Icons.add), label: 'Tạo Sách', tooltip: 'Thêm sách mới'),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Nhân viên', tooltip: 'Danh sách nhân viên'),
        ],
      ),
    );
  }
}