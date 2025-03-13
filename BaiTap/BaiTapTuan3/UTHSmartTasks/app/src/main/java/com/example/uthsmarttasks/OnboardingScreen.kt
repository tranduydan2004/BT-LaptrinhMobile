package com.example.uthsmarttasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onSkip: () -> Unit = {}) {
    val screens = listOf(
        Screen(
            title = "Easy Time Management",
            description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
            image = R.drawable.time_management // Thay bằng ID hình ảnh thực tế
        ),
        Screen(
            title = "Increase Work Effectiveness",
            description = "Time management and the determination of more important tasks will give your job of statistics better and always improve",
            image = R.drawable.work_effectiveness // Thay bằng ID hình ảnh thực tế
        ),
        Screen(
            title = "Reminder Notification",
            description = "The advantage of this application is that it also provides reminders to keep doing your assignments well and according to the time you have set",
            image = R.drawable.reminder_notification // Thay bằng ID hình ảnh thực tế
        )
    )

    val pagerState = rememberPagerState(pageCount = { screens.size })
    val scope = rememberCoroutineScope() // Tạo CoroutineScope để gọi hàm suspend

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Phần đầu: Dots và nút "Skip"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(
                totalDots = screens.size,
                selectedIndex = pagerState.currentPage
            )
            Text(
                text = "skip",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(screens.size - 1) // Chuyển đến trang cuối
                        }
                    }
                    .padding(8.dp)
            )
        }

        // Phần nội dung chính
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Phần nội dung trên (hình ảnh, tiêu đề, mô tả)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = screens[page].image),
                        contentDescription = screens[page].title,
                        modifier = Modifier
                            .size(300.dp)
                            .padding(16.dp)
                    )
                    Text(
                        text = screens[page].title,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = screens[page].description,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }

                // Phần button ở dưới
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    if (page == 0) {
                        // Trang đầu tiên: Button "Next" fillMaxWidth
                        Button(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(page + 1)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = ButtonDefaults.shape
                        ) {
                            Text(
                                text = "Next",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    } else {
                        // Các trang sau: Button "Back" và "Next" với khoảng cách
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page - 1)
                                    }
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = ButtonDefaults.shape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    scope.launch {
                                        if (page < screens.size - 1) {
                                            pagerState.animateScrollToPage(page + 1)
                                        } else {
                                            onSkip() // Chuyển đến màn hình chính hoặc thoát onboarding
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .width(280.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = ButtonDefaults.shape
                            ) {
                                Text(
                                    text = if (page == screens.size - 1) "Get Started" else "Next",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex) MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    )
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

data class Screen(
    val title: String,
    val description: String,
    val image: Int
)

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    UTHSmartTasksTheme {
        OnboardingScreen()
    }
}