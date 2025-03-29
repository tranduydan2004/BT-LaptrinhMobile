package com.example.firebaseprofile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.firebaseprofile.AuthManager
import com.example.firebaseprofile.R
import com.example.firebaseprofile.ui.components.BackButton
import com.example.firebaseprofile.ui.components.ProfileField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    authManager: AuthManager,
    onBackClick: () -> Unit
) {
    var dateOfBirth by remember { mutableStateOf(authManager.userDateOfBirth) }
    var isEditingDate by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                },
                navigationIcon = {
                    BackButton(onClick = onBackClick)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Back",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Profile picture
                Box(
                    modifier = Modifier
                        .size(120.dp)
                ) {
                    // Ảnh đại diện với viền xanh dương
                    Image(
                        painter = if (authManager.userPhotoUrl != null) {
                            rememberAsyncImagePainter(authManager.userPhotoUrl)
                        } else {
                            painterResource(id = R.drawable.avatar)
                        },
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.Center) // Căn giữa trong Box
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF2196F3), CircleShape) // Viền màu xanh dương
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Camera Icon",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Profile fields
                ProfileField(label = "Name", value = authManager.userName)
                ProfileField(label = "Email", value = authManager.userEmail)

                // Date of Birth field (có thể chỉnh sửa)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Date of Birth",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                    if (isEditingDate) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray)
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BasicTextField(
                                value = dateOfBirth,
                                onValueChange = { newValue ->
                                    dateOfBirth = newValue
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "Save",
                                color = Color.Blue,
                                modifier = Modifier
                                    .clickable {
                                        authManager.updateDateOfBirth(dateOfBirth)
                                        isEditingDate = false
                                    }
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Cancel",
                                color = Color.Red,
                                modifier = Modifier
                                    .clickable {
                                        dateOfBirth = authManager.userDateOfBirth
                                        isEditingDate = false
                                    }
                                    .padding(8.dp)
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray)
                                .padding(12.dp)
                                .clickable { isEditingDate = true },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dateOfBirth,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    )
}