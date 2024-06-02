package com.venture.bootcounter.di

import com.venture.bootcounter.data.repo.BootEventRepository
import com.venture.bootcounter.feature.BootViewModel
import com.venture.bootcounter.framework.data.db.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { BootEventRepository(get()) }
    viewModel { BootViewModel(get()) }
}