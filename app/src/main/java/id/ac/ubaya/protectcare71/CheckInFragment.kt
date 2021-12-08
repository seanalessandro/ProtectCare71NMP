package id.ac.ubaya.protectcare71

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_check_in.*
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CheckInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_in, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Global.check_location == null)
        {
            displayLayoutCheckIn()
        }
        else
        {
            displayLayoutCheckOut()
        }

        btnCheckIn.setOnClickListener{

            val currDate = LocalDateTime.now()
            val changeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            var currDateFormatted = currDate.format(changeFormat)
            val q_2 = Volley.newRequestQueue(activity)
            val url_2 = "https://ubaya.fun/native/160419026/insert_checkIn.php"

            val stringRequest_2 = object : StringRequest(
                Request.Method.POST, url_2, Response.Listener<String> {
                    Log.d("test_res", it)
                    val locationJSON = JSONObject(it)

                    if(locationJSON.getString("result") == "success")
                    {
                        Toast.makeText(requireActivity(),
                        "Cek in berhasil",
                        Toast.LENGTH_SHORT).show()

                        Global.check_id = locationJSON.getInt("id_lokasi")
                        Global.check_location = spinner.selectedItem.toString()
                        Global.check_time = currDateFormatted.toString()
                        Global.histories.add(
                            History(
                                Global.check_location!!, Global.check_time!!, "null"
                            )
                        )

                        val spEdit = requireContext().getSharedPreferences(
                            Global.SHARED_PREFERENCE_APP,
                            Context.MODE_PRIVATE
                        ).edit()
                        spEdit.putInt(Global.SHARED_PREFERENCE_KEY_CHECK_ID, Global.check_id)
                        spEdit.putString(Global.SHARED_PREFERENCE_KEY_CHECK_LOCATION, Global.check_location)
                        spEdit.putString(Global.SHARED_PREFERENCE_KEY_CHECK_TIME, Global.check_time)
                        spEdit.apply()

                        displayLayoutCheckOut()

                    }

                },
                Response.ErrorListener {
                    Log.e("error", it.message.toString()
                }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = Global.user_id.toString()
                    params["id_lokasi"] = editTextKode.text.toString()
                    params["check_in"] = currDateFormatted.toString()
                    return params
                }
            }
            q_2.add(stringRequest_2)
        }
    }

    private fun displayLayoutCheckIn()
    {
        checkin_layout.visibility = View.VISIBLE
        checkout_layout.visibility = View.INVISIBLE
        location()
    }

    private fun displayLayoutCheckOut()
    {
        checkin_layout.visibility = View.INVISIBLE
        checkout_layout.visibility = View.VISIBLE

    }


    private fun get_location()
    {
        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.fun/native/160419026/insert_checkIn.php"
        val stringRequest = StringRequest(Request.Method.POST,
            url, {
            Log.d("test", it)
            val locJSON = JSONObject(it)

            if(locJSON.getString("result") == "OK"){
                val data = locJSON.getJSONArray("data")
                var check: ArrayList<CheckIn> = ArrayList()
                for(i in 0 until data.length())
                {
                    val locObj = data.getJSONObject(i)
                    with(locObj){
                        val newCheckIn = CheckIn(
                            getString("id"),
                            getString("nama")
                        )
                        check.add(locObj)
                    }
                    val adapter = ArrayAdapter(requireContext(), , check)
                }
            }
        })
    }
}