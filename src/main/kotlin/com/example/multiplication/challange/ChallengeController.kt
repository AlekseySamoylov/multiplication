package com.example.multiplication.challange

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/challenges")
class ChallengeController(private val challengeGeneratorService: ChallengeGeneratorService) {
  private val log = LoggerFactory.getLogger(this::class.java)

  @GetMapping("/random")
  fun getRandomChallenge(): Challenge {
    val challenge = challengeGeneratorService.randomChallenge()
    log.info("Generating a random challenge: {}", challenge)
    return challenge
  }
}
