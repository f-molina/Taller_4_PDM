package com.example.myapplication.database.Entities

import android.nfc.Tag
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "libro_table",
    foreignKeys = [
        ForeignKey(entity = AutorEntity::class, parentColumns = ["id_autor"], childColumns = ["id_autores"]),
        ForeignKey(entity = TagEntity::class, parentColumns = ["id_tag"], childColumns = ["id_tag"])
    ])
data class LibroEntity(
    @PrimaryKey @ColumnInfo(name = "id_libro") val id_libro: String,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "caratula") val caratula: String,
    @ColumnInfo(name = "id_autores") val id_autores: String,
    @ColumnInfo(name = "editorial") val editorial: String,
    @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "resumen") val resumen: String?,
    @ColumnInfo(name = "id_tag") val id_tag: Int,
    @ColumnInfo(name = "edicion") val edicion: Int,
    @ColumnInfo(name = "favorito") val favorito: Boolean

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),//0
        parcel.readString(),//1
        parcel.readString(),//2
        parcel.readString(),//3
        parcel.readString(),//4
        parcel.readString(),//5
        parcel.readString(),//6
        parcel.readInt(),//7
        parcel.readInt(),//8
        parcel.readByte() != 0.toByte()) {//9
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_libro)//0
        parcel.writeString(titulo)//1
        parcel.writeString(caratula)//2
        parcel.writeValue(id_autores)//0
        parcel.writeString(editorial)//0
        parcel.writeValue(isbn)//0
        parcel.writeString(resumen)//0
        parcel.writeValue(id_tag)//0
        parcel.writeValue(edicion)//0
        parcel.writeByte(if (favorito) 1 else 0)//0
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LibroEntity> {
        override fun createFromParcel(parcel: Parcel): LibroEntity {
            return LibroEntity(parcel)
        }

        override fun newArray(size: Int): Array<LibroEntity?> {
            return arrayOfNulls(size)
        }
    }
}