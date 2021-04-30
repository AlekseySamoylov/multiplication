package com.example.multiplication.challange

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/attempts")
class ChallengeAttemptController(private val challengeService: ChallengeService) {
  private val log = LoggerFactory.getLogger(this::class.java)

  @PostMapping
  fun postResult(@RequestBody @Valid challengeAttemptDTO: ChallengeAttemptDTO): ResponseEntity<ChallengeAttempt> {
    log.debug("Attempt posted {}", challengeAttemptDTO)
    return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO))
  }

  @GetMapping
  fun getLastAttemptsForUserAlias(@RequestParam alias: String): ResponseEntity<List<ChallengeAttempt>> {
    log.debug("Get last attempts for user alias {}", alias)
    return ResponseEntity.ok(challengeService.getLastAttemptsForUserAlias(alias))
  }
}
