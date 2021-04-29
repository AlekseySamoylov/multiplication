package com.example.multiplication.users

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity(name = "users")
data class User(
  @Id
  @GeneratedValue
  var id: Long? = null,
  var alias: String = ""
)
