// common/src/commonMain/kotlin/com/example/data/local/LocalJsonData.kt
package com.diegoferreiracaetano.pokedex.data.pokedex.source.remote

val bulbasaurJson = """
{
  "id": 1,
  "name": "bulbasaur",
  "sprites": {
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
  },
  "types": [
    {
      "slot": 1,
      "type": {
        "name": "grass",
        "url": "https://pokeapi.co/api/v2/type/12/"
      }
    },
    {
      "slot": 2,
      "type": {
        "name": "poison",
        "url": "https://pokeapi.co/api/v2/type/4/"
      }
    }
  ]
}
""".trimIndent()

val ivysaurJson = """
{
  "id": 2,
  "name": "ivysaur",
  "sprites": {
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
  },
  "types": [
    {
      "slot": 1,
      "type": {
        "name": "grass",
        "url": "https://pokeapi.co/api/v2/type/12/"
      }
    },
    {
      "slot": 2,
      "type": {
        "name": "poison",
        "url": "https://pokeapi.co/api/v2/type/4/"
      }
    }
  ]
}
""".trimIndent()

val charmanderJson = """
{
  "id": 4,
  "name": "charmander",
  "sprites": {
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
  },
  "types": [
    {
      "slot": 1,
      "type": {
        "name": "fire",
        "url": "https://pokeapi.co/api/v2/type/10/"
      }
    }
  ]
}
""".trimIndent()

val squirtleJson = """
{
  "id": 7,
  "name": "squirtle",
  "sprites": {
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
  },
  "types": [
    {
      "slot": 1,
      "type": {
        "name": "water",
        "url": "https://pokeapi.co/api/v2/type/11/"
      }
    }
  ]
}
""".trimIndent()

val pikachuJson = """
{
  "id": 25,
  "name": "pikachu",
  "sprites": {
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
  },
  "types": [
    {
      "slot": 1,
      "type": {
        "name": "electric",
        "url": "https://pokeapi.co/api/v2/type/13/"
      }
    }
  ]
}
""".trimIndent()

val pokemonListJson = """
{
  "count": 1302,
  "next": "https://pokeapi.co/api/v2/pokemon?offset=5&limit=5",
  "previous": null,
  "results": [
    { "name": "bulbasaur", "url": "https://pokeapi.co/api/v2/pokemon/1/" },
    { "name": "ivysaur", "url": "https://pokeapi.co/api/v2/pokemon/2/" },
    { "name": "charmander", "url": "https://pokeapi.co/api/v2/pokemon/4/" },
    { "name": "squirtle", "url": "https://pokeapi.co/api/v2/pokemon/7/" },
    { "name": "pikachu", "url": "https://pokeapi.co/api/v2/pokemon/25/" }
  ]
}
""".trimIndent()