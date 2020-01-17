package com.entrevista.accounts.repository

import com.entrevista.accounts.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction,String>{
    fun findByFromAccount(account: Long) : Iterable<Transaction>
    fun findByToAccount(account: Long): Iterable<Transaction>
    fun findByToAccountOrFromAccount(account: Long, account1: Long): Iterable<Transaction>
}