package com.flexiMart.auth_service.domain

import jakarta.persistence.*


@Entity
@Table(
    name = User.TABLE_NAME,
    uniqueConstraints = [UniqueConstraint(name = "define_unique", columnNames = [User.EMAIL])]
)
data class User(

    val id:Long? = null,

    @Column(name = FIRST_NAME, nullable = false)
    val firstName:String,
    @Column(name = LAST_NAME, nullable = false)
    val lastName: String,

    @Column(name = EMAIL, nullable = false, unique = true)
    val email: String,

    @Column(name = PASSWORD, nullable = false)
    val password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ROLE_ID)
    val role: Role? = null


)
{
    companion object{

        const val TABLE_NAME = "user_table"
        private const val FIRST_NAME = "first_name"
        private const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val ROLE_ID = "role_id"
    }
}
