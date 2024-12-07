package com.flexiMart.auth_service.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint


@Entity
@Table(
    name = Role.TABLE_NAME,
    uniqueConstraints = [UniqueConstraint(name = "define_unique", columnNames = [Role.NAME])]
)
data class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = Role.NAME)
    val name: String

    ){

    companion object{
        const val TABLE_NAME = "role_table"
        const val NAME = "name"
    }


}
