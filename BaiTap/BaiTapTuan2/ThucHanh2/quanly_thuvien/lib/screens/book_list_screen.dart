import 'package:flutter/material.dart';
import '../managers/library_manager.dart';

class BookListScreen extends StatelessWidget {
  final LibraryManager manager;

  const BookListScreen({super.key, required this.manager});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.all(16.0),
      itemCount: manager.books.length,
      itemBuilder: (context, index) {
        final book = manager.books[index];
        return Card(
          child: ListTile(
            title: Text(book.title),
            subtitle: Text(
                'Trạng thái: ${book.isBorrowed ? 'Đã mượn bởi ${book.borrowedBy}' : 'Chưa mượn'}'),
          ),
        );
      },
    );
  }
}