package com.entrevista.accounts.controller

import com.entrevista.accounts.model.Account
import com.entrevista.accounts.model.Transaction
import com.entrevista.accounts.repository.AccountRepository
import com.entrevista.accounts.repository.TransactionRepository
import com.entrevista.accounts.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TransactionController(@Autowired val transactionRepository: TransactionRepository,
                            @Autowired val accountRepository: AccountRepository,
                            @Autowired val transactionService: TransactionService) {
    /*
    Get all the transactions sent by the account queried
     */
    @GetMapping(value = ["/sentTransactions/{account}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSentTransactions(@PathVariable("account") account : Long) : HashMap<String,Iterable<Transaction>> = hashMapOf("transactions" to transactionRepository.findByFromAccount(account))

    /*
    Get all the transactions received to the account queried
     */
    @GetMapping(value = ["/receivedTransactions/{account}"],  produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getReceivedTransactions(@PathVariable("account") account : Long) : HashMap<String,Iterable<Transaction>> = hashMapOf("transactions" to transactionRepository.findByToAccount(account))

    /*
    Get all transactions sent and received
     */
    @GetMapping(value = ["/transactions/{account}"],  produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactions(@PathVariable("account") account : Long) : HashMap<String,Iterable<Transaction>> = hashMapOf("transactions" to transactionRepository.findByToAccountOrFromAccount(account, account))

    /*
    Get current balance
     */
    @GetMapping(value = ["/account/{account}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun account(@PathVariable("account") account : Long) : HashMap<String,Account> = hashMapOf("balance" to accountRepository.findById(account).get())

    /*
    Send money
     */
    @PostMapping(value = ["/sendMoney"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun applyTransaction(@RequestBody body: Transaction) : ResponseEntity<Transaction> = transactionService.sendMoney(body.fromAccount, body.toAccount, body.amount)
}