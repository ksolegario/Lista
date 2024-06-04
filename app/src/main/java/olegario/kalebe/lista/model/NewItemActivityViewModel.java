package olegario.kalebe.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.security.PublicKey;

public class NewItemActivityViewModel extends ViewModel {

    //guradando o endereço URI da foto que foi escolhida para garantir que a imagem escolhida continuara aparecendo mesmo que gire a tela enquanto NewItemActivity estiver funcionando
    Uri selectPhotoLocation = null;

    //metodo getter para obter a ista de itens
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    //metodo setter para setar o endereço URI dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
