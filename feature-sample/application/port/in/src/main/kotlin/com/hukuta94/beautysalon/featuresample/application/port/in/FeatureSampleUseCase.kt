package com.hukuta94.beautysalon.featuresample.application.port.`in`

import arrow.core.Either

interface FeatureSampleUseCase {
    /**
     * Demo use case of feature sample.
     *
     * @param shouldBeSucceed
     * - true: returns successful result;
     * - false: returns business error.
     *
     * @return
     * - [Either.Left]: business error;
     * - [Either.Right]: text of successfully executed business logic.
     */
    fun execute(shouldBeSucceed: Boolean): Either<FeatureSampleUseCaseError, String>
}

sealed class FeatureSampleUseCaseError(val message: String) {
    object BusinessError : FeatureSampleUseCaseError("Feature sample use case business error")
}