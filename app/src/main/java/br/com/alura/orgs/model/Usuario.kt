package br.com.alura.orgs.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

private const val EMAIL_REGEX= "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

@Entity
data class Usuario(
    @PrimaryKey
    val id: String,
    val email: String,
    val senha: String
) {
    @Ignore
    fun ehValido() = emailValido && senhaValida

    @Ignore
    private val emailValido = email.matches(Regex(EMAIL_REGEX))

    @Ignore
    private val senhaValida = senha.count() >= 6
}
