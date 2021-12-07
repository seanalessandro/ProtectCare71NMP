package id.ac.ubaya.s160419026_hellomaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments: ArrayList<Fragment> = ArrayList()

        fragments.add(HomeFragment())
        fragments.add(PlaylistFragment())
        fragments.add(ProfileFragment())

        viewPager.adapter = MyAdapter(this, fragments)

        viewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNav.selectedItemId = bottomNav.menu.getItem(position).itemId

            }
        })

        bottomNav.setOnNavigationItemSelectedListener {
            viewPager.currentItem = when(it.itemId) {
                R.id.itemHome -> 0
                R.id.itemPlaylist -> 1
                R.id.itemProfile -> 2
                else -> 0 // default to home
            }

            true
        }

    }
}