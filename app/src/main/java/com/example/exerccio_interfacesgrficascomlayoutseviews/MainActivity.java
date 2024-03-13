package com.example.exerccio_interfacesgrficascomlayoutseviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.exerccio_interfacesgrficascomlayoutseviews.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<CheckBox> arrayPreferencias = new ArrayList<CheckBox>();
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayPreferencias.add(binding.checkboxCinema);
        arrayPreferencias.add(binding.checkboxEsportes);
        arrayPreferencias.add(binding.checkboxGastronomia);
        arrayPreferencias.add(binding.checkboxMusica);

        binding.buttonExibir.setOnClickListener(this);
        binding.buttonLimpar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonExibir.getId())
            onExibirButtonClick();
        if (view.getId() == binding.buttonLimpar.getId())
            onLimparButtonClick();
    }

    public void onExibirButtonClick() {
        binding.labelExibirNome.setText("Nome: " + binding.editTextNome.getText());
        binding.labelExibirTelefone.setText("Telefone: " + binding.editTextTelefone.getText());
        binding.labelExibirEmail.setText("Email: " + binding.editTextEmail.getText());

        RadioButton sexo = findViewById(binding.radioGroupSexo.getCheckedRadioButtonId());

        binding.labelExibirSexo.setText("Sexo: " + (sexo == null ? "" : sexo.getText()));

        String preferencias = "";

        for(int i = 0 ; i < this.arrayPreferencias.size() ; i++) {
            CheckBox checkBox = this.arrayPreferencias.get(i);
            preferencias += checkBox.isChecked() ? checkBox.getText().toString() : "";
            if (i < this.arrayPreferencias.size() - 1 && checkBox.isChecked())
                preferencias += ", ";
            else if (checkBox.isChecked())
                preferencias += ".";
        }

        binding.labelExibirPreferencias.setText("Preferencias: " + preferencias);

        Switch noti = binding.switchNotificacoes;

        binding.labelExibirNotificacoes.setText("Notificações: " +
                (noti.isChecked() ? noti.getTextOn() : noti.getTextOff()));

        binding.layoutExibicaoDados.setVisibility(View.VISIBLE);

        shortNotify("Conteúdo exibido com sucesso!");
    }

    public void onLimparButtonClick() {
        binding.editTextNome.setText("");
        binding.editTextTelefone.setText("");
        binding.editTextEmail.setText("");

        binding.switchNotificacoes.setChecked(false);

        binding.radioGroupSexo.clearCheck();

        binding.checkboxCinema.setChecked(false);
        binding.checkboxEsportes.setChecked(false);
        binding.checkboxGastronomia.setChecked(false);
        binding.checkboxMusica.setChecked(false);

        binding.layoutExibicaoDados.setVisibility(View.INVISIBLE);

        shortNotify("Conteúdo limpado com sucesso!");
    }

    private void shortNotify(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}