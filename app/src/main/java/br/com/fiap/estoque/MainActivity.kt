package br.com.fiap.estoque

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.estoque.model.Produto
import br.com.fiap.estoque.repository.ProdutoRepository
import br.com.fiap.estoque.ui.theme.EstoqueTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      EstoqueTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          CadastroScreen()
        }
      }
    }
  }
}

@Composable
fun CadastroScreen() {

  val context = LocalContext.current
  val produtoRepository = ProdutoRepository(context)

  var listaDeProdutos by remember {
    mutableStateOf(produtoRepository.listarTodosOsProdutos())
  }

  var nomeProdutoState by remember {
    mutableStateOf("")
  }

  var quantidadeState by remember {
    mutableStateOf("")
  }

  var dataVencimentoState by remember {
    mutableStateOf("")
  }

  var disponivelState by remember {
    mutableStateOf(false)
  }

  Column(
    modifier = Modifier.padding(16.dp)
  ) {
    Text(
      text = "Cadastro de produtos",
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    OutlinedTextField(
      value = nomeProdutoState,
      onValueChange = { nome ->
        Log.i("FIAP", "CadastroScreen: $nome")
        nomeProdutoState = nome
      },
      label = { Text(text = "Nome do produto") },
      modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
      value = quantidadeState,
      onValueChange = {qtde ->
        quantidadeState = qtde
      },
      label = { Text(text = "Quantidade em estoque") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
      value = dataVencimentoState,
      onValueChange = {
        dataVencimentoState = it
      },
      label = { Text(text = "Data de vencimento") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email
      )
    )
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Checkbox(
        checked = disponivelState,
        onCheckedChange = {
          disponivelState = it
        }
      )
      Text(text = "Disponível")
    }
    Button(
      onClick = {
        val produto = Produto(
          nome = nomeProdutoState,
          dataVencimento = dataVencimentoState,
          disponivel = disponivelState,
          quantidade = quantidadeState.toInt()
        )
        produtoRepository.salvar(produto)
        listaDeProdutos = produtoRepository.listarTodosOsProdutos()
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Salvar")
    }
    Button(onClick = {
      val produto = Produto(
        id = 6,
        nome = "Teste de atualização",
        dataVencimento = "Teste de data",
        disponivel = false,
        quantidade = 1899
      )
      produtoRepository.atualizar(produto)
    }) {
      Text(text = "Atualizar")
    }
    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ){
      items(listaDeProdutos){ prod ->
        Card(
          modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(end = 16.dp)
        ) {
          Column {
            Text(text = prod.nome)
            Text(text = prod.dataVencimento)
            Text(text = prod.quantidade.toString())
            Checkbox(checked = prod.disponivel,
              onCheckedChange = {}
            )
          }
        }
      }
//      items(listaDeProdutos){

//      }



    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CadastroScreenPreview() {
  EstoqueTheme {
    CadastroScreen()
  }
}