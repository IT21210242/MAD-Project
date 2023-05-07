package com.example.travelsl

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.travelsl.models.DoctorModel
import com.google.firebase.database.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.travelsl", appContext.packageName)
    }

    private lateinit var database: DatabaseReference

    @Before
    fun setUp() {
        database = FirebaseDatabase.getInstance().getReference("Doctors")
    }
    @Test
    fun testInsertUser() {

        val doctorId = database.push().key.toString()
        val doctorDb = DoctorModel(doctorId,"Bruce Wayne",
            "dermatology","MediHelp",
            "012354985", "bw@mail.com", "Western Medicine")
        database.child("users").child(doctorId).setValue(doctorDb)
        database.child("users").child(doctorId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val retrievedDoc = snapshot.getValue(doctorDb::class.java)
                assertEquals(doctorDb, retrievedDoc)
            }
            override fun onCancelled(error: DatabaseError) {
                // handle errors here
            }
        })
    }

}