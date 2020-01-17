package com.entrevista.accounts.service

import com.entrevista.accounts.model.Account
import com.entrevista.accounts.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService (@Autowired val accountRepository: AccountRepository){

    /*
    Function to add or decrease balance
     */
    fun modifyBalance(account : Long, amount : Double) : Account {
        var accountObject : Optional<Account> = accountRepository.findById(account)
        accountObject.get().balance +=  amount
        return accountRepository.save(accountObject.get())
    }
}