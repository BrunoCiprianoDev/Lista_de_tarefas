package br.com.dominio.classes.validadores;

import javax.swing.*;

public class ValidadorTitulo {
    public boolean validaTitulo(String titulo){
        if(titulo.length()>70) {
            JOptionPane.showMessageDialog(null,"O titulo inserido possui mais de 70 caracteres!","Error data", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            return true;
        }
    }
}
