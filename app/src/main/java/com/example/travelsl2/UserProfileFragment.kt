package com.example.travelsl2

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class UserProfileFragment : Fragment() {
    lateinit var session: LoginPref
    private lateinit var dbh: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        val txtuname = view?.findViewById<TextView>(R.id.unametxt)
        val txtname = view?.findViewById<TextView>(R.id.updatenametxt)
        val txtemail = view?.findViewById<TextView>(R.id.updateemailtxt)
        val txtpwd = view?.findViewById<TextView>(R.id.updatepwdtxt)
        val updatebtn = view?.findViewById<Button>(R.id.updatebtn)
        val deletebtn = view?.findViewById<Button>(R.id.deletebtn)


        var username: String = session.getUserDetails()
        val userdetails: UserModel = dbh.getUser(username)

        val uname: String = userdetails.username
        val name: String = userdetails.name
        val email: String = userdetails.email
        val password: String = userdetails.password

        txtuname?.setText(uname, null)
        txtname?.setText(name, null)
        txtemail?.setText(email, null)
        txtpwd?.setText(password, null)

        //val updateUser = dbh.updatedata()

        return view
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}