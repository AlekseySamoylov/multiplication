package com.example.multiplication.challange

import com.example.multiplication.users.User
import com.example.multiplication.users.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.verify


@ExtendWith(MockitoExtension::class)
internal class ChallengeServiceImplTest {
  private var challengeService: ChallengeService? = null

  @Mock
  private var userRepository: UserRepository? = null

  @Mock
  private var attemptRepository: ChallengeAttemptRepository? = null

  @BeforeEach
  fun setUp() {
    challengeService = ChallengeServiceImpl(
      userRepository!!,
      attemptRepository!!
    )
  }

  @Test
  fun `Should save attempt to repository`() {
  }

  @Test
  fun `Should validate successful attempt`() {
    // Given
    val user = User(alias = "john_doe")
    val challengeAttemptToAccept = ChallengeAttempt.Builder()
      .user(user)
      .factorA(33)
      .factorB(11)
      .resultAttempt(363)
      .correct(true)
      .build()
    given(userRepository!!.save(user))
      .willReturn(User(1, "john_doe"))
    given(attemptRepository!!.save(challengeAttemptToAccept.toBuilder().user(User(1, "john_doe")).build()))
      .willReturn(challengeAttemptToAccept.toBuilder().user(User(1, "john_doe")).correct(true).build())

    val challengeService = ChallengeServiceImpl(userRepository!!, attemptRepository!!)
    val challengeAttemptDTO = ChallengeAttemptDTO(33, 11, "john_doe", 363)

    // When
    val challengeAttempt = challengeService.verifyAttempt(challengeAttemptDTO)

    // Then
    assertThat(challengeAttempt.correct).isTrue
    assertThat(challengeAttempt.user).isEqualTo(User(1, "john_doe"))
    verify(userRepository!!).save(user)
    verify(attemptRepository!!).save(
      challengeAttemptToAccept.toBuilder().user(User(1, "john_doe")).correct(true).build()
    )
  }

  @Test
  fun `Should validate fail attempt`() {
    // Given
    val challengeService = ChallengeServiceImpl(userRepository!!, attemptRepository!!)
    val challengeAttemptDTO = ChallengeAttemptDTO(33, 11, "john_doe", 366)

    // When
    val challengeAttempt = challengeService.verifyAttempt(challengeAttemptDTO)

    // Then
    assertThat(challengeAttempt.correct).isFalse
  }

  @Test
  fun `Should return last user attempts`() {
    // Given
    val challengeService = ChallengeServiceImpl(userRepository!!, attemptRepository!!)
    val userAlias = "john"
    val challengeAttemptsOfUser = listOf(ChallengeAttempt(1), ChallengeAttempt(2))
    given(attemptRepository!!.findTop10ByUserAliasOrderByIdDesc(eq(userAlias)))
      .willReturn(challengeAttemptsOfUser)

    // When
    val result = challengeService.getLastAttemptsForUserAlias(userAlias)

    // Then
    assertThat(result).isEqualTo(challengeAttemptsOfUser)
  }
}
