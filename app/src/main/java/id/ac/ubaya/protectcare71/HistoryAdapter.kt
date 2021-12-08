package id.ac.ubaya.protectcare71

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.card_history.view.*
import org.json.JSONObject
import kotlin.coroutines.coroutineContext

class HistoryAdapter (private val histories:ArrayList<History>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.card_history, parent, false)
        return HistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyPosition = histories[position]
        with(holder.v)
        {
            txtNamaLokasi.text = historyPosition.nama_lokasi
            txtCheckIn.text = historyPosition.check_in
            txtCheckOut.text = historyPosition.check_out
            cardView.setCardBackgroundColor(Color.parseColor(Global.cardColor[Global.vaccine]))
        }
    }

    override fun getItemCount(): Int {
        return histories.size
    }
}