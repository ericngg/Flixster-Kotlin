package project.eric.flixsterkotlin

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MOVIE = 0
    private val IMAGE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            MOVIE -> {
                val v1: View = inflater.inflate(R.layout.item_movie, parent, false)
                viewHolder = ViewHolder(v1)
            }
            IMAGE -> {
                val v2: View = inflater.inflate(R.layout.item_movie2, parent, false)
                viewHolder = ViewHolder2(v2)
            }
            else -> {
                val v1: View = inflater.inflate(R.layout.item_movie, parent, false)
                viewHolder = ViewHolder(v1)
            }
        }

        return viewHolder

        /*
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)

         */
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            MOVIE -> {
                val vh1 = holder as ViewHolder
                vh1.bind(movies[position])
            }
            IMAGE -> {
                val vh2 = holder as ViewHolder2
                vh2.bind(movies[position])
            }
            else -> {
                val vh1 = holder as ViewHolder
                vh1.bind(movies[position])
            }
        }

        /*
        val movie = movies[position]
        holder.bind(movie)
         */
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int {
        val movie = movies[position]
        if (movie.rating >= 5) {
            return IMAGE
        } else if (movie.rating < 5) {
            return MOVIE
        }

        return -1
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(movie: Movie) {

            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            val orientation = context.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(context)
                    .load(movie.posterImageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(ivPoster)

            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Glide.with(context)
                    .load(movie.backdropImageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(ivPoster)
            }
        }
    }

    inner class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(movie: Movie) {
            val orientation = context.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(context)
                    .load(movie.posterImageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(ivPoster)

            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Glide.with(context)
                    .load(movie.backdropImageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(ivPoster)
            }
        }
    }
}
