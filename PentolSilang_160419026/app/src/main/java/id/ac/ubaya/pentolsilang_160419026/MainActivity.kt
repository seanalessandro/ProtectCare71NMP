package id.ac.ubaya.pentolsilang_160419026

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_players.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    var whoTurn = 1;
    lateinit var buttons: Array<Array<Button>>
    var nowRound: Int = 1
    companion object
    {
        val pemenang = "PEMENANG"
        val colorP1 = "colorP1"
        val colorP2 = "colorP2"
        val nameP1 = "nameP1"
        val nameP2 = "nameP2"
        val time = "TIME"
    }
    var player1Name = ""
    var player2Name = ""
    var player1Color = ""
    var player2Color = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        player1Name =  intent.getStringExtra(PlayersActivity.nameP1).toString()
        player2Name =  intent.getStringExtra(PlayersActivity.nameP2).toString()
        txtNamePlayer1.text = player1Name + " (O)"
        txtNamePlayer2.text = player2Name + " (X)"

        player1Color = intent.getStringExtra(PlayersActivity.colorP1).toString()
        player2Color = intent.getStringExtra(PlayersActivity.colorP2).toString()



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

        txtTurnP1.text = "YOUR TURN!"
        txtTurnP2.text = "$player1Name'S TURN!"



        buttons = Array(3)
        { i->Array(3)
            {
                    j->initButtons(i,j) //inisialisasi button
            }
        }
    }

    private fun initButtons(i: Int, j: Int): Button {
        val btn: Button = findViewById(resources.getIdentifier
            ("btn$i$j", "id", packageName))
        btn.setOnClickListener {
            tandaiFunc(btn)
            btn.isEnabled = false
            nowRound++
        }
        return btn
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you want to quit?" )
        builder.setPositiveButton("Quit!", DialogInterface.OnClickListener{
                dialogInterface, i->
            finish()
            val intent = Intent(this, PlayersActivity::class.java)
            startActivity(intent)
        })
        builder.setNegativeButton("Keep Playing!", null )
        builder.create().show()

    }

    private fun tandaiFunc(btn: Button) {
        if(whoTurn == 2)
        {
            txtTurnP1.text = "YOUR TURN!"
            txtTurnP2.text = "$player1Name'S TURN!"
        }
        else if(whoTurn == 1)
        {
            txtTurnP2.text = "YOUR TURN!"
            txtTurnP1.text = "$player2Name'S TURN!"
        }


        if(btn.text != "") return
        if(whoTurn == 1)
        {
            btn.text = "O"
            whoTurn = 2
        }
        else
        {
            btn.text = "X"
            whoTurn = 1
        }

        if(winFunc())
        {
            if(whoTurn == 1)
            {
                finnishFunc(2)
            }
            else
            {
                finnishFunc(1)
            }
        }
        else if(nowRound == 9)
        {
            drawFunc()
        }

    }

    private fun finnishFunc(i: Int) {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val finalDate = (formatter.format(date)).toString()
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(MainActivity.colorP1, player1Color)
        intent.putExtra(MainActivity.colorP2, player2Color)
        intent.putExtra(MainActivity.nameP1, player1Name)
        intent.putExtra(MainActivity.nameP2, player2Name)
        intent.putExtra(MainActivity.pemenang, i.toString())
        intent.putExtra(MainActivity.time, finalDate)

        var p1Status = ""
        var p2Status = ""

        if(i == 1)
        {
            p1Status = "Player 1 (O) : $player1Name WIN"
            p2Status = "Player 2 (X) : $player2Name"
            val builder = AlertDialog.Builder(this)
            builder.setMessage("$player1Name WIN" )
            builder.setPositiveButton("Okay!", DialogInterface.OnClickListener{
                dialogInterface, i->
                Global.history.add(History(Global.historyNumber, finalDate, p1Status,
                    p2Status, player1Color, player2Color))
                Global.historyNumber++
                startActivity(intent)
                finish()
            })
            builder.create().show()
        }
        else if(i == 2)
        {
            p1Status = "Player 1 (O) : $player1Name"
            p2Status = "Player 2 (X) : $player2Name WIN"
            val builder = AlertDialog.Builder(this)
            builder.setMessage("$player2Name WIN" )
            builder.setPositiveButton("Okay!", DialogInterface.OnClickListener{
                    dialogInterface, i->
                Global.history.add(History(Global.historyNumber, finalDate, p1Status,
                    p2Status, player1Color, player2Color))
                Global.historyNumber++
                startActivity(intent)
                finish()
            })
            builder.create().show()
        }


    }


    private fun drawFunc() {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val finalDate = (formatter.format(date)).toString()
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(MainActivity.colorP1, player1Color)
        intent.putExtra(MainActivity.colorP2, player2Color)
        intent.putExtra(MainActivity.nameP1, player1Name)
        intent.putExtra(MainActivity.nameP2, player2Name)
        intent.putExtra(MainActivity.pemenang, "3")
        val builder = AlertDialog.Builder(this)
        builder.setMessage("DRAW!" )
        builder.setPositiveButton("Hmm okay!", DialogInterface.OnClickListener{
                dialogInterface, i->
            Global.history.add(History(Global.historyNumber, finalDate, "DRAW",
                "DRAW", player1Color, player2Color))
            Global.historyNumber++
            startActivity(intent)
            finish()
        })
        builder.create().show()
    }

    private fun winFunc(): Boolean {
        val isiBtn = Array(3){i->Array(3){j->buttons[i][j].text}}

        for(index in 0..2)
        {
            if((isiBtn[index][0] != "") && (isiBtn[index][0] == isiBtn[index][1]) && (isiBtn[index][0] == isiBtn[index][2]))
                return true
        }

        for(index in 0..2)
        {
            if((isiBtn[0][index] != "") && (isiBtn[0][index] == isiBtn[1][index]) && (isiBtn[0][index] == isiBtn[2][index]) )
                return true
        }

        if(
            (isiBtn[0][0] == isiBtn[1][1]) && (isiBtn[0][0] == isiBtn[2][2]) && (isiBtn[0][0] != ""))
                return true

        if((isiBtn[0][2] == isiBtn[1][1]) && (isiBtn[0][2] == isiBtn[2][0]) && (isiBtn[0][2] != ""))
            return true

        return false
    }


}