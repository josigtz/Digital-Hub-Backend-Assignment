package com.entrevista.accounts.config

import com.entrevista.accounts.model.Account
import com.entrevista.accounts.repository.AccountRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class BataBaseConfiguration {

    @Bean
    fun databaseInitializer(accountRepository: AccountRepository) = ApplicationRunner{
        val date = Calendar.getInstance().time
        accountRepository.save(Account(
                owner = 123456789,
                account = 123456789,
                balance = 0.0,
                createdAt = date
        ))
        accountRepository.save(Account(
                owner = 123456788,
                account = 123456788,
                balance = 34567.0,
                createdAt = date
        ))
        accountRepository.save(Account(
                owner = 123456787,
                account = 123456787,
                balance = 56784.0,
                createdAt = date
        ))
        accountRepository.save(Account(
                owner = 123456786,
                account = 123456786,
                balance = 3452.0,
                createdAt = date
        ))
        accountRepository.save(Account(
                owner = 123456786,
                account = 123456786,
                balance = -500.0,
                createdAt = date
        ))
    }
}