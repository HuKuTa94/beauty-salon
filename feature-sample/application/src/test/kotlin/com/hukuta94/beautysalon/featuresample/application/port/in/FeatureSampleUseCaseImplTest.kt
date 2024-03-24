package com.hukuta94.beautysalon.featuresample.application.port.`in`

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FeatureSampleUseCaseImplTest {
    @Test
    fun `usecase param is true - successful text message`() {
        FeatureSampleUseCaseImpl().execute(true).shouldBeRight()
    }

    @Test
    fun `usecase param is false should return business error`() {
        val actualError = FeatureSampleUseCaseImpl().execute(false).shouldBeLeft()

        assertThat(actualError)
            .isInstanceOf(FeatureSampleUseCaseError.BusinessError::class.java)
    }
}