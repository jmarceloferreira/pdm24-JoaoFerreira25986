package com.example.vinylstore.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class CartRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    // Adiciona item ao carrinho
    fun addToCart(
        userEmail: String,
        vinil: Vinil,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val cartItem = mapOf(
            "userEmail" to userEmail,
            "vinilId" to vinil.id,
            "nome" to vinil.nome,
            "artista" to vinil.artista,
            "preco" to vinil.preco,
            "imageUrl" to vinil.imageUrl
        )

        firestore.collection("cart")
            .add(cartItem)
            .addOnSuccessListener {
                Log.d("CartRepository", "Item adicionado ao carrinho com sucesso para $userEmail")
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.e("CartRepository", "Erro ao adicionar item ao carrinho", exception)
                onFailure(exception)
            }
    }

    // Obtém itens do carrinho para um e-mail específico
    fun getUserCart(
        userEmail: String,
        onSuccess: (List<Vinil>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("cart")
            .whereEqualTo("userEmail", userEmail)
            .get()
            .addOnSuccessListener { result ->
                val cartItems = result.documents.mapNotNull { doc ->
                    Vinil(
                        id = doc.getString("vinilId") ?: "",
                        nome = doc.getString("nome") ?: "",
                        artista = doc.getString("artista") ?: "",
                        preco = doc.getString("preco") ?: "",
                        imageUrl = doc.getString("imageUrl") ?: ""
                    )
                }
                onSuccess(cartItems)
            }
            .addOnFailureListener { exception ->
                Log.e("CartRepository", "Erro ao buscar itens do carrinho", exception)
                onFailure(exception)
            }
    }

    fun removeFromCart(
        userEmail: String,
        vinilId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("cart")
            .whereEqualTo("userEmail", userEmail)
            .whereEqualTo("vinilId", vinilId)
            .get()
            .addOnSuccessListener { result ->
                val batch = firestore.batch()
                for (document in result.documents) {
                    batch.delete(document.reference)
                }
                batch.commit()
                    .addOnSuccessListener {
                        Log.d("CartRepository", "Item removido com sucesso: $vinilId")
                        onSuccess()
                    }
                    .addOnFailureListener { exception ->
                        Log.e("CartRepository", "Erro ao remover item: $vinilId", exception)
                        onFailure(exception)
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("CartRepository", "Erro ao buscar item para remoção: $vinilId", exception)
                onFailure(exception)
            }
    }

}
