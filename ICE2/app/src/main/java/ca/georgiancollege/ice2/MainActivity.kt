package ca.georgiancollege.ice2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice2.databinding.ActivityMainBinding

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

        // creates a reference to the TextView
        val helloWorldString = binding.helloWorldTextView
        // changes the text property of the TextView
        helloWorldString.text = getString(R.string.hello_tom)

        val clickMeButton = binding.clickMeButton

        clickMeButton.setOnClickListener{
            Log.i("onCreate","Click Me Button Clicked!")

            binding.helloWorldTextView.text = getString(R.string.clicked)
        }

        val anotherButton = binding.anotherButton

        anotherButton.setOnClickListener {
            Log.i("onCreate","Another Button was Clicked!")

            binding.helloWorldTextView.text = getString(R.string.hello_tom)
        }
    }
}