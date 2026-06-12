package com.example.aliya_blush.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aliya_blush.Data.Entity.UsulanEntity

@Dao
interface UsulanDao {

    @Query("SELECT * FROM usulan")
    suspend fun getAll(): List<UsulanEntity>

    @Insert
    suspend fun insert(usulan: UsulanEntity)

    @Delete
    suspend fun delete(usulan: UsulanEntity)
}