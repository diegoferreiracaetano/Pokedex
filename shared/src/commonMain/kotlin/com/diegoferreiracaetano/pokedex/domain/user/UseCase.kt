package com.diegoferreiracaetano.pokedex.domain.user

interface UseCase<in Params, out Result> {
    operator fun invoke(params: Params): Result
}