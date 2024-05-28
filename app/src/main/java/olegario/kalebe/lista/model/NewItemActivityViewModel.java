package olegario.kalebe.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.security.PublicKey;

public class NewItemActivityViewModel extends ViewModel {

    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
