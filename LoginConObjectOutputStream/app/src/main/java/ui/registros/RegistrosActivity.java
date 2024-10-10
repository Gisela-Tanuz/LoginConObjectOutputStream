package ui.registros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginconobjectoutputstream.databinding.ActivityRegistrosBinding;

import model.Usuario;

public class RegistrosActivity extends AppCompatActivity {
    private ActivityRegistrosBinding binding;

    private RegistrosActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistrosActivityViewModel.class);



        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(usuario.getDni()+"");
                binding.etMail.setText(usuario.getMail());
                binding.etPass.setText(usuario.getPass());
            }
        });
        if(getIntent().getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK)
        {
            vm.recuperarUsuario();
        }

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String dni = binding.etDni.getText().toString();
                String mail = binding.etMail.getText().toString();
                String pass = binding.etPass.getText().toString();


                vm.guardar(nombre, apellido, dni, mail, pass);

            }
        });

    }
}