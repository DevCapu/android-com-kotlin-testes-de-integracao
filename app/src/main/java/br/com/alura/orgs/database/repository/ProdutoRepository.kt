package br.com.alura.orgs.database.repository

import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.model.Produto

class ProdutoRepository(
    private val dao: ProdutoDao
) {
    suspend fun salva(produto: Produto) = dao.salva(produto)

    suspend fun remove(produto: Produto) = dao.remove(produto)

    fun buscaPorId(id: Long) = dao.buscaPorId(id)

    fun buscaTodos() = dao.buscaTodos()
}