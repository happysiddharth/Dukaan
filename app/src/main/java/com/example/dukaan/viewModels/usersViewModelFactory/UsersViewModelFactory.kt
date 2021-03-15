package com.example.dukaan.viewModels.usersViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dukaan.localDatabase.DukaanRoomDAO
import com.example.dukaan.viewModels.UsersViewModel

class UsersViewModelFactory(private val dukaanRoomDAO: DukaanRoomDAO): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(dukaanRoomDAO) as T
    }
}