package com.example.multiplication.challange

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
class ChallengeGeneratorImpl(private val random: Random = Random()) : ChallengeGeneratorService {
  private val log = LoggerFactory.getLogger(this::class.java)

  private fun next(): Int {
    return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR
  }

  override fun randomChallenge(): Challenge {
    log.debug("Invoke challenge generate")
    return Challenge(next(), next())
  }
}

private const val MINIMUM_FACTOR = 11
private const val MAXIMUM_FACTOR = 100
