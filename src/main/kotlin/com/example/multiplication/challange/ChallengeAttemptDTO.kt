package com.example.multiplication.challange

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Positive


data class ChallengeAttemptDTO(
  @get:Min(1) @get:Max(99)
  val factorA: Int,
  @get:Min(1) @get:Max(99)
  val factorB: Int,
  val userAlias: String,
  @get:Positive(message = "How could you possibly get a negative result here? Try again.")
  val guess: Int
)
