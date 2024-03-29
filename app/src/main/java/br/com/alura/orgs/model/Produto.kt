package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo val nome: String,
    @ColumnInfo val descricao: String,
    @ColumnInfo val valor: BigDecimal,
    @ColumnInfo val imagem: String? = null,
    val usuarioId: String? = null
) : Parcelable
