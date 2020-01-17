package com.entrevista.accounts.service

import com.entrevista.accounts.model.Account
import com.entrevista.accounts.model.Transaction
import com.entrevista.accounts.repository.AccountRepository
import com.entrevista.accounts.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService (@Autowired val transactionRepository: TransactionRepository,
                          @Autowired val accountRepository: AccountRepository,
                          @Autowired val accountService: AccountService){

    fun sendMoney(fromAccount : Long, toAccount : Long, ammount : Double) : ResponseEntity<Transaction> {

        //Initialize transaction
        val transaction : Transaction = Transaction(
                fromAccount = fromAccount,
                toAccount = toAccount,
                amount = ammount,
                sentAt = Calendar.getInstance().time
        )

        //Validate transaction
        val validation : ResponseEntity<Transaction> = validator(transaction)
        //In case transaction is not valid
        if(validation.statusCode == HttpStatus.BAD_REQUEST)
            return validation

        //Balances adjustment
        accountService.modifyBalance(transaction.fromAccount, -ammount)
        accountService.modifyBalance(transaction.toAccount, ammount)

        //Save transaction history
        transactionRepository.save(transaction)

        return validation
    }

    fun validator(transaction: Transaction) : ResponseEntity<Transaction>{
        var senderAccount : Optional<Account> = accountRepository.findById(transaction.fromAccount)
        var receiverAccount : Optional<Account> = accountRepository.findById(transaction.toAccount)

        //Validate accounts
        if(!senderAccount.isPresent)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error","Invalid sender account number").build()
        else if(!receiverAccount.isPresent)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error","Invalid receiver account number").build()
        //Dont let make transaction if balance is lower than -500 or is going to be lower than -500 after transaction
        if(senderAccount.get().balance < -500 || (senderAccount.get().balance - transaction.amount) < -500)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error","SenderAccount has not enough money").build()

        return ResponseEntity.accepted().body(transaction)
    }

}