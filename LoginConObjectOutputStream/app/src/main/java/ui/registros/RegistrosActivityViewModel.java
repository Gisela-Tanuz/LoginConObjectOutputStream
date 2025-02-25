package ui.registros;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import model.Usuario;
import request.ApiClient;
import ui.login.MainActivity;

public class RegistrosActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public RegistrosActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuario()
    {
        if(mUsuario == null)
        {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void guardar(String nombre, String apellido, String dni, String mail, String pass){
        Usuario u = new Usuario(dni, nombre, apellido, mail, pass);
        if(ApiClient.Guardar(context, u)) {

            Toast.makeText(context, "Se guardaron los datos", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else
        {
            Toast.makeText(context, "No se guardaron los datos", Toast.LENGTH_SHORT).show();

        }
    }
    public void recuperarUsuario()
    {
        Usuario us = ApiClient.leer(context);
        if(us != null)
        {
            mUsuario.setValue(us);
        }
        else{
            Toast.makeText(getApplication(), "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
}
