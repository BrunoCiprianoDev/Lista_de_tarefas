package br.com.dominio.classes.objects;

public class Tarefa {
    private String data;
    private String titulo;
    private boolean check;
    public Tarefa(String data, String titulo){
        this.data=data;
        this.titulo=titulo;
    }

    public Tarefa(String data, String titulo, boolean check){
        this.data=data;
        this.titulo=titulo;
        this.check=check;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
