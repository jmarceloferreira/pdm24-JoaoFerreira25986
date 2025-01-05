package com.example.vinylstore.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vinylstore.data.CartRepository
import com.example.vinylstore.data.Vinil
import androidx.compose.foundation.background

@Composable
fun CartScreen(
    userEmail: String,
    cartRepository: CartRepository
) {
    var sharedEmail by remember { mutableStateOf("") }
    var cartItems by remember { mutableStateOf<List<Vinil>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }
    var totalPrice by remember { mutableStateOf(0.0) }
    var isViewingOwnCart by remember { mutableStateOf(true) }

    fun loadCart(email: String) {
        isViewingOwnCart = email == userEmail
        cartRepository.getUserCart(
            userEmail = email,
            onSuccess = { items ->
                cartItems = items
                totalPrice = items.sumOf { it.preco.toDoubleOrNull() ?: 0.0 }
                Log.d("CartScreen", "Itens carregados: $items")
            },
            onFailure = { exception ->
                errorMessage = "Erro ao carregar carrinho: ${exception.message}"
                Log.e("CartScreen", "Erro ao carregar carrinho", exception)
            }
        )
    }

    fun removeFromCart(vinilId: String) {
        cartRepository.removeFromCart(
            userEmail = userEmail,
            vinilId = vinilId,
            onSuccess = {
                Log.d("CartScreen", "Item removido com sucesso: $vinilId")
                loadCart(userEmail) // Recarregar o carrinho após remoção
            },
            onFailure = { exception ->
                errorMessage = "Erro ao remover item: ${exception.message}"
                Log.e("CartScreen", "Erro ao remover item", exception)
            }
        )
    }

    LaunchedEffect(Unit) {
        loadCart(userEmail)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrinho", color = Color.White) },
                backgroundColor = Color.Black
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFBDBDBD))
            ) {
                OutlinedTextField(
                    value = sharedEmail,
                    onValueChange = { sharedEmail = it },
                    label = { Text("Ver carrinho de outro utilizador", color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                Button(
                    onClick = { loadCart(sharedEmail) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Ver Carrinho")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                if (cartItems.isEmpty()) {
                    Text(
                        text = "Nenhum item no carrinho.",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        items(cartItems) { item ->
                            CartItemCard(
                                nome = item.nome,
                                artista = item.artista,
                                preco = item.preco,
                                imageUrl = item.imageUrl,
                                isOwnCart = isViewingOwnCart,
                                onRemove = { removeFromCart(item.id) }
                            )
                        }

                        if (isViewingOwnCart) {
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { /* Lógica de pagamento aqui */ },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Black,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Efetuar Pagamento (€${"%.2f".format(totalPrice)})")
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CartItemCard(
    nome: String,
    artista: String,
    preco: String,
    imageUrl: String,
    isOwnCart: Boolean,
    onRemove: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = nome,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Artista: $artista",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Preço: €$preco",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold
                )
            }
            if (isOwnCart) {
                Button(
                    onClick = onRemove,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF303030),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.size(width = 95.dp, height = 35.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Remover", style = MaterialTheme.typography.button)
                }
            }
        }
    }
}
