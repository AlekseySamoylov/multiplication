package com.example.multiplication.challange

import org.springframework.data.repository.CrudRepository


interface ChallengeAttemptRepository : CrudRepository<ChallengeAttempt, Long> {
  fun findTop10ByUserAliasOrderByIdDesc(userAlias: String): List<ChallengeAttempt>
}
