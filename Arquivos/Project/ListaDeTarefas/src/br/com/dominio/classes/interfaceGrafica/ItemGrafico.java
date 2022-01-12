package br.com.dominio.classes.interfaceGrafica;

import br.com.dominio.classes.Connection.Key;
import br.com.dominio.classes.Connection.TarefaDB;
import br.com.dominio.classes.objects.Tarefa;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemGrafico extends JButton implements ActionListener {

    private Color backgroundJText = new Color(105,105,105);
    private Color fontTextColor = new Color(220,220,220);
    private Color cinzaEscuro = new Color(55,55,55);
    private Font fonteIcon = new Font("Roboto",Font.BOLD,8);
    private ImageIcon imgEdit = new ImageIcon(getClass().getResource("Imagens/editImage.png"));
    private ImageIcon imgDele = new ImageIcon(getClass().getResource("Imagens/wasteImage.png"));
    private ImageIcon imgChecked = new ImageIcon(getClass().getResource("Imagens/checkedImage.png"));
    private ImageIcon imgUnchecked = new ImageIcon(getClass().getResource("Imagens/uncheckedImage.png"));
    private JLabel labelTitulo = new JLabel();
    private JLabel labelIndice = new JLabel();
    protected JButton btnEditar = new JButton();
    protected JButton btnDeletar = new JButton();
    protected JButton btnCheck = new JButton();
    private String data;
    private String titulo;
    private Tarefa tarefa;
    private boolean valueCheck = false;
    private boolean delete;
    private boolean editar;
    private int idLocal = 0;
    private Key key;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==btnCheck){
            imprimeCheck();
        }
        if(actionEvent.getSource()==btnDeletar){
            this.delete = true;
        }
        if(actionEvent.getSource()==btnCheck){
            if(this.valueCheck) {
                TarefaDB.updateStatus(new Tarefa(this.data, this.titulo),false, key );
            }else{
                TarefaDB.updateStatus(new Tarefa(this.data, this.titulo),true, key );
            }
        }
        if(actionEvent.getSource()==btnEditar){
            this.editar = true;
        }
    }

    public void imprimeCheck(){
        if(this.valueCheck == true){
            btnCheck.setIcon(imgChecked);
            this.valueCheck = false;
        }else{
            btnCheck.setIcon(imgUnchecked);
            this.valueCheck = true;
        }
    }

    public boolean getEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean getValueCheck(){
        return this.valueCheck;
    }

    public void setValueCheck(boolean valueCheck){
        this.valueCheck = valueCheck;
    }

    public int getIdLocal(){
        return this.idLocal;
    }

    public boolean getDelete() {
        return delete;
    }

    public void setDelete(boolean delete){
        this.delete = delete;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public ItemGrafico(int y, Tarefa tarefa, Key key){
        this.tarefa = tarefa;
        this.data = tarefa.getData();
        this.titulo = tarefa.getTitulo();
        this.idLocal = y;
        this.valueCheck = tarefa.getCheck();
        this.key = key;
        this.delete = false;
        this.editar = false;

        btnDeletar.setBounds(500,1,30,30);
        btnDeletar.setBackground(backgroundJText);
        btnDeletar.setBorderPainted(false);
        btnDeletar.addActionListener(this);
        btnDeletar.setIcon(imgDele);
        add(btnDeletar);

        btnEditar.setBounds(532,1,30,30);
        btnEditar.setBackground(backgroundJText);
        btnEditar.setBorderPainted(false);
        btnEditar.addActionListener(this);
        btnEditar.setIcon(imgEdit);
        add(btnEditar);

        btnCheck.setBounds(564,1,30,30);
        btnCheck.setBackground(backgroundJText);
        btnCheck.addActionListener(this);
        btnCheck.setBorderPainted(false);
        imprimeCheck();
        add(btnCheck);

        labelTitulo.setBounds(50,5,450,20);
        labelTitulo.setForeground(fontTextColor);
        labelTitulo.setText("("+data+") "+titulo);
        add(labelTitulo);

        labelIndice.setBounds(10,5,450,20);
        labelIndice.setForeground(fontTextColor);
        labelIndice.setText(""+(y+1));
        add(labelIndice);

        setLayout(null);
        setBounds(0, 2+(y*32),600,30);
        setBorder(new LineBorder(backgroundJText, 2));
        setBackground(backgroundJText);
        addActionListener(this);
        repaint();
    }
}
