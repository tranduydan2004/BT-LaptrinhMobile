import 'package:flutter/material.dart';

void main() {
  runApp(const AppThucHanh1());
}

class AppThucHanh1 extends StatelessWidget {
  const AppThucHanh1({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Kiem tra tuoi',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const AgeCheckerScreen(),
    );
  }
}

class AgeCheckerScreen extends StatefulWidget {
  const AgeCheckerScreen({super.key});

  @override
  State<AgeCheckerScreen> createState() => AgeCheckerScreenState();
}

class AgeCheckerScreenState extends State<AgeCheckerScreen> {
  final TextEditingController nameController = TextEditingController();
  final TextEditingController ageController = TextEditingController();
  String result = '';

  void checkAge() {
    String name = nameController.text.trim();
    int? age = int.tryParse(ageController.text.trim());

    if (name.isEmpty || age == null || age < 0) {
      setState(() {
        result = "Vui lòng nhập họ tên và tuổi hợp lệ!";
      });
      return;
    }

    String category;
    if (age > 65) {
      category = "Người già";
    } else if (age >= 18) {
      category = "Người lớn";
    } else if (age >= 6) {
      category = "Trẻ em";
    } else {
      category = "Em bé";
    }

    setState(() {
      result = "$name thuộc nhóm: $category";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              const Text(
                "THỰC HÀNH 01",
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 30),
              Card(
                elevation: 2,
                color: Colors.grey[300],
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      TextField(
                        controller: nameController,
                        decoration: const InputDecoration(
                          labelText: "Họ và tên",
                          border: InputBorder.none,
                        ),
                      ),
                      const Divider(height: 1, color: Colors.grey),
                      TextField(
                        controller: ageController,
                        keyboardType: TextInputType.number,
                        decoration: const InputDecoration(
                          labelText: "Tuổi",
                          border: InputBorder.none,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: checkAge,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.blue,
                  padding: const EdgeInsets.symmetric(vertical: 12),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8),
                  ),
                ),
                child: const Text(
                  "Kiểm tra",
                  style: TextStyle(fontSize: 16, color: Colors.white),
                ),
              ),
              const SizedBox(height: 20),
              Text(
                result,
                textAlign: TextAlign.center,
                style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  void dispose() {
    nameController.dispose();
    ageController.dispose();
    super.dispose();
  }
}