package com.example.usnews.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.core.data.Resource
import com.example.newsapp.core.ui.NewsAdapter
import com.example.usnews.R
import com.example.usnews.databinding.FragmentHomeBinding
import com.example.usnews.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.news.observe(viewLifecycleOwner) {
            if (activity != null) {

                val newsAdapter = NewsAdapter()
                newsAdapter.onItemClick = { selectedData ->
                    val intent = Intent(activity, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.EXTRA_DATA, selectedData)
                    startActivity(intent)
                }

                homeViewModel.news.observe(viewLifecycleOwner) { news ->
                    if (news != null) {
                        when (news) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                newsAdapter.setData(news.data)
                            }

                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text =
                                    news.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }

                with(binding.rvNews) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = newsAdapter
                }
            }
        }
    }
}