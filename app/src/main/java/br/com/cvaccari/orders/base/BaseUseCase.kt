package br.com.cvaccari.orders.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Result, in Params> {
    abstract suspend fun run(params: Params): Result
    suspend operator fun invoke(params: Params): Result {
        return Mutex().withLock {
            withContext(Dispatchers.IO) { run(params) }
        }
    }
}