package com.madar.madar.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madar.madar.data.User

@Composable
fun UserItem(user: User) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${user.name}")
        Text(text = "Age: ${user.age}")
        Text(text = "Job Title: ${user.jobTitle}")
        Text(text = "Gender: ${user.gender}")
    }
}

