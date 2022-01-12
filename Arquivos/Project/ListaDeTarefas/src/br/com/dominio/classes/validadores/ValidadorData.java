package br.com.dominio.classes.validadores;

import javax.swing.*;

public class ValidadorData {

    public boolean validaData(String txtAreaData) {
        try{
            String[] pt = txtAreaData.split("/");
            int dia = Integer.parseInt(pt[0]);
            int mes = Integer.parseInt(pt[1]);
            int ano = Integer.parseInt(pt[2]);
            if(!validaDia(dia,mes,ano)){
                JOptionPane.showMessageDialog(null,"Dia invalido","Error data", JOptionPane.ERROR_MESSAGE);
                return false;
            }else if(!validaMes(mes)){
                JOptionPane.showMessageDialog(null,"Mes invalido","Error data", JOptionPane.ERROR_MESSAGE);
                return false;
            }else if(!validaAno(ano)){
                JOptionPane.showMessageDialog(null,"Ano invalido","Error data", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Data invalida!","Error data", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean validaDia(int dia, int mes, int ano) {
        if (mes == 1) {
            if (dia > 0 && dia < 32) {
                return true;
            } else {
                return false;
            }
        } else if (mes == 2) {
            int bissexto = ano%4;
            if(bissexto==0){
                if(dia>0 && dia<30){
                    return true;
                }else{
                    return false;
                }
            } else {
                if(dia>0 && dia<29){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            int trigesimoPrimeiro=mes%2;
            if(trigesimoPrimeiro==0){
                if(dia>0 && dia<32){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(dia>0 && dia<31){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    private boolean validaMes(int mes){
        if(mes>0 && mes<13){
            return true;
        }else{
            return false;
        }
    }

    private boolean validaAno(int ano){
        if(ano>2021){
            return true;
        }else{
            return false;
        }
    }
}