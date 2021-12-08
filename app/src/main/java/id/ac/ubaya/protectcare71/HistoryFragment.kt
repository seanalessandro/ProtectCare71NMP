package id.ac.ubaya.protectcare71

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.card_history.view.*
import kotlinx.android.synthetic.main.fragment_history.*
import org.json.JSONObject



class HistoryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)
        historyView.let{


            it.adapter = HistoryAdapter(Global.histories)
            it.layoutManager = linearLayoutManager
            it.setHasFixedSize(true)


        }

        val q = com.android.volley.toolbox.Volley.newRequestQueue(context)
        val url = "https://ubaya.fun/native/160419026/get_history.php"

        var stringRequest = object : StringRequest(
            com.android.volley.Request.Method.POST, url,
            com.android.volley.Response.Listener<String> {
                android.util.Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    Global.histories.clear()
                    for(i in 0 until data.length()) {
                        val historyObj = data.getJSONObject(i)
                        with(historyObj)
                        {
                            val newHistory = History(

                                getString("nama"),
                                getString("check_in"),
                                getString("check_out"),
                            )
                            Global.histories.add(newHistory)
                        }


                    }
                    updateNewHistory()
                }


            },
            com.android.volley.Response.ErrorListener {
                android.util.Log.e("apiresult", it.message.toString())
            })

        {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    fun updateNewHistory(){
        if(view == null) return
        historyView.adapter = HistoryAdapter(Global.histories)
    }

}