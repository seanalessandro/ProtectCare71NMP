package id.ac.ubaya.protectcare71

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragments: ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.add(CheckInFragment())
        fragments.add(HistoryFragment())
        fragments.add(ProfileFragment())

        viewPager.adapter = ViewPagerAdapter(this, fragments)
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavBar.selectedItemId = bottomNavBar.menu.getItem(position).itemId

            }
        })

        bottomNavBar.setOnItemSelectedListener {
            viewPager.currentItem = when(it.itemId)
            {
                R.id.itemCheckIn -> 0
                R.id.itemHistory -> 1
                R.id.itemProfile -> 2
                else -> 0
            }
            true
        }
    }
}