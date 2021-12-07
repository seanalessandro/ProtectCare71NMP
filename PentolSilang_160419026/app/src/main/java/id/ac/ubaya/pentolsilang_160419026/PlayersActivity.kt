package id.ac.ubaya.pentolsilang_160419026

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_players.*


class PlayersActivity : AppCompatActivity() {

    companion object
    {
        val colorP1 = "colorP1"
        val colorP2 = "colorP2"
        val nameP1 = "nameP1"
        val nameP2 = "nameP2"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Global.genre)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColorP1.adapter = adapter
        spinnerColorP2.adapter = adapter

        btnPlay.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(colorP1, spinnerColorP1.selectedItem.toString())
            intent.putExtra(colorP2, spinnerColorP2.selectedItem.toString())
            intent.putExtra(nameP1, editTextPlayer1Name.text.toString())
            intent.putExtra(nameP2, editTextPlayer2Name.text.toString())

            startActivity(intent)
        }
    }
}