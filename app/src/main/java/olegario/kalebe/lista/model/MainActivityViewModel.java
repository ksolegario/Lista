package olegario.kalebe.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens() {

        return itens;
    }
}