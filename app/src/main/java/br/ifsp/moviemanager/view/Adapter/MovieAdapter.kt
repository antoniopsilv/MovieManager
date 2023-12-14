package br.ifsp.moviemanager.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.ifsp.moviemanager.databinding.TileMovieBinding
import br.ifsp.moviemanager.model.entity.Movie

class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), Filterable {

    private lateinit var binding: TileMovieBinding

    var moviesList = ArrayList<Movie>()
    var listener: MovieListener?=null
    var moviesListFilterable = ArrayList<Movie>()
    fun updateList(newList: ArrayList<Movie> ){
        moviesList = newList
        moviesListFilterable = moviesList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        binding = TileMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }
    fun setClickListener(listener: MovieListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.nome.text = moviesList[position].name
        holder.anoLancamento.text = moviesList[position].releaseYear
/*        holder.producao.text = moviesList[position].producer
        holder.duracao.text = moviesList[position].duration
        holder.genero.text = moviesList[position].genre
        holder.nota.text = moviesList[position].note*/
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
    inner class MovieViewHolder(view:TileMovieBinding): RecyclerView.ViewHolder(view.root)
    {
        val nome = view.editTextNome
        val anoLancamento = view.editTextAnoLancamento
/*        val producao = view.editTextProducao
        val duracao = view.editTextDuracao
        val genero = view.editTextGenero
        val nota = view.editTextNota*/
        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    interface MovieListener
    {
        fun onItemClick(pos: Int)
    }
}