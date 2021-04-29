package com.example.multiplication.users

import org.springframework.data.repository.CrudRepository
import java.util.*


interface UserRepository : CrudRepository<User, Long> {
  fun findByAlias(alias: String): Optional<User>
}
