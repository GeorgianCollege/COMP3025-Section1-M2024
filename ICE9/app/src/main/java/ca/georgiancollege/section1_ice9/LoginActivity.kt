package ca.georgiancollege.section1_ice9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ca.georgiancollege.section1_ice9.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: TVShowViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // initialize ViewModel
        viewModel = ViewModelProvider(this).get(TVShowViewModel::class.java)

        binding.loginButton.setOnClickListener {
            val email = binding.emailAddressEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty())
            {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful)
                        {
                            val user = auth.currentUser
                            user?.let {
                                viewModel.loadUserById(it.uid)
                            }

                            // If the sign in is successful, navigate to the MainActivity
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else
                        {
                            // If the sign in fails, display an error message
                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else
            {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}