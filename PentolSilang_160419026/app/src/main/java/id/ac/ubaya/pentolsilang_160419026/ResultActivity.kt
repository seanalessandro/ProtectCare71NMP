package id.ac.ubaya.pentolsilang_160419026

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.cardViewP1
import kotlinx.android.synthetic.main.activity_main.cardViewP2
import kotlinx.android.synthetic.main.activity_main.txtNamePlayer1
import kotlinx.android.synthetic.main.activity_main.txtNamePlayer2
import kotlinx.android.synthetic.main.activity_main.txtTurnP1
import kotlinx.android.synthetic.main.activity_main.txtTurnP2
import kotlinx.android.synthetic.main.activity_players.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var player1Name = ""
    var player2Name = ""
    var player1Color = ""
    var player2Color = ""
    var pemenang = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        player1Name =  intent.getStringExtra(MainActivity.nameP1).toString()
        player2Name =  intent.getStringExtra(MainActivity.nameP2).toString()
        txtNamePlayer1.text = player1Name + " (O)"
        txtNamePlayer2.text = player2Name + " (X)"

        player1Color = intent.getStringExtra(MainActivity.colorP1).toString()
        player2Color = intent.getStringExtra(MainActivity.colorP2).toString()

        pemenang = intent.getStringExtra(MainActivity.pemenang).toString()
        if(pemenang == "1")
        {
            txtTurnP1.text = "YOU WIN!"
            txtTurnP2.text = "$player1Name WIN!"
        }
        else if(pemenang == "2")
        {
            txtTurnP2.text = "YOU WIN!"
            txtTurnP1.text = "$player2Name WIN!"
        }
        else
        {
            txtTurnP2.text = "DRAW!"
            txtTurnP1.text = "DRAW!"
        }

        if(player1Color == "Red")
        {
            cardViewP1.setCardBackgroundColor(android.graphics.Color.parseColor("#FF0000"))
        }
        else if(player1Color == "Green")
        {
            cardViewP1.setCardBackgroundColor(android.graphics.Color.parseColor("#00FF00"))
        }
        else if(player1Color == "Blue")
        {
            cardViewP1.setCardBackgroundColor(android.graphics.Color.parseColor("#0000FF"))
        }

        if(player2Color == "Red")
        {
            cardViewP2.setCardBackgroundColor(android.graphics.Color.parseColor("#FF0000"))
        }
        else if(player2Color == "Green")
        {
            cardViewP2.setCardBackgroundColor(android.graphics.Color.parseColor("#00FF00"))
        }
        else if(player2Color == "Blue")
        {
            cardViewP2.setCardBackgroundColor(android.graphics.Color.parseColor("#0000FF"))
        }


        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.colorP1, player1Color)
            intent.putExtra(MainActivity.colorP2, player2Color)
            intent.putExtra(MainActivity.nameP1, player1Name)
            intent.putExtra(MainActivity.nameP2, player2Name)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}