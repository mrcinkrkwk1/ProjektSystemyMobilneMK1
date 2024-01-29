package com.example.myapplication4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MyDatabase.db"
        const val TABLE_NAME = "Konto_uzytkownika"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "haslo"
        const val COLUMN_ROLE = "rola"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_EMAIL TEXT PRIMARY KEY, $COLUMN_PASSWORD TEXT, $COLUMN_ROLE TEXT)"
        db?.execSQL(createTableQuery)

        // Dodawanie początkowych rekordów
        addInitialRecords(db)
    }

    private fun addInitialRecords(db: SQLiteDatabase?) {
        val contentValues = ContentValues()

        contentValues.put(COLUMN_EMAIL, "jankowalski@gmail.com")
        contentValues.put(COLUMN_PASSWORD, "jan123")
        contentValues.put(COLUMN_ROLE, "rodzic")
        db?.insert(TABLE_NAME, null, contentValues)

        contentValues.clear()
        contentValues.put(COLUMN_EMAIL, "martakowalska@gmail.com")
        contentValues.put(COLUMN_PASSWORD, "marta123")
        contentValues.put(COLUMN_ROLE, "rodzic")
        db?.insert(TABLE_NAME, null, contentValues)

        contentValues.clear()
        contentValues.put(COLUMN_EMAIL, "natalkakowalska@wp.pl")
        contentValues.put(COLUMN_PASSWORD, "natalka123")
        contentValues.put(COLUMN_ROLE, "dziecko")
        db?.insert(TABLE_NAME, null, contentValues)

        contentValues.clear()
        contentValues.put(COLUMN_EMAIL, "michalkowalski@o2.pl")
        contentValues.put(COLUMN_PASSWORD, "michal123")
        contentValues.put(COLUMN_ROLE, "dziecko")
        db?.insert(TABLE_NAME, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aktualizacja bazy danych (jeśli jest wymagana)
    }
}

