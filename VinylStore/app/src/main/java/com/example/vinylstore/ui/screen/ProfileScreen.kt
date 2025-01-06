package com.example.vinylstore.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    onViewHistoryClick: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val userName = user?.displayName ?: "Utilizador"
    val userEmail = user?.email
    val coroutineScope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Olá, $userName!",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Email: ${userEmail ?: "Não autenticado"}",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        if (userEmail != null) {
                            auth.sendPasswordResetEmail(userEmail)
                            message = "Link para redefinir a password enviado para $userEmail"
                        } else {
                            message = "Erro: Não foi possível encontrar o email do utilizador"
                        }
                    } catch (e: Exception) {
                        message = "Erro: ${e.localizedMessage}"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Alterar Password")
        }

        Button(
            onClick = { onViewHistoryClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Histórico de Compras")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                color = if (message.startsWith("Erro")) Color.Red else Color.Green,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
