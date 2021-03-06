package org.michaelbel.moviemade.presentation.listitem

import android.graphics.Typeface
import android.graphics.Typeface.DEFAULT
import android.graphics.Typeface.NORMAL
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.listitem_text.view.*
import org.michaelbel.core.adapter.ListItem
import org.michaelbel.core.adapter.ViewTypes.TEXT_ITEM
import org.michaelbel.moviemade.R
import org.michaelbel.moviemade.core.DeviceUtil
import org.michaelbel.moviemade.presentation.common.DebouncingOnClickListener
import java.io.Serializable

data class TextListItem(internal var data: Data): ListItem {

    data class Data(
            @DrawableRes internal val icon: Int = 0,
            @StringRes internal var text: Int = 0,
            internal val divider: Boolean = true,
            internal val medium: Boolean = true
    ): Serializable

    interface Listener {
        fun onClick() {}
    }

    lateinit var listener: Listener

    override fun getData() = data

    override fun getViewType() = TEXT_ITEM

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem_text, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Icon.
        if (getData().icon != 0) {
            holder.itemView.iconView.visibility = VISIBLE
            holder.itemView.iconView.setImageResource(getData().icon)
        } else {
            holder.itemView.iconView.visibility = GONE
        }

        // Text.
        holder.itemView.textView.setText(getData().text)
        holder.itemView.textView.typeface = if (getData().medium) Typeface.create("sans-serif-medium", NORMAL) else DEFAULT

        // Divider.
        holder.itemView.divider.visibility = if (getData().divider) VISIBLE else GONE

        val params: ConstraintLayout.LayoutParams = holder.itemView.divider.layoutParams as ConstraintLayout.LayoutParams
        params.marginStart = DeviceUtil.dp(holder.itemView.context, if (getData().icon == 0) 16F else 56F)
        holder.itemView.divider.layoutParams = params

        // Click.
        holder.itemView.setOnClickListener(object: DebouncingOnClickListener() {
            override fun doClick(v: View) {
                if (holder.adapterPosition != NO_POSITION) {
                    listener.onClick()
                }
            }
        })
    }

    private inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer
}