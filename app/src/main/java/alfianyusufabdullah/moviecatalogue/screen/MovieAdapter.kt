package alfianyusufabdullah.moviecatalogue.screen

import alfianyusufabdullah.moviecatalogue.R
import alfianyusufabdullah.moviecatalogue.entity.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation

class MovieAdapter(private var movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])

    fun submitNewMovies(newMovie: List<Movie>) {
        movies.clear()
        movies.addAll(newMovie)

        notifyDataSetChanged()
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.item_title)
        private val tvDateRelease = itemView.findViewById<TextView>(R.id.item_date_release)

        private val tvVote = itemView.findViewById<TextView>(R.id.item_vote)
        private val ivBackdrop = itemView.findViewById<ImageView>(R.id.item_backdrop)

        private val ivPoster = itemView.findViewById<ImageView>(R.id.item_poster)

        fun bind(movie: Movie) {

            tvTitle.text = movie.movieName
            tvDateRelease.text = movie.movieReleaseDate
            tvVote.text = movie.voteAverage.toString()

            ivBackdrop.load(movie.backdropPath) {
                transformations(BlurTransformation(itemView.context, 25f))
                crossfade(true)
            }

            ivPoster.load(movie.posterPath) {
                transformations(CircleCropTransformation())
                crossfade(true)
            }
        }
    }
}