package com.example.multiplication.challange

import com.example.multiplication.users.User


data class ChallengeAttempt(
  val id: Long,
  val user: User,
  val factorA: Int,
  val factorB: Int,
  val resultAttempt: Int,
  val correct: Boolean
)
