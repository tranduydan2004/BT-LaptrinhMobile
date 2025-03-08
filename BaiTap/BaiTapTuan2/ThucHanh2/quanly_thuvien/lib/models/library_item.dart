// Tính trừu tượng - Abstraction
abstract class LibraryItem {
  String get id;
  String get title;
  bool get isBorrowed;
  String? get borrowedBy;
  void borrow(String staffName);
  void showInfo();
}