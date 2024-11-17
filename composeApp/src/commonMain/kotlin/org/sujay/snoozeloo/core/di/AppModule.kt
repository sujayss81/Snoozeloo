package org.sujay.snoozeloo.core.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.sujay.snoozeloo.data.Repository
import org.sujay.snoozeloo.data.RepositoryImpl

val appModule: Module = module {

    single<Repository> { RepositoryImpl() }
}