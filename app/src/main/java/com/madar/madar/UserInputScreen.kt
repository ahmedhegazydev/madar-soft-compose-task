package com.madar.madar

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.madar.madar.compose.main.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun UserInputScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    var jobTitleError by remember { mutableStateOf<String?>(null) }
    var genderError by remember { mutableStateOf<String?>(null) }

    val genderOptions = listOf("Male", "Female")
    var expanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    fun validate(): Boolean {
        var isValid = true
        nameError = if (name.isBlank()) {
            isValid = false
            context.getString(R.string.name_cannot_be_empty)
        } else {
            null
        }

        ageError = if (age.isBlank()) {
            isValid = false
            context.getString(R.string.age_cannot_be_empty)
        } else if (age.toIntOrNull() == null) {
            isValid = false
            context.getString(R.string.age_must_be_a_number)
        } else if (age.toInt() <= 0) {
            isValid = false
            context.getString(R.string.age_must_be_greater_than_zero)
        } else {
            null
        }

        jobTitleError = if (jobTitle.isBlank()) {
            isValid = false
            context.getString(R.string.job_title_cannot_be_empty)
        } else {
            null
        }

        genderError = if (gender.isBlank()) {
            isValid = false
            context.getString(R.string.gender_cannot_be_empty)
        } else {
            null
        }

        return isValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = null
            },
            label = { Text(stringResource(R.string.name)) },
            isError = nameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError != null) {
            Text(text = nameError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
                ageError = null
            },
            label = { Text(stringResource(R.string.age)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = ageError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (ageError != null) {
            Text(text = ageError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = jobTitle,
            onValueChange = {
                jobTitle = it
                jobTitleError = null
            },
            label = { Text(stringResource(R.string.job_title)) },
            isError = jobTitleError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (jobTitleError != null) {
            Text(text = jobTitleError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box {
            OutlinedTextField(
                value = gender,
                onValueChange = {},
                label = { Text(stringResource(R.string.gender)) },
                isError = genderError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { expanded = true },
                readOnly = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                genderOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            gender = option
                            genderError = null
                            expanded = false
                        })
                }
            }
        }

        if (genderError != null) {
            Text(text = genderError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validate()) {
                    scope.launch {
                        viewModel.saveUser(name, age.toInt(), jobTitle, gender)
                        navController.navigate("userList")
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.save_and_view_users))
        }
    }
}


