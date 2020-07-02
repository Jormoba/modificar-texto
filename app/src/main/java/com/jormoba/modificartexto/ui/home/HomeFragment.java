package com.jormoba.modificartexto.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.jormoba.modificartexto.R;

import java.text.Normalizer;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;

    private EditText editText;
    private TextView textView;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */

        /*
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Toca el texto para copiarlo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                funcion();
            }
        });
        */

        editText = (EditText) root.findViewById(R.id.editTextTextMultiLine);
        textView = (TextView) root.findViewById(R.id.textView);
        button = (Button) root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Toast.makeText(getActivity().getApplicationContext(), "Toca el texto para copiarlo", Toast.LENGTH_SHORT).show();
                funcion();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getActivity().getApplicationContext(), "Texto copiado al portapapeles", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void funcion(){

        String texto = editText.getText().toString().toLowerCase();

        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{ASCII}]", "");

        String textoCambiado = "";
        char letra, letraCambiada;

        for (int i = 0; i < texto.length(); i++){
            letra = texto.charAt(i);
            letraCambiada = cambiarLetra(letra);
            textoCambiado = letraCambiada + textoCambiado;
        }

        textView.setText(textoCambiado);
    }
    private char cambiarLetra(char letra){

        switch (letra){
            case 'a': return 'ɐ';
            case 'b': return 'q';
            case 'c': return 'ɔ';
            case 'd': return 'p';
            case 'e': return 'ǝ';
            case 'f': return 'ɟ';
            case 'g': return 'ƃ';
            case 'h': return 'ɥ';
            case 'i': return 'ı';
            case 'j': return 'ɾ';
            case 'k': return 'ʞ';
            case 'l': return 'l';
            case 'm': return 'ɯ';
            case 'n': return 'u';
            case 'o': return 'o';
            case 'p': return 'd';
            case 'q': return 'b';
            case 'r': return 'ɹ';
            case 's': return 's';
            case 't': return 'ʇ';
            case 'u': return 'n';
            case 'v': return 'ʌ';
            case 'w': return 'ʍ';
            case 'x': return 'x';
            case 'y': return 'ʎ';
            case 'z': return 'z';

            default: return letra;
        }
    }
}