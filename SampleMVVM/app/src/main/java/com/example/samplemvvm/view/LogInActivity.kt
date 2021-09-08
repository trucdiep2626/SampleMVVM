package com.example.samplemvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        binding.lifecycleOwner = this
        binding.logInViewModel = logInViewModel
    }

    private fun authentication() {
        logInViewModel.mCurrentUser.observe(this, {
            if (it != null) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })
    }


}