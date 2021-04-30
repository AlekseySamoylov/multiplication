package com.example.multiplication.challange

import com.example.multiplication.users.User
import javax.persistence.*


@Entity
class ChallengeAttempt(
  @Id
  @GeneratedValue
  var id: Long? = null,
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  var user: User? = null,
  var factorA: Int? = null,
  var factorB: Int? = null,
  var resultAttempt: Int? = null,
  var correct: Boolean? = null,
) {

  fun toBuilder(): Builder {
    return Builder(id, user, factorA, factorB, resultAttempt, correct)
  }

  class Builder(
    var id: Long? = null,
    var user: User? = null,
    var factorA: Int? = null,
    var factorB: Int? = null,
    var resultAttempt: Int? = null,
    var correct: Boolean? = null,
  ) {
    fun id(id: Long) = apply { this.id = id }
    fun user(user: User) = apply { this.user = user }
    fun factorA(factorA: Int) = apply { this.factorA = factorA }
    fun factorB(factorB: Int) = apply { this.factorB = factorB }
    fun resultAttempt(resultAttempt: Int) = apply { this.resultAttempt = resultAttempt }
    fun correct(correct: Boolean) = apply { this.correct = correct }
    fun build(): ChallengeAttempt = ChallengeAttempt(id, user, factorA, factorB, resultAttempt, correct)
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as ChallengeAttempt

    if (id != other.id) return false
    if (user != other.user) return false
    if (factorA != other.factorA) return false
    if (factorB != other.factorB) return false
    if (resultAttempt != other.resultAttempt) return false
    if (correct != other.correct) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id?.hashCode() ?: 0
    result = 31 * result + (user?.hashCode() ?: 0)
    result = 31 * result + (factorA ?: 0)
    result = 31 * result + (factorB ?: 0)
    result = 31 * result + (resultAttempt ?: 0)
    result = 31 * result + (correct?.hashCode() ?: 0)
    return result
  }


}


