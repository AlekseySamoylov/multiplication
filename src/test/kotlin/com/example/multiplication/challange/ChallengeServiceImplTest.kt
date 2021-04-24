package com.example.multiplication.challange

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class ChallengeServiceImplTest {

  @Test
  fun `Should validate successful attempt`() {
    // Given
    val challengeService = ChallengeServiceImpl()
    val challengeAttemptDTO = ChallengeAttemptDTO(33, 11, "john_doe", 363)

    // When
    val challengeAttempt = challengeService.verifyAttempt(challengeAttemptDTO)

    // Then
    assertThat(challengeAttempt.correct).isTrue()
  }

  @Test
  fun `Should validate fail attempt`() {
    // Given
    val challengeService = ChallengeServiceImpl()
    val challengeAttemptDTO = ChallengeAttemptDTO(33, 11, "john_doe", 366)

    // When
    val challengeAttempt = challengeService.verifyAttempt(challengeAttemptDTO)

    // Then
    assertThat(challengeAttempt.correct).isFalse()
  }
}
