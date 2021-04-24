package com.example.multiplication.challange

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/attempts")
class ChallengeAttemptController(private val challengeService: ChallengeService) {
  private val log = LoggerFactory.getLogger(this::class.java)

  @PostMapping
  fun postResult(@RequestBody challengeAttemptDTO: ChallengeAttemptDTO): ResponseEntity<ChallengeAttempt> {
    log.debug("Attempt posted {}", challengeAttemptDTO)
    return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO))
  }
}
