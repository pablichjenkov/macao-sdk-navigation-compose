package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import kotlinx.coroutines.flow.Flow

interface SingleNoInputUseCase<out R> {
    suspend fun doWork(): R
}

interface SingleInputUseCase<T, out R> {
    suspend fun doWork(param: T): R
}

interface FlowNoInputUseCase<out R> {
    suspend fun doWork(): Flow<R>
}

interface FlowInputUseCase<T, out R> {
    suspend fun doWork(param: T): Flow<R>
}
