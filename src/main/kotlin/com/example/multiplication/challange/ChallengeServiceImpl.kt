package com.example.multiplication.challange

import com.example.multiplication.users.User
import com.example.multiplication.users.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChallengeServiceImpl(
  private val userRepository: UserRepository,
  private val attemptRepository: ChallengeAttemptRepository
) : ChallengeService {
  private val log = LoggerFactory.getLogger(this::class.java)
  override fun verifyAttempt(resultAttempt: ChallengeAttemptDTO): ChallengeAttempt {
    log.debug("Verify attempt {}", resultAttempt)
    val result = resultAttempt.factorA * resultAttempt.factorB
    val correct = resultAttempt.guess == result
    val user = User()
    user.alias = resultAttempt.userAlias
    userRepository.save(user)
    val attempt = ChallengeAttempt.Builder()
      .user(user)
      .factorA(resultAttempt.factorA)
      .factorB(resultAttempt.factorB)
      .resultAttempt(resultAttempt.guess)
      .correct(correct)
      .build()
    val savedAttempt = attemptRepository.save(attempt)
    return savedAttempt
  }
}
