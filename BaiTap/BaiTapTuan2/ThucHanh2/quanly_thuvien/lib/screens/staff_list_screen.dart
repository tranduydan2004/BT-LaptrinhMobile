import 'package:flutter/material.dart';
import '../managers/library_manager.dart';

class StaffListScreen extends StatelessWidget {
  final LibraryManager manager;

  const StaffListScreen({super.key, required this.manager});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.all(16.0),
      itemCount: manager.staffs.length,
      itemBuilder: (context, index) {
        final staff = manager.staffs[index];
        return Card(
          child: ListTile(
            title: Text(staff.name),
          ),
        );
      },
    );
  }
}