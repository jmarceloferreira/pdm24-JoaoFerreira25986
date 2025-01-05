package com.example.vinylstore.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.vinylstore.data.CartRepository
import com.example.vinylstore.data.Vinil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.draw.clip

@Composable
fun HomeScreen(onCartClick: () -> Unit) {
    val firestore = FirebaseFirestore.getInstance()
    val cartRepository = CartRepository(firestore)
    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: return

    var vinis by remember { mutableStateOf<List<Vinil>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        firestore.collection("vinis").get()
            .addOnSuccessListener { result ->
                val fetchedVinis = result.documents.mapNotNull { doc ->
                    Vinil(
                        id = doc.id,
                        nome = doc.getString("nome") ?: "",
                        artista = doc.getString("artista") ?: "",
                        preco = doc.getString("preco") ?: "",
                        imageUrl = doc.getString("imageUrl") ?: ""
                    )
                }
                vinis = fetchedVinis
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VinylStore", color = Color.White) },
                backgroundColor = Color.Black,
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrinho",
                            tint = Color.White
                        )
                    }
                },
                elevation = 8.dp
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFBDBDBD))
                    .padding(padding)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Pesquisar por Artista", color = Color.Black) },
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

                LazyColumn(
                    modifier = Modifier.weight(1f)// Ocupa o espaço disponível
                ) {
                    items(vinis.filter {
                        it.artista.contains(searchQuery, ignoreCase = true)
                    }) { vinil ->
                        VinylCard(vinil) {
                            cartRepository.addToCart(
                                userEmail = userEmail,
                                vinil = vinil,
                                onSuccess = { Log.d("HomeScreen", "Adicionado ao carrinho!") },
                                onFailure = { Log.e("HomeScreen", "Erro ao adicionar: $it") }
                            )
                        }
                    }

                    // Footer da empresa
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "© 2025 VinylStore",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun VinylCard(vinil: Vinil, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = vinil.imageUrl),
                contentDescription = vinil.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = vinil.nome,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = vinil.artista,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "€${vinil.preco}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = onAddToCart,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Adicionar ao Carrinho")
            }
        }
    }
}
