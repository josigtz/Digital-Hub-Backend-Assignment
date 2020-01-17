package com.entrevista.accounts.model

import java.util.*
import java.util.concurrent.atomic.AtomicLong
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Account (@Id val account : Long,
                    val owner : Long,
                    var balance : Double,
                    val createdAt : Date)