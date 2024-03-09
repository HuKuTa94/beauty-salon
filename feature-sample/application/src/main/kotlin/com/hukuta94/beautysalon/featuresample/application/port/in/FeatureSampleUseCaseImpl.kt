package com.hukuta94.beautysalon.featuresample.application.port.`in`

import arrow.core.Either
import arrow.core.left
import arrow.core.right

class FeatureSampleUseCaseImpl : FeatureSampleUseCase {
    override fun execute(shouldBeSucceed: Boolean): Either<FeatureSampleUseCaseError, String> {
        return if (shouldBeSucceed) "Real implementation of FeatureSampleUseCase was executed successfully".right()
            else FeatureSampleUseCaseError.BusinessError.left()

    }
}