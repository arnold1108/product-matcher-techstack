package com.adventure.accounts.query


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository: JpaRepository<AccountEntity, UUID> {

}
