package com.example.multiplication.challange

import com.example.multiplication.users.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
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

  @Test
  fun `Should validate POST result`() {
    // Given
    val user = User(1, "john")
    val attemptId = 5L
    val attemptDTO = ChallengeAttemptDTO(50, 70, user.alias, 3500)
    val expectedResponse = ChallengeAttempt(attemptId, user, 50, 70, 3500, true)
    // todo fix it
    given(challengeService!!.verifyAttempt(ArgumentMatchers.eq(attemptDTO)))
      .willReturn(expectedResponse)

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

  // TODO add negative scenario test

}
