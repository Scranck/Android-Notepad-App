//Andy Scott
//CIS 2818
//Prof John Baugh
//11 December 2023

package com.andyscott.proj4

import android.provider.BaseColumns

object NoteContract {

    // Define table contents
    class NoteEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "notes"
            const val COLUMN_TITLE = "title"
            const val COLUMN_CONTENT = "content"
        }
    }

    // SQL query for creating the table
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${NoteEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${NoteEntry.COLUMN_TITLE} TEXT," +
                "${NoteEntry.COLUMN_CONTENT} TEXT)"
}