package com.vi.newsapp.presentation.article.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.vi.newsapp.databinding.ItemArticleBinding
import com.vi.newsapp.domain.articles.Article
import java.time.format.DateTimeFormatter

class ArticleDelegate(
    private val context: Context,
    private val listener: OnClickListener
) : AbsListItemAdapterDelegate<Article, Any, ArticleDelegate.ArticleViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    interface OnClickListener {
        fun onItemClick(article: Article, position: Int)
    }

    override fun isForViewType(article: Any, items: MutableList<Any>, position: Int): Boolean {
        return article is Article
    }

    override fun onCreateViewHolder(parent: ViewGroup): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(
        article: Article,
        holder: ArticleViewHolder,
        payloads: MutableList<Any>
    ) {
        with(holder) {
            tvTitle.text = article.title
            tvDescription.text = article.description

            Glide.with(context)
                .load(article.urlToImage)
                .error(android.R.drawable.stat_notify_error)
                .into(ivImage)

            tvDate.text = formatter.format(article.publishedAt)

            itemView.setOnClickListener { listener.onItemClick(article, adapterPosition) }
        }
    }

    class ArticleViewHolder(
        binding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val ivImage = binding.ivImage
        val tvDate = binding.tvDate
    }

}