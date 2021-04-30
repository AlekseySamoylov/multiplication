package com.example.multiplication

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfiguration {
  @Bean
  fun hibernateModule(): Module {
    return Hibernate5Module()
  }
}
