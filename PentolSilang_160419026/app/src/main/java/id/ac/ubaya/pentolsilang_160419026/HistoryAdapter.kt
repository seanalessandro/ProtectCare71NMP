package id.ac.ubaya.pentolsilang_160419026

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_card.view.*
import kotlinx.android.synthetic.main.activity_main.*

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.activity_card, parent, false)
        return HistoryViewHolder(v)

    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        with(holder.v) {
            val gameSession = Global.history[position].gameCount.toString()
            txtGameSession.text = "Game Session #$gameSession"
            txtDate.text = Global.history[position].dateNow
            txtPlayer1Status.text = Global.history[position].p1status
            txtPlayer2Status.text = Global.history[position].p2status
            val player1Color = Global.history[position].p1color
            val player2Color = Global.history[position].p2color
            if(player1Color == "Red")
            {
                txtPlayer1Status.setBackgroundColor(android.graphics.Color.parseColor("#FF0000"))
            }
            else if(player1Color == "Green")
            {
                txtPlayer1Status.setBackgroundColor(android.graphics.Color.parseColor("#00FF00"))
            }
            else if(player1Color == "Blue")
            {
                txtPlayer1Status.setBackgroundColor(android.graphics.Color.parseColor("#0000FF"))
            }

            if(player2Color == "Red")
            {
                txtPlayer2Status.setBackgroundColor(android.graphics.Color.parseColor("#FF0000"))
            }
            else if(player2Color == "Green")
            {
                txtPlayer2Status.setBackgroundColor(android.graphics.Color.parseColor("#00FF00"))
            }
            else if(player2Color == "Blue")
            {
                 txtPlayer2Status.setBackgroundColor(android.graphics.Color.parseColor("#0000FF"))
            }



        }
    }

    override fun getItemCount(): Int {
        return Global.history.size
    }

}