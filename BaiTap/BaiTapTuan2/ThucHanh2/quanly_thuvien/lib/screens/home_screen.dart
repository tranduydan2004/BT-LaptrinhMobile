import 'package:flutter/material.dart';
import '../managers/library_manager.dart';

class HomeScreen extends StatefulWidget {
  final LibraryManager manager;

  const HomeScreen({super.key, required this.manager});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final TextEditingController _staffController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // const Text('➤ Tạo danh sách sách', style: TextStyle(fontSize: 16)),
            // const SizedBox(height: 8),
            // const Text('➤ Tạo danh sách người dùng', style: TextStyle(fontSize: 16)),
            // const SizedBox(height: 8),
            // const Text('➤ Tạo danh sách nguồn dùng sách và hiện thông tin sách', style: TextStyle(fontSize: 16)),
            // const SizedBox(height: 8),
            // const Text('➤ Cho phép người dùng mượn sách và hiện thông tin sách', style: TextStyle(fontSize: 16)),
            // const SizedBox(height: 20),

            // const Text('Nhân viên', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
            // const SizedBox(height: 8),
            Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _staffController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: 'Nguyen Van A',
                    ),
                  ),
                ),
                const SizedBox(width: 10),
                ElevatedButton(
                  onPressed: () => setState(() => _staffController.clear()),
                  child: const Text('Đổi'),
                ),
              ],
            ),
            const SizedBox(height: 20),

            const Text('Danh sách sách', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            ...widget.manager.books.map((book) => CheckboxListTile(
                  title: Text(book.title),
                  value: widget.manager.selectedBookIds.contains(book.id),
                  onChanged: (value) {
                    setState(() {
                      if (value!) {
                        widget.manager.selectedBookIds.add(book.id);
                      } else {
                        widget.manager.selectedBookIds.remove(book.id);
                      }
                    });
                  },
                  secondary: IconButton(
                    icon: const Icon(Icons.info),
                    onPressed: () => widget.manager.showBookInfo(book.id, context),
                  ),
                )),

            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    if (_staffController.text.isNotEmpty) {
                      widget.manager.addStaff(_staffController.text);
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Đã thêm nhân viên ${_staffController.text}')),
                      );
                      _staffController.clear();
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('Vui lòng nhập tên nhân viên!')),
                      );
                    }
                  },
                  child: const Text('Thêm'),
                ),
                const SizedBox(width: 10),
                ElevatedButton(
                  onPressed: () => widget.manager.borrowBooks(_staffController.text, context),
                  child: const Text('Mượn sách'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}