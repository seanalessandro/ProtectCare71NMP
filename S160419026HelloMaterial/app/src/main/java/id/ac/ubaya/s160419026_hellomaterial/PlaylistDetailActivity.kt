package id.ac.ubaya.s160419026_hellomaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_playlist_detail.*
import kotlinx.android.synthetic.main.drawer_layout.*

class PlaylistDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Radiohead"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        var drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.app_name, R.string.app_name)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.itemPremium -> Toast.makeText(this, "Premium", Toast.LENGTH_SHORT).show()
                R.id.itemChart -> Toast.makeText(this, "Chart", Toast.LENGTH_SHORT).show()
                R.id.itemPlaylist -> Toast.makeText(this, "Playlist", Toast.LENGTH_SHORT).show()
                R.id.itemFavourites -> Toast.makeText(this, "Favourites", Toast.LENGTH_SHORT).show()
                R.id.itemProfile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.itemInbox -> Toast.makeText(this, "Inbox", Toast.LENGTH_SHORT).show()
                R.id.itemSignout -> Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onResume() {
        super.onResume()

        var q = Volley.newRequestQueue(this)
        val url = "https://gist.githubusercontent.com/jasonbaldridge/2668632/raw/e56320c485a33c339791a25cc107bf70e7f1d763/music.json"

        var stringRequest = StringRequest(Request.Method.GET,url,
            {
                Log.d("playlist", it)
                var obj = JSONArray(it)
                var name = obj.getJSONObject(0).getString("name")
                var album = obj.getJSONObject(0).getJSONArray("albums").getJSONObject(0).getString("title")

                var songContent = ""
                var discoContent = "lorem ipsum disco"
                var eventContent = "lorem ipsum content"

                var playlist = obj.getJSONObject(0).getJSONArray("albums").getJSONObject(0).getJSONArray("songs")
                for(i in 0 until playlist.length()) {
                    var title = playlist.getJSONObject(i).getString("title")
                    var length = playlist.getJSONObject(i).getString("length")
                    songContent += "$title - $length\n"

                }

                var frags:ArrayList<Fragment> = ArrayList()
                frags.add(PlaylistTabFragment.newInstance(songContent))
                frags.add(PlaylistTabFragment.newInstance(discoContent))
                frags.add(PlaylistTabFragment.newInstance(eventContent))

                var adapter = MyAdapter(this, frags)
                viewPagerTab.adapter = adapter
                viewPagerTab.isUserInputEnabled = false

                tabLayout2.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        viewPagerTab.currentItem = tab!!.position
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?){ }

                    override fun onTabReselected(tab: TabLayout.Tab?) { }
                })

            },
            {
                Log.d("playlist error", it.toString())
            }
        )

        q.add(stringRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemCart ->
                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show()
            R.id.itemInbox ->
                Toast.makeText(this, "Inbox", Toast.LENGTH_SHORT).show()
            R.id.itemProfile ->
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            android.R.id.home -> onBackPressed()
        }
        return true
    }

}