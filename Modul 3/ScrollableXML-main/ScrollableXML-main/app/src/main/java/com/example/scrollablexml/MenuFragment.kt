package com.example.scrollablexml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollablexml.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var falloutList: List<FalloutItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        falloutList = listOf(
            FalloutItem(1, getString(R.string.judul1), getString(R.string.year1), getString(R.string.deskripsi1), getString(R.string.wiki1), getString(R.string.detail1), R.drawable.img1),
            FalloutItem(2, getString(R.string.judul2), getString(R.string.year2), getString(R.string.deskripsi2), getString(R.string.wiki2), getString(R.string.detail2), R.drawable.img2),
            FalloutItem(3, getString(R.string.judul3), getString(R.string.year3), getString(R.string.deskripsi3), getString(R.string.wiki3), getString(R.string.detail3), R.drawable.img3),
            FalloutItem(4, getString(R.string.judul4), getString(R.string.year4), getString(R.string.deskripsi4), getString(R.string.wiki4), getString(R.string.detail4), R.drawable.img4),
            FalloutItem(5, getString(R.string.judul5), getString(R.string.year5), getString(R.string.deskripsi5), getString(R.string.wikinv), getString(R.string.detail5), R.drawable.imgnv)
        )

        val adapter = FalloutAdapter(
            falloutList,
            onDetailClick = { item ->
                val detailFragment = DetailFragment.newInstance(
                    item.title, item.year, item.detail, item.imageResId
                )

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            onWikiClick = { item ->
                val url = item.wiki
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        )

        binding.rvFallout.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFallout.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
