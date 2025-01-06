package com.example.vinylstore.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class PurchaseRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    fun getPurchaseHistory(
        userEmail: String,
        onSuccess: (List<Vinil>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("purchases")
            .whereEqualTo("userEmail", userEmail)
            .get()
            .addOnSuccessListener { result ->
                val purchaseItems = result.documents.mapNotNull { doc ->
                    Vinil(
                        id = doc.getString("vinilId") ?: "",
                        nome = doc.getString("nome") ?: "",
                        artista = doc.getString("artista") ?: "",
                        preco = doc.getString("preco") ?: "",
                        imageUrl = doc.getString("imageUrl") ?: ""
                    )
                }
                onSuccess(purchaseItems)
            }
            .addOnFailureListener { exception ->
                Log.e("PurchaseRepository", "Erro ao buscar histórico de compras", exception)
                onFailure(exception)
            }
    }
}
