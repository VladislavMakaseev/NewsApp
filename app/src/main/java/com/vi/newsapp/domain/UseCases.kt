package com.vi.newsapp.domain

/**
 * Тип UseCase, который не принимает и не возвращает данные
 */
interface UseCase {
    suspend fun execute()
}

/**
 * Тип UseCase, который принимает, но не возвращает данные
 */
interface InputUseCase<in InputT> {
    suspend fun execute(params: InputT)
}

/**
 * Тип UseCase, который не принимает, но возвращает данные
 */
interface OutputUseCase<out OutputT> {
    suspend fun execute(): OutputT
}

/**
 * Тип UseCase, который принимает и возвращает данные
 */
interface InputOutputUseCase<in InputT, out OutputT> {
    suspend fun execute(params: InputT): OutputT
}