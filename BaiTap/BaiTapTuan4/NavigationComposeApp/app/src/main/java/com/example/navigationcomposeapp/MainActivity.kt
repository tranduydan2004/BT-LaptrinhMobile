package com.example.navigationcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationcomposeapp.ui.theme.NavigationComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComposeAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "root") {
                    composable("root") { RootScreen(navController) }
                    composable("list") { ListScreen(navController) }
                    composable("detail") { DetailScreen(navController) }
                }
            }
        }
    }
}

// Màn hình Root
@Composable
fun RootScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )

        Text(
            text = "Navigation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(
            onClick = { navController.navigate("list") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "PUSH")
        }
    }
}

// Màn hình List (Lazy) với 1 triệu phần tử
@Composable
fun ListScreen(navController: NavController) {
    val itemsList = List(1_000_000) { " | The only way to do great work is to love what you do." }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFF1E90FF), // Màu xanh lam giống trong hình
                        shape = RoundedCornerShape(8.dp) // Bo góc
                    )
                    .clickable { navController.navigateUp() }, // Quay lại màn hình trước
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            // Tiêu đề "LazyColumn"
            Text(
                text = "LazyColumn",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color(0xFF1E90FF)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp) // Khoảng cách giữa các phần tử
        ) {
            itemsIndexed(itemsList) {index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail") }
                        .background(
                            color = Color(0xFFE6F0FA), // Màu xanh nhạt giống trong hình
                            shape = RoundedCornerShape(8.dp) // Bo góc
                        )
                        .padding(12.dp), // Padding bên trong
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nội dung chính
                    Text(
                        text = String.format("%02d", index + 1) + "$item", // Định dạng số thứ tự
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        fontSize = 16.sp
                    )

                    // Mũi tên bên phải
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Forward",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

// Màn hình Detail
@Composable
fun DetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFF1E90FF), // Màu xanh lam giống trong hình
                        shape = RoundedCornerShape(8.dp) // Bo góc
                    )
                    .clickable { navController.navigateUp() }, // Quay lại màn hình trước
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            // Tiêu đề "Detail"
            Text(
                text = "Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color(0xFF1E90FF)
            )
        }

        Text(
            text = "\"The only way to do great work is to love what you do.\"",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Nội dung chính
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFADD8E6),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(24.dp)
                .size(350.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\"The only\nway to do\ngreat work\nis to love\nwhat you\ndo.\"",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
                Text(
                    text = "Steve Jobs",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống dưới
                Text(
                    text = "http://quotes.thisgrandpa.blogspot.com/",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống dưới

        // Nút "Back to root"
        Button(
            onClick = { navController.navigate("root") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "BACK TO ROOT")
        }
    }
}

// Preview cho RootScreen
@Preview(showBackground = true)
@Composable
fun RootScreenPreview() {
    NavigationComposeAppTheme {
        RootScreen(rememberNavController())
    }
}

// Preview cho ListScreen
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    NavigationComposeAppTheme {
        ListScreen(rememberNavController())
    }
}

// Preview cho DetailScreen
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    NavigationComposeAppTheme {
        DetailScreen(rememberNavController())
    }
}