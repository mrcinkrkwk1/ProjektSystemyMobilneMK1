package com.example.myapplication4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseManager(context: Context) {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addUser(email: String, password: String, role: String) {
        val values = ContentValues()
        values.put(DatabaseHelper.COLUMN_EMAIL, email)
        values.put(DatabaseHelper.COLUMN_PASSWORD, password)
        values.put(DatabaseHelper.COLUMN_ROLE, role)
        db.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    fun getUser(email: String): User? {
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME} WHERE ${DatabaseHelper.COLUMN_EMAIL} = ?", arrayOf(email))
        if (cursor.moveToFirst()) {
            val columnIndexEmail = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)
            val columnIndexPassword = cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)
            val columnIndexRole = cursor.getColumnIndex(DatabaseHelper.COLUMN_ROLE)

            if (columnIndexEmail != -1 && columnIndexPassword != -1 && columnIndexRole != -1) {
                val user = User(
                    cursor.getString(columnIndexEmail),
                    cursor.getString(columnIndexPassword),
                    cursor.getString(columnIndexRole)
                )
                cursor.close()
                return user
            }
        }
        cursor.close()
        return null
    }

}
