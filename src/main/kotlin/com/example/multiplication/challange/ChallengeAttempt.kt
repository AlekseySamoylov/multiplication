package com.example.multiplication.challange


data class ChallengeAttempt(
  val id: Long,
  val userId: Long,
  val factorA: Int,
  val factorB: Int,
  val resultAttempt: Int,
  val correct: Boolean
)
