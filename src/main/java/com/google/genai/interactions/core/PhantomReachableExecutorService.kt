/*
* Copyright 2025 Google LLC
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.google.genai.interactions.core

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * A delegating wrapper around an [ExecutorService] that shuts it down once it's only phantom
 * reachable.
 *
 * This class ensures the [ExecutorService] is shut down even if the user forgets to do it.
 */
internal class PhantomReachableExecutorService(private val executorService: ExecutorService) :
    ExecutorService {
    init {
        closeWhenPhantomReachable(this) { executorService.shutdown() }
    }

    override fun execute(command: Runnable) = executorService.execute(command)

    override fun shutdown() = executorService.shutdown()

    override fun shutdownNow(): MutableList<Runnable> = executorService.shutdownNow()

    override fun isShutdown(): Boolean = executorService.isShutdown

    override fun isTerminated(): Boolean = executorService.isTerminated

    override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean =
        executorService.awaitTermination(timeout, unit)

    override fun <T : Any?> submit(task: Callable<T>): Future<T> = executorService.submit(task)

    override fun <T : Any?> submit(task: Runnable, result: T): Future<T> =
        executorService.submit(task, result)

    override fun submit(task: Runnable): Future<*> = executorService.submit(task)

    override fun <T : Any?> invokeAll(
        tasks: MutableCollection<out Callable<T>>
    ): MutableList<Future<T>> = executorService.invokeAll(tasks)

    override fun <T : Any?> invokeAll(
        tasks: MutableCollection<out Callable<T>>,
        timeout: Long,
        unit: TimeUnit,
    ): MutableList<Future<T>> = executorService.invokeAll(tasks, timeout, unit)

    override fun <T : Any?> invokeAny(tasks: MutableCollection<out Callable<T>>): T =
        executorService.invokeAny(tasks)

    override fun <T : Any?> invokeAny(
        tasks: MutableCollection<out Callable<T>>,
        timeout: Long,
        unit: TimeUnit,
    ): T = executorService.invokeAny(tasks, timeout, unit)
}
