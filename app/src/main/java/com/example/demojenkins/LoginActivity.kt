package com.example.demojenkins

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by waleed on 20/03/2018.
 */

// Top Level and Extension functions
inline fun <reified T> AppCompatActivity.startActivityWithFinish() {
    finish()
    startActivity(Intent(this, T::class.java))
}


// LoginActivity
class LoginActivity : AppCompatActivity(), LoginViewContract {


    private val presenter: LoginPresenter by lazy { LoginPresenter(this, LoginRepo()) }
    private val usernameEditText: EditText by lazy { findViewById<EditText>(R.id.username_edit_text) }
    private val passwordEditText: EditText by lazy { findViewById<EditText>(R.id.password_edit_text) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.e("onCreate", "onCreate:Hello World")
    }


    fun onLoginClick(v: View) = presenter.onLoginClick(usernameEditText.text.toString(),
        passwordEditText.text.toString())


    override fun showEmptyUsernameError() = showToast(getString(R.string.enter_username))

    override fun showEmptyPasswordError() = showToast(getString(R.string.enter_password))

    override fun showSomethingWentWrong() = showToast(getString(R.string.something_went_wrong))

    override fun startHomeActivity() = startActivityWithFinish<HomeActivity>()

    private fun showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}


// Contract between UI and Presenter.
interface LoginViewContract {
    fun showEmptyUsernameError()
    fun showEmptyPasswordError()
    fun startHomeActivity()
    fun showSomethingWentWrong()
}


// Presenter
class LoginPresenter(private val view: LoginViewContract, private val repo: LoginDataSource) {

    fun onLoginClick(username: String, password: String) = with(view) {
        when {
            username.isEmpty() -> showEmptyUsernameError()
            password.isEmpty() -> showEmptyPasswordError()
            repo.doLogin() -> startHomeActivity()
            else -> showSomethingWentWrong()
        }
    }

}


// Data Source and Repo

interface LoginDataSource {
    fun doLogin(): Boolean
}

class LoginRepo : LoginDataSource {
    override fun doLogin(): Boolean {
        return true
    }

}