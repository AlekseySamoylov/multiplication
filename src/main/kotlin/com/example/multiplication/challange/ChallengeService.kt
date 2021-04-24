package com.example.multiplication.challange


interface ChallengeService {
  fun verifyAttempt(resultAttempt: ChallengeAttemptDTO): ChallengeAttempt
}
