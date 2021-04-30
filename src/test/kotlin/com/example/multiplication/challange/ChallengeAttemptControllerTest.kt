package com.example.multiplication.challange

import com.example.multiplication.users.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@ExtendWith(SpringExtension::class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController::class)
open class ChallengeAttemptControllerTest {
  @MockBean
  private var challengeService: ChallengeService? = null

  @Autowired
  private var mvc: MockMvc? = null

  @Autowired
  private var jsonRequestAttempt: JacksonTester<ChallengeAttemptDTO>? = null

  @Autowired
  private var jsonResultAttempt: JacksonTester<ChallengeAttempt>? = null

  @Autowired
  private var jsonResultAttemptList: JacksonTester<List<ChallengeAttempt>>? = null

  @Test
  fun `Should validate successful POST result`() {
    // Given
    val user = User(1, "john")
    val attemptId = 5L
    val attemptDTO = ChallengeAttemptDTO(50, 70, user.alias, 3500)
    val expectedResponse = ChallengeAttempt(attemptId, user, 50, 70, 3500, true)
    given(challengeService!!.verifyAttempt(eq(attemptDTO)))
      .willReturn(expectedResponse.toBuilder().correct(true).build())

    // When
    val response = mvc!!.perform(
      post("/attempts").contentType(MediaType.APPLICATION_JSON).content(jsonRequestAttempt!!.write(attemptDTO)!!.json)
    ).andReturn().response

    // Then
    assertThat(response.status).isEqualTo(HttpStatus.OK.value())
    assertThat(response.contentAsString).isEqualTo(
      jsonResultAttempt!!.write(
        expectedResponse
      ).json
    )
  }

  @Test
  fun `Should return bad request for invalid input data POST`() {
    // Given
    val user = User(1, "john")
    val attemptDTO = ChallengeAttemptDTO(20000, -55, user.alias, 1)

    // When
    val response = mvc!!.perform(
      post("/attempts").contentType(MediaType.APPLICATION_JSON).content(jsonRequestAttempt!!.write(attemptDTO)!!.json)
    ).andReturn().response

    // Then
    assertThat(response.status).isEqualTo(HttpStatus.BAD_REQUEST.value())
  }

  @Test
  fun `Should return last attempts for user`() {
    // Given
    val userAlias = "john"
    val attempts = listOf(ChallengeAttempt(1), ChallengeAttempt(2))
    given(challengeService!!.getLastAttemptsForUserAlias(eq(userAlias)))
      .willReturn(attempts)

    // When
    val response = mvc!!.perform(
      get("/attempts/?alias=john")
    ).andReturn().response

    // Then
    assertThat(response.status).isEqualTo(HttpStatus.OK.value())
    assertThat(response.contentAsString).isEqualTo(
      jsonResultAttemptList!!.write(
        attempts
      ).json
    )
  }

}
