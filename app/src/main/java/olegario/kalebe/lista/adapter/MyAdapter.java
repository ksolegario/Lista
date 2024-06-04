package olegario.kalebe.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import olegario.kalebe.lista.R;
import olegario.kalebe.lista.activity.MainActivity;
import olegario.kalebe.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {

    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {

        //Instancia para a classe MainActivity
        this.mainActivity = mainActivity;

        //Lista de objetos do tipo Myitem
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //obtendo Inflator de layouts que ler arquivos xml e cria elementos de interface
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        //criando elementos de interface e guardanno de um objeto do tipo View
        View v = inflater.inflate(R.layout.item_list, parent, false);

        //objeto do tipo View e guardado dentro de um objeto do tipo MyVieHolder
        return new MyViewHolder(v);
    }

    @Override

    //Preenche a UI com os dados de um item, recebendo dois parametros
    public  void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //obtendo item que sera usado para preencher a UI
        MyItem myItem = itens.get(position);

        //obtendo item que sera guardado dentro do ViewHolder
        View v = holder.itemView;

        //Preenchendo a UI com od dados do item
        ImageView imvfoto = v.findViewById(R.id.imvphoto);
        imvfoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

}
