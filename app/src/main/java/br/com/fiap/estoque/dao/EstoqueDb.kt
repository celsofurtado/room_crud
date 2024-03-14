package br.com.fiap.estoque.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.estoque.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class EstoqueDb : RoomDatabase() {

  abstract fun produtoDao(): ProdutoDao

  companion object{
    private lateinit var instancia: EstoqueDb

    fun getDataBase(context: Context): EstoqueDb{

      if(!::instancia.isInitialized){
        instancia = Room
          .databaseBuilder(
            context,
            EstoqueDb::class.java,
            "estoque_db"
          )
          .allowMainThreadQueries()
          .fallbackToDestructiveMigration()
          .build()
      }

      return instancia

    }

  }


}