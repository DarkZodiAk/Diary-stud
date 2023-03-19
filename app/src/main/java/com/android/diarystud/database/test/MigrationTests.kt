package com.android.diarystud.database.test

import android.annotation.SuppressLint
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.diarystud.database.room.AppRoomDatabase
import com.android.diarystud.utils.Constants.Keys.NOTES_TABLE
import com.google.common.truth.Truth.assertThat

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "test"

@RunWith(AndroidJUnit4::class)
class MigrationTests {

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppRoomDatabase::class.java,
        listOf(),
        FrameworkSQLiteOpenHelperFactory()
    )

    /*@Test
    fun migration1To2_containsCorrectData(){
        var db = helper.createDatabase(DB_NAME, 1).apply {
            execSQL("INSERT INTO $NOTES_TABLE VALUES (0,'sample_name','sample_subtitle')")
            close()
        }

        db = helper.runMigrationsAndValidate(DB_NAME, 2, true)

        db.query("SELECT * FROM $NOTES_TABLE").apply {
            assertThat(moveToFirst()).isTrue()
            //assertThat(getInt(getColumnIndex("folder_id"))).isEqualTo(0)
        }
    }*/

    @Test
    fun testAllMigrations(){
        helper.createDatabase(DB_NAME, 1).apply { close() }
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppRoomDatabase::class.java,
            DB_NAME
        ).addMigrations(AppRoomDatabase.migration1To2).build().apply {
            openHelper.writableDatabase.close()
        }
    }
}