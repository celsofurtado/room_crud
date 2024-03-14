package br.com.fiap.estoque.repository

import android.content.Context
import br.com.fiap.estoque.dao.EstoqueDb
import br.com.fiap.estoque.model.Produto

class ProdutoRepository(context: Context) {

  private val db = EstoqueDb.getDataBase(context).produtoDao()

  fun salvar (produto: Produto) = db.salvar(produto)

  fun atualizar(produto: Produto) = db.atualizar(produto)

  fun listarTodosOsProdutos() = db.listarTodosOsProdutos()

  fun excluir(produto: Produto) = db.excluir(produto)

}