package com.vi.newsapp.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vi.newsapp.R
import com.vi.newsapp.base.event.EventObserver
import com.vi.newsapp.databinding.FragmentArticleBinding
import com.vi.newsapp.domain.articles.Article
import com.vi.newsapp.presentation.article.delegate.ArticleDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy { findNavController() }
    private val viewModel: ArticlesViewModel by viewModel()

    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        adapter = ArticlesAdapter()
        val ctx = requireContext()

        adapter.delegatesManager
            .addDelegate(
                ArticleDelegate(
                    ctx,
                    object : ArticleDelegate.OnClickListener {
                        override fun onItemClick(article: Article, position: Int) {
                            navController.navigate(
                                ArticlesFragmentDirections.actionMainFragmentToDetailArticleFragment(
                                    id = article.id
                                )
                            )
                        }
                    })
            )
        binding.rvArticles.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            showOrHideLoading(isLoading)
        })
        viewModel.articlesLiveData.observe(viewLifecycleOwner, { items ->
            adapter.add(items)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, EventObserver { message ->
            showError(message)
        })
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.error_unknown)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun showOrHideLoading(isLoading: Boolean, @IdRes resId: Int = R.id.progressBar) {
        val progressBar = view?.findViewById<ProgressBar>(resId)
        if (isLoading) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}