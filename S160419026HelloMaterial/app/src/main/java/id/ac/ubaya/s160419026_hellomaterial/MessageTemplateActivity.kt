package id.ac.ubaya.s160419026_hellomaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_message_template.*

class MessageTemplateActivity : AppCompatActivity() {
    companion object {
        val TEMPLATE_MESSAGE = "TEMPLATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_template)
        val templates:ArrayList<String> =
            arrayListOf("Let’s meet up!", "Have you worked on the project?",
                "Movie time?", "Busy, do not disturb",
                "Why you leave me?!", "Please pay me a visit. Urgent!",
                "Please call me back")
        var lm: LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = TemplateAdapter(templates, this)

    }
}