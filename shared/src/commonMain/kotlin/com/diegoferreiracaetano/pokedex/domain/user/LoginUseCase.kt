package com.diegoferreiracaetano.pokedex.domain.user

import com.diegoferreiracaetano.pokedex.data.user.UserRepository
import com.diegoferreiracaetano.pokedex.domain.session.SessionManager
import com.diegoferreiracaetano.pokedex.util.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class LoginUseCase(
    private val repository: UserRepository,
    private val sessionManager: SessionManager
) : UseCase<Pair<String, String>, Flow<String>> {

    companion object{
        private const val USER = "user"
        private const val ERROR_USER = "Usuário ou senha inválidos."
    }

    override fun invoke(params: Pair<String, String>): Flow<String> = flow {
        val user = repository.findByUser(params.first, params.second).single()

        if (user != null) {
            val token = createFakeJwt(mapOf(USER to user.toJson()))
            sessionManager.login(user, token)
            emit(token)
        } else {
            throw IllegalArgumentException(ERROR_USER)
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun createFakeJwt(payload: Map<String, Any>): String {
        val encoder = Base64.UrlSafe.withPadding(Base64.PaddingOption.ABSENT)

        val header = """{"alg":"HS256","typ":"JWT"}"""
        val body = payload.entries.joinToString(",", "{", "}") { "\"${it.key}\":\"${it.value}\"" }

        fun encode(data: String): String = encoder.encode(data.encodeToByteArray())

        return "${encode(header)}.${encode(body)}.signature"
    }
}
