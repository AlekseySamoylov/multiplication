package com.example.multiplication.challange

import com.example.multiplication.users.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChallengeServiceImpl : ChallengeService {
  private val log = LoggerFactory.getLogger(this::class.java)
  override fun verifyAttempt(resultAttempt: ChallengeAttemptDTO): ChallengeAttempt {
    log.debug("Verify attempt {}", resultAttempt)
    val result = resultAttempt.factorA * resultAttempt.factorB
    val correct = resultAttempt.guess == result
    val user = User(0, resultAttempt.userAlias)
    return ChallengeAttempt(0, user, resultAttempt.factorA, resultAttempt.factorB, resultAttempt.guess, correct)
  }
}
