package id.ac.ubaya.s160419026_hellomaterial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_playlist_tab.view.*

private const val ARG_CONTENT = "content"


/**
 * A simple [Fragment] subclass.
 * Use the [PlaylistTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaylistTabFragment : Fragment() {

    private var content: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString(ARG_CONTENT)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist_tab, container, false).apply {
            textContent.text = content
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment PlaylistTabFragment.
         */

        @JvmStatic
        fun newInstance(param1: String) =
            PlaylistTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CONTENT, content)

                }
            }
    }
}