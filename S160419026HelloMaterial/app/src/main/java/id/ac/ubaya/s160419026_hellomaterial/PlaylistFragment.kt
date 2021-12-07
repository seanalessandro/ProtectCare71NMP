package id.ac.ubaya.s160419026_hellomaterial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_playlist.*
import org.json.JSONObject



/**
 * A simple [Fragment] subclass.
 * Use the [PlaylistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaylistFragment : Fragment() {
    val addPlaylistIntent = "ADDPLAYLIST"

    var playlists:ArrayList<Playlist> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//            fab.setOnClickListener{
//                val intent = Intent(this.context, AddPlaylistActivity::class.java)
//                startActivity(intent)
//            }

            val q = Volley.newRequestQueue(activity)
            val url = "https://ubaya.fun/native/160419026/get_playlist.php"
            var stringRequest = StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("apiresult", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")

                        for(i in 0 until data.length()) {
                            val playObj = data.getJSONObject(i)
                            val playlist = Playlist(
                                playObj.getInt("id"),
                                playObj.getString("title"),
                                playObj.getString("subtitle"),
                                playObj.getString("description"),
                                playObj.getString("image_url"),
                                playObj.getInt("num_likes")
                            )
                            playlists.add(playlist)
                        }
                        updateList()
                        Log.d("cekisiarray", playlists.toString())

                    }


                },
                Response.ErrorListener {
                    Log.e("apiresult", it.message.toString())
                })
            q.add(stringRequest)

//            val q = Volley.newRequestQueue(activity)
//            val url = "http://ubaya.fun/native/160419026/get_playlist.php"
//            var stringRequest = StringRequest(
//                Request.Method.POST, url,
//                Response.Listener<String>{
//                    Log.d("apiresult", it)
//                },
//                Response.ErrorListener {
//                    Log.e("apiresult", it.message.toString())
//                }
//            )
//            q.add(stringRequest)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener{
            val intent = Intent(context, AddPlaylistActivity::class.java)
            activity?.startActivity(intent)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    fun updateList() {
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        var recyclerView = view?.findViewById<RecyclerView>(R.id.playlistView)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = PlaylistAdapter(playlists)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlaylistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlaylistFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}