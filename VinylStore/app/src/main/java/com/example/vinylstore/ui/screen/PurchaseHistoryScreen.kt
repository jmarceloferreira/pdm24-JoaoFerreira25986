package com.example.vinylstore.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vinylstore.data.PurchaseRepository
import com.example.vinylstore.data.Vinil
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PurchaseHistoryScreen(
    userEmail: String,
    purchaseRepository: PurchaseRepository = PurchaseRepository(FirebaseFirestore.getInstance())
) {
    var purchaseItems by remember { mutableStateOf<List<Vinil>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(userEmail) {
        purchaseRepository.getPurchaseHistory(
            userEmail = userEmail,
            onSuccess = { items ->
                purchaseItems = items
            },
            onFailure = { exception ->
                errorMessage = "Erro ao buscar histórico: ${exception.message}"
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Histórico de Compras", color = Color.White) },
                backgroundColor = Color.Black
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFBDBDBD))
                    .padding(16.dp)
            ) {
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                if (purchaseItems.isEmpty() && errorMessage.isEmpty()) {
                    Text(
                        text = "Nenhuma compra encontrada.",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(purchaseItems) { item ->
                            PurchaseItemCard(
                                nome = item.nome,
                                artista = item.artista,
                                preco = item.preco,
                                imageUrl = item.imageUrl
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun PurchaseItemCard(
    nome: String,
    artista: String,
    preco: String,
    imageUrl: String
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
        }
    }
}
