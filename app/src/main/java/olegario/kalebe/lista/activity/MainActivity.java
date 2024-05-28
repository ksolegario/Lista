package olegario.kalebe.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import olegario.kalebe.lista.R;
import olegario.kalebe.lista.adapter.MyAdapter;
import olegario.kalebe.lista.model.MyItem;
import olegario.kalebe.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;

    //definindo lista de itens
    List<MyItem> itens = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // obtendo o baitao fab pelo seu id
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // intent explicito para navegar para NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);

                //executando o intent com metodo especial: startActivityForResult que fara com qoue a Activity destino ira retomar com dados em algum momento para a activity que iniciou a navegação
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        //Obtendo RecycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);

        //criando o MyAdapter e ele é estabelecido no RecycleView
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);

        //Metodo setHasFixedSize indica ao Recycke que nao há variação de tamanho entre os itens da lista
        rvItens.setHasFixedSize(true);

        //criando gerenciador de layout do tipo linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //Estabelendo no Recycle
        rvItens.setLayoutManager(layoutManager);

        // Criando decolador para a lista, que consiste apenas em uma linha separando cada item e estabelecendo em Recycle
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //verificando se as condições foram cumpridas
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                //se for veredito, criamos um instancia de MyItem para guardar os dados do item
                MyItem myItem = new MyItem();

                //obtendo dados retornados de NewItemActivity e guardando em myItem
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoUri = data.getData();

                try {
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);
                    myItem.photo = photo;
                }
                catch ( FileNotFoundException e) {
                    e.printStackTrace();
                }

                //adicionando o item em uma lista de itens
                itens.add(myItem);

                //notificando o Adapter para que um novo item seja mostrado no RecycleView
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

}