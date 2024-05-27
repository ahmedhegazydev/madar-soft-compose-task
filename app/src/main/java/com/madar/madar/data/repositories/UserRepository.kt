package com.madar.madar.data.repositories

import com.madar.madar.data.User
import com.madar.madar.data.db.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor (private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()


    suspend fun insertTask(user: User) {
        return userDao.insert(user)
    }

    suspend fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }
}