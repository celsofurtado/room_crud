package br.com.fiap.estoque.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.estoque.model.Produto

@Dao
interface ProdutoDao {

  @Insert
  fun salvar(produto: Produto): Long

  @Update
  fun atualizar(produto: Produto): Int

  @Delete
  fun excluir(produto: Produto): Int

  @Query("SELECT * FROM tbl_produtos ORDER BY nome ASC")
  fun listarTodosOsProdutos(): List<Produto>

  @Query("SELECT * FROM tbl_produtos WHERE id = :id")
  fun buscarProdutoPeloId(id: Long): Produto

}