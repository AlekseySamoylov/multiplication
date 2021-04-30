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
    val userOptional = userRepository.findByAlias(resultAttempt.userAlias)
    val savedUser = if (userOptional.isPresent) {
      val user = userOptional.get()
      log.debug("User found {}", user)
      user
    } else {
      val user = User()
      user.alias = resultAttempt.userAlias
      log.debug("User was not found, created new one {}", user)
      userRepository.save(user)
    }
    val attempt = ChallengeAttempt.Builder()
      .user(savedUser)
      .factorA(resultAttempt.factorA)
      .factorB(resultAttempt.factorB)
      .resultAttempt(resultAttempt.guess)
      .correct(correct)
      .build()
    return attemptRepository.save(attempt)
  }

  override fun getLastAttemptsForUserAlias(userAlias: String): List<ChallengeAttempt> {
    log.debug("Search last 10 attempts by alias {}", userAlias)
    return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias)
  }
}
