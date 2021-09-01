package com.example.samplemvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.samplemvvm.CountryApplication
import com.example.samplemvvm.R
import com.example.samplemvvm.databinding.ActivityLogInBinding
import com.example.samplemvvm.viewModel.LogInViewModel
import javax.inject.Inject

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    @Inject
    lateinit var logInViewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)

        val authComponent = (application as CountryApplication).appComponent
        authComponent.inject(this)

        authentication()
        setListener()
    }

    private fun authentication() {
        logInViewModel.mCurrentUser.observe(this, {
            if (it != null) {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnLogIn.visibility = View.GONE
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                binding.progressBar.visibility = View.GONE
                binding.btnLogIn.visibility = View.VISIBLE
            }
        })
    }

    private fun setListener() {
        binding.btnLogIn.setOnClickListener {
            val username: String = binding.edtEmail.text.toString().trim()
            val password: String = binding.edtPassword.text.toString().trim()
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogIn.visibility = View.GONE
            logInViewModel.logIn(username, password, this)
        }
    }
}