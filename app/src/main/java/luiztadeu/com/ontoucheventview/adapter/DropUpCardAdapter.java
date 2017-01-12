package luiztadeu.com.ontoucheventview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import luiztadeu.com.ontoucheventview.R;

/**
 * Created by Luiz Tadeu on 09/01/2017.
 */

public class DropUpCardAdapter extends RecyclerView.Adapter<DropUpCardAdapter.ViewHolder> {

    private Context context;
    private List<String> lista;

    public DropUpCardAdapter(Context context, List<String> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_with_card, parent, false);

        ViewHolder vh = new ViewHolder(convertView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text1.setText(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.textview_nome);

        }
    }

    public void setList(List<String> list){
        lista = list;
    }

}
