package com.example.aliya_blush

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.Home.pertemuan_3.Register_Activity
import com.example.aliya_blush.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val userDataPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)

        // Jika sudah login, langsung lempar ke BaseActivity
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
            return
        }

        // BUTTON LOGIN
        binding.btnLogin.setOnClickListener {
            val usernameInput = binding.etUsername.text.toString()
            val passwordInput = binding.etPassword.text.toString()

            // Ambil data dari registrasi
            val registeredUser = userDataPref.getString("username", null)
            val registeredPass = userDataPref.getString("password", null)

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Isi username dan password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Logic login: Cek ke data registrasi atau default admin:admin
            val isValid = (usernameInput == registeredUser && passwordInput == registeredPass) || 
                          (usernameInput == "admin" && passwordInput == "admin")

            if (isValid) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", usernameInput)
                editor.apply()

                Toast.makeText(
                    this,
                    "Login Berhasil! Selamat Datang $usernameInput 👋",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah.\nSilakan coba lagi.")
                    .setPositiveButton("Tutup", null)
                    .show()
            }
        }

        // PINDAH KE REGISTER
        binding.tvToRegister.setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
        }
    }
}
