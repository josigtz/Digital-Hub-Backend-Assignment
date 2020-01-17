package com.entrevista.accounts.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Transaction(@Id @JsonIgnore val id : String = UUID.randomUUID().toString(),
                       val fromAccount : Long,
                       val toAccount : Long,
                       val amount : Double,
                       val sentAt : Date?)