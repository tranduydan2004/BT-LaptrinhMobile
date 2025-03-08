import 'library_item.dart';

// Tính kế thừa và đóng gói (Inheritance & Encapsulation)
class Book implements LibraryItem {
  @override
  final String id;
  @override
  final String title;
  @override
  bool isBorrowed;
  @override
  String? borrowedBy;

  Book({required this.id, required this.title, this.isBorrowed = false, this.borrowedBy});

  @override
  void borrow(String staffName) {
    if (!isBorrowed) {
      isBorrowed = true;
      borrowedBy = staffName;
    } else {
      throw Exception('Sách đã được mượn!');
    }
  }

  @override
  void showInfo() {
    // Logic hiển thị thông tin (sẽ được gọi từ UI)
    print('Tên sách: $title, Trạng thái: ${isBorrowed ? 'Đã mượn bởi $borrowedBy' : 'Chưa mượn'}');
  }
}