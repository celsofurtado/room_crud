package br.com.fiap.estoque.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_produtos")
data class Produto(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,

  var nome: String = "",
  var quantidade: Int = 0,

  @ColumnInfo(name = "data_vencimento", defaultValue = "2024-01-01")
  var dataVencimento: String = "",

  var disponivel: Boolean = false
)
