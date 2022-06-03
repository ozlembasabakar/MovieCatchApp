package com.example.moviecatch.ui.fragments.app_intro.pages

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.moviecatch.R
import com.example.moviecatch.databinding.FragmentFifthBinding
import com.example.moviecatch.di.dao.GenreData
import com.example.moviecatch.util.StringHelper
import com.example.moviecatch.viewmodel.GenreViewModel
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthScreen : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!

    private lateinit var genreViewModel: GenreViewModel
    private lateinit var homePageViewModel: HomePageViewModel

    private var stringHelper: StringHelper? = null
    private var genreList: MutableList<GenreData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)

        stringHelper = StringHelper()

        genreList = mutableListOf()
        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        homePageViewModel.getObserverGenre().observe(viewLifecycleOwner, {
            if (it != null) {
                for (item in it.genres) {
                    val tr_name = stringHelper!!.getTrItem(item.name)
                    val genre = GenreData(0, item.id, item.name, tr_name)
                    genreList!!.add(genre)
                }
                genreViewModel.addAllGenres(genreList!!)
                findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
            }
        })

        binding.nextButton.setOnClickListener {
            homePageViewModel.loadGenreData()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nxtButton)

        prevButton?.alpha = 1f
        prevButton?.isClickable = true

        prevButton?.setOnClickListener {
            viewPager?.currentItem = 3
        }

        nextButton?.alpha = 0f
        nextButton?.isClickable = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}