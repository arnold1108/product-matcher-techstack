package com.adventure.authorization_server.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.oidc.OidcScopes
import java.util.UUID


@Entity(name = "registered_clients")
class ClientsEntity {

    @Id
    @Column(name = "id") var id: UUID = UUID.randomUUID()
    @Column(name = "client_id") var clientId: String = ""
    @Column(name = "client_secret") var clientSecret: String = ""
    @Column(name = "scopes") var scopes: String = OidcScopes.OPENID
    @Column(name = "grant_types") var grantTypes: AuthorizationGrantType = AuthorizationGrantType.AUTHORIZATION_CODE

    companion object {
        fun registerClient(
            id: UUID,
            clientId: String,
            clientSecret: String
        ) = ClientsEntity().apply {
            this.id = id
            this.clientId = clientId
            this.clientSecret = clientSecret
        }
    }

}