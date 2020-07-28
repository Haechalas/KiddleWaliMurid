import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.Kegiatan
import kotlinx.android.synthetic.main.holder_kegiatan.view.*

class KegiatanAdapter(val Kegiatan: List<Kegiatan>) : RecyclerView.Adapter<KegiatanAdapter.KegiatanViewHolder>()  {

    class KegiatanViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
        return KegiatanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_kegiatan, parent, false))
    }

    override fun getItemCount() = Kegiatan.size

    override fun onBindViewHolder(holder: KegiatanAdapter.KegiatanViewHolder, position: Int) {


    }

}




