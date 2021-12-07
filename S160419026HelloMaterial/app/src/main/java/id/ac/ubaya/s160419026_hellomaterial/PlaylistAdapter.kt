package id.ac.ubaya.s160419026_hellomaterial

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_playlist_card.view.*

class PlaylistAdapter(val playlists:ArrayList<Playlist>)
    : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    class PlaylistViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.activity_playlist_card, parent,false)
        return PlaylistViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val playlist = playlists[position]
        with(holder.v)
        {
            val url = playlist.image_url
            Picasso.get().load(url).into(imgPlaylistCard)
            txtTitle.text = playlist.title
            txtSubtitle.text = playlist.subtitle
            txtDescription.text = playlist.description
            btnLike.text = playlist.num_likes.toString() + " LIKES"
            btnLike.setOnClickListener {
                val q = Volley.newRequestQueue(context)
                val url = "https://ubaya.fun/native/160419026/setlikes.php"
                val stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                        playlist.num_likes++
                        var newlikes = playlist.num_likes
                                      btnLike.text = "$newlikes LIKES"},
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    }
                )
                {
                    override fun getParams() = hashMapOf("id" to playlists[position].id.toString())
                }
                q.add(stringRequest)
                //btnLike.text = playlists[position].num_likes.toString() + " LIKES"
        }

        }


        }

    override fun getItemCount(): Int {
        return playlists.size
    }


}


