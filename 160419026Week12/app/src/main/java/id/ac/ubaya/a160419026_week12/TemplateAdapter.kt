package id.ac.ubaya.a160419026_week12

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template_card.view.*

class TemplateAdapter(var templates: ArrayList<String>, var activity: Activity)
    : RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    class TemplateViewHolder(var v: View, var activity:Activity)
        :RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            :TemplateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.template_card, parent,false)
        return TemplateViewHolder(v, activity)
    }

    override fun getItemCount(): Int {
        return templates.size
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.v.txtTemplate.text = templates[position]
        holder.v.btnPick.setOnClickListener {
            val intent = Intent()
            intent.putExtra("result",
                templates[position])
            holder.activity.setResult(Activity.RESULT_OK, intent)
            holder.activity.finish()
        }
    }

}
