package com.example.multiplication.challange

import java.util.*


class ChallengeGeneratorImpl(private val random: Random = Random()) : ChallengeGeneratorService {

  private fun next(): Int {
    return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR
  }

  override fun randomChallenge(): Challenge {
    return Challenge(next(), next())
  }
}

private const val MINIMUM_FACTOR = 11
private const val MAXIMUM_FACTOR = 100
