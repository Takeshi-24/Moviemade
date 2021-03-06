package org.michaelbel.moviemade.presentation.listitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.listitem_genre.view.*
import org.michaelbel.core.adapter.ListItem
import org.michaelbel.core.adapter.ViewTypes.GENRE_ITEM
import org.michaelbel.data.remote.model.Genre
import org.michaelbel.moviemade.R

data class GenreListItem(internal var genre: Genre): ListItem {

    override fun getData() = genre

    override fun getViewType() = GENRE_ITEM

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem_genre, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.chipName.text = getData().name
    }

    private inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer
}