package com.example.scrollablexml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrollablexml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val year = arguments?.getString("year")
        val description = arguments?.getString("description")
        val imageResId = arguments?.getInt("imageResId", 0) ?: 0

        binding.tvTitle.text = title
        binding.tvYear.text = year
        binding.tvDetail.text = description
        binding.detailImage.setImageResource(imageResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(title: String, year: String, description: String, imageResId: Int): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle().apply {
                putString("title", title)
                putString("year", year)
                putString("description", description)
                putInt("imageResId", imageResId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
