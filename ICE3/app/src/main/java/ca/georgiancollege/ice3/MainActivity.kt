package ca.georgiancollege.ice3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // instantiates an object of type ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)


        enableEdgeToEdge()

        // sets the content view to the "super view" or main view group
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // changes the text property of the TextView
        binding.helloWorldTextView.text = getString(R.string.hello_tom)

        binding.clickMeButton.setOnClickListener{
            sharedEventHandler(it as Button)
        }

        binding.anotherButton.setOnClickListener {
            sharedEventHandler(it as Button)
        }
    }

    private fun sharedEventHandler(button: Button) = when(button)
    {
        binding.clickMeButton -> binding.helloWorldTextView.text =
            (if(binding.helloWorldTextView.text == "Clicked!")
                getString(R.string.not_clicked)
            else
                getString(R.string.clicked))

        binding.anotherButton ->
            binding.helloWorldTextView.text = getString(R.string.do_something_else)

        else -> {}
    }
}