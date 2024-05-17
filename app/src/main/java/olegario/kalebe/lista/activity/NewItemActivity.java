package olegario.kalebe.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import olegario.kalebe.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    // atributo photoSelect do tipo Uri(um endereço que esta localizado em outras apps). Dessa forma, photoSelect guardara o endereço a foto selcionada pelo usuario, e nao a foto em si
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item);

        //obtendo imagem do botão pelo seu id
        ImageButton imgCI = findViewById(R.id.imgCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executando a galeria para escolha da foto com um intent explitico
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //foto apenas do tipo "image/*"(qaulquer tipo de imagem)
                photoPickerIntent.setType("image/*");
                //executando a intent com o metodo starActivityForResult que entrega uma imagem de documentos do Android
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //obtendo Botão
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verificando se os campos foram preenchidos
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText editText = findViewById(R.id.etTitle);
                String title = editText.getText(). toString();
                //caso os campos estejam vazios ou nenhuma imagem tenha sido escolhida, irá exibir um mensagem de erro
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessario uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //criando intent para guardar dados a serem retornados para MainActivity
                Intent i = new Intent();
                //estabelecendo o Uri da imagem escolhida dentro da inet
                i.setData(photoSelected);
                //estabelecendo título e descrição
                i.putExtra("title", title);
                i.putExtra("description", description);
                //usando o método setResult para indicar o resultado da Activity(o RESULT_OK indica que ha dados de retorno)
                setResult(Activity.RESULT_OK,i);
                //Activity finalizada
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //verificando se requestCode é referente ao fornecido chamando startActivityForResult com o id PHOTO_PICKER_REQUEST
        if (requestCode == PHOTO_PICKER_REQUEST) {
            //verificando se ResultCode é sucesso
            if (resultCode == Activity.RESULT_OK) {
                photoSelected = data.getData();
                //obtendo o ImageView e colocando o Uri nele para que a foto seja exibida na app
                ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview);
                //obtendo o Uri da foto e guardando dentro do atributo photoSelect
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }

}