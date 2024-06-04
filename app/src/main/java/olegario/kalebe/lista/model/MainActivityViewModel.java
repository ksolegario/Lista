package olegario.kalebe.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    //lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();

    //metodo para getter obter a lista de itens
    public List<MyItem> getItens() {

        return itens;
    }
}