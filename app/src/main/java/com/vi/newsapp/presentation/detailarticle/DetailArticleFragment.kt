package com.vi.newsapp.presentation.detailarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vi.newsapp.R
import com.vi.newsapp.base.event.EventObserver
import com.vi.newsapp.databinding.FragmentArticleDetailBinding
import com.vi.newsapp.domain.articles.Article
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.format.DateTimeFormatter

class DetailArticleFragment : Fragment() {

    private val args: DetailArticleFragmentArgs by navArgs()
    private val viewModel: DetailArticleViewModel by viewModel { parametersOf(args.id) }
    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, { article ->
            setupViews(article)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, EventObserver { message ->
            showError(message)
        })
    }

    private fun setupViews(article: Article) {
        binding.tvTitle.text = article.title

        Glide.with(this)
            .load(article.urlToImage)
            .error(android.R.drawable.stat_notify_error)
            .into(binding.ivImage)

        binding.tvContent.text = article.content
        binding.tvDate.text = formatter.format(article.publishedAt)
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.error_unknown)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

}
