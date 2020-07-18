import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiddle.kiddlewalimurid.R
import com.kiddle.kiddlewalimurid.model.kegiatan
import kotlinx.android.synthetic.main.jurnal_holder.view.*
import kotlinx.android.synthetic.main.kegiatan_holder.view.*
import kotlinx.android.synthetic.main.materi_holder.view.*

class KegiatanAdapter(val kegiatan: List<kegiatan>) : RecyclerView.Adapter<KegiatanAdapter.KegiatanViewHolder>()  {

    class KegiatanViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
        return KegiatanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.kegiatan_holder, parent, false))
    }

    override fun getItemCount() = kegiatan.size

    override fun onBindViewHolder(holder: KegiatanAdapter.KegiatanViewHolder, position: Int) {
        val kegiatan = kegiatan[position]
        holder.view.judulKG.text = kegiatan.judul
        holder.view.bg_KG.setBackgroundResource(kegiatan.img)

    }

}




