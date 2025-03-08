import 'package:flutter/material.dart';
import '../models/book.dart';
import '../models/staff.dart';

// Encapsulation & Abstraction
class LibraryManager {
  final List<Book> _books = [
    Book(id: '1', title: 'Sách 01'),
    Book(id: '2', title: 'Sách 02'),
  ];
  final List<Staff> _staffs = [Staff('Nguyen Van A')];
  final Set<String> _selectedBookIds = {};

  List<Book> get books => _books;
  List<Staff> get staffs => _staffs;
  Set<String> get selectedBookIds => _selectedBookIds;

  void addBook(String title) {
    _books.add(Book(id: (_books.length + 1).toString(), title: title));
  }

  void addStaff(String name) {
    _staffs.add(Staff(name));
  }

  void borrowBooks(String staffName, BuildContext context) {
    if (_selectedBookIds.isNotEmpty && staffName.isNotEmpty) {
      final borrowedBooks = _books.where((book) => _selectedBookIds.contains(book.id)).toList();
      bool allBooksBorrowed = false;
      for (var book in borrowedBooks) {
        try {
          book.borrow(staffName);
        } catch (e) {
          allBooksBorrowed = true;
        }
      }
      if (allBooksBorrowed) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Một số sách đã được mượn bởi người khác!')),
        );
      } else {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Đã mượn ${borrowedBooks.length} sách cho $staffName')),
        );
      }
      _selectedBookIds.clear();
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Vui lòng chọn sách và nhập tên nhân viên!')),
      );
    }
  }

  void showBookInfo(String bookId, BuildContext context) {
    final book = _books.firstWhere((book) => book.id == bookId);
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Thông tin sách'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Tên sách: ${book.title}'),
            Text('Trạng thái: ${book.isBorrowed ? 'Đã mượn' : 'Chưa mượn'}'),
            if (book.isBorrowed) Text('Người mượn: ${book.borrowedBy}'),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Đóng'),
          ),
        ],
      ),
    );
  }
}