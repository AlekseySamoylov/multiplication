package com.example.multiplication.challange

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*


internal class ChallengeGeneratorImplTest {
  private lateinit var challengeGeneratorService: ChallengeGeneratorService

  @Test
  fun `Should generate challenge`() {
    // Given
    val mockRandom: Random = mock()
    whenever(mockRandom.nextInt(89)).thenReturn(20, 30)
    challengeGeneratorService = ChallengeGeneratorImpl(mockRandom)

    // When
    val challenge = challengeGeneratorService.randomChallenge()

    // Then
    assertThat(challenge).isEqualTo(Challenge(31, 41))
  }
}
