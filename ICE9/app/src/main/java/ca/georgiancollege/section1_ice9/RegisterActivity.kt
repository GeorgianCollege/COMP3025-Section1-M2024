package ca.georgiancollege.section1_ice9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ca.georgiancollege.section1_ice9.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: TVShowViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // initialize the ViewModel
        viewModel = ViewModelProvider(this).get(TVShowViewModel::class.java)

        binding.RegisterButton.setOnClickListener {
            val firstName = binding.FirstNameEditText.text.toString()
            val lastName = binding.LastNameEditText.text.toString()
            val email = binding.EmailEditText.text.toString()
            val password = binding.PasswordText.text.toString()
            val confirmPassword = binding.ConfirmPassword.text.toString()

            if(firstName.isNotEmpty() &&
                lastName.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isEmpty())
            {
                if(password == confirmPassword)
                {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful)
                            {
                                val user = auth.currentUser
                                user?.let {
                                    val newUser = User(id = it.uid, firstName = firstName, lastName = lastName, email = email)
                                    viewModel.insertUser(newUser)
                                }

                                // If the registration is successful, navigate to the MainActivity
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            else
                            {
                                // If the registration fails, display an error message
                                Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else
                {
                    Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.CancelButton.setOnClickListener {
            finish()
        }
    }
}