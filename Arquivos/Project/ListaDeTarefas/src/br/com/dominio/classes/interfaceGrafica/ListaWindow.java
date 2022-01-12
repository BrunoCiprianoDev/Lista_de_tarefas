package br.com.dominio.classes.interfaceGrafica;

import br.com.dominio.classes.Connection.Key;
import br.com.dominio.classes.Connection.TarefaDB;
import br.com.dominio.classes.objects.Tarefa;
import br.com.dominio.classes.validadores.ValidadorData;
import br.com.dominio.classes.validadores.ValidadorTitulo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListaWindow extends JFrame implements ActionListener{

    private Color cinzaClaro = new Color(105,105,105);
    private Color cinzaEscuro = new Color(55,55,55);
    private Color corFonte = new Color(220,220,200);
    private JPanel painel = new JPanel();
    private JScrollPane scrollPane;
    private JToolBar header = new JToolBar();
    private JButton btnAdicionar = new JButton("Adicionar");
    private ArrayList<ItemGrafico> listaItems = new ArrayList<>();
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    private int indexList = 0;
    private Key key;

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource()==btnAdicionar){
            NewTaskWindow newTaskWindow = new NewTaskWindow();
            newTaskWindow.btnSalvar.addMouseListener(
                    new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            String dataInserida = newTaskWindow.areaData.getText();
                            String tituloInserido = newTaskWindow.areaTitulo.getText();
                            ValidadorData validadorData = new ValidadorData();
                            ValidadorTitulo validadorTitulo = new ValidadorTitulo();
                            if(validadorData.validaData(dataInserida) && validadorTitulo.validaTitulo(tituloInserido)){
                                TarefaDB.save(new Tarefa(dataInserida, tituloInserido), key);
                                newTaskWindow.dispose();
                                updatePainel();
                            }
                        }
                    });

        }
    }

    private void updatePainel(){
        painel.removeAll();
        repaint();
        updateToDB();
    }

    private void verifyItemsListDelete(){
        for(ItemGrafico item : listaItems){
            boolean delete = item.getDelete();
            if(delete){
                for(int i=0; i<(indexList-1); i++){
                    listaItems.get(i).setDelete(false);
                }
                DeletePane deletePane = new DeletePane();
                deletePane.btnConfirmar.addMouseListener(
                        new MouseAdapter() {
                            public void mouseReleased(MouseEvent e) {
                                TarefaDB.delete(item.getTarefa(), key);
                                deletePane.dispose();
                                updatePainel();
                            }
                        });
                deletePane.btnNegar.addMouseListener(
                        new MouseAdapter() {
                            public void mouseReleased(MouseEvent e) {
                                deletePane.dispose();
                                updatePainel();
                            }
                        });
            }
        }
    }

    private void verifyItemsListEdit(){
        for(ItemGrafico item : listaItems){
            boolean editar = item.getEditar();
            if(editar){
                for(int i=0; i<(indexList); i++){
                    listaItems.get(i).setEditar(false);
                }
                NewTaskWindow newTaskWindow = new NewTaskWindow();
                newTaskWindow.setTitle("Editar tarefa");
                newTaskWindow.areaData.setText(item.getTarefa().getData());
                newTaskWindow.areaTitulo.setText(item.getTarefa().getTitulo());
                newTaskWindow.btnSalvar.addMouseListener(
                        new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                String dataInserida = newTaskWindow.areaData.getText();
                                String tituloInserido = newTaskWindow.areaTitulo.getText();
                                ValidadorData validadorData = new ValidadorData();
                                ValidadorTitulo validadorTitulo = new ValidadorTitulo();
                                if(validadorData.validaData(dataInserida) && validadorTitulo.validaTitulo(tituloInserido)){
                                    TarefaDB.update(item.getTarefa(),new Tarefa(dataInserida, tituloInserido), key);
                                    newTaskWindow.dispose();
                                    updatePainel();
                                }
                            }
                        });
                newTaskWindow.btnCancelar.addMouseListener(
                        new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                for(int i=0; i<(indexList); i++){
                                    listaItems.get(i).setEditar(false);
                                }
                                newTaskWindow.dispose();
                            }
                        });
            }
        }
    }

    private void updateToDB(){
        indexList=0;
        listaTarefas = (ArrayList<Tarefa>) TarefaDB.selectAll(this.key);
        listaItems.clear();
        for(Tarefa tarefa : listaTarefas){
            listaItems.add(new ItemGrafico(indexList, tarefa, key));
            painel.add(listaItems.get(indexList));
            listaItems.get(indexList).btnDeletar.addMouseListener(
                    new MouseAdapter() {
                        public void mouseReleased(MouseEvent e) {
                            verifyItemsListDelete();
                        }
                    });
            listaItems.get(indexList).btnCheck.addMouseListener(
                    new MouseAdapter() {
                        public void mouseReleased(MouseEvent e) {
                            updatePainel();
                        }
                    });
            listaItems.get(indexList).btnEditar.addMouseListener(
                    new MouseAdapter() {
                        public void mouseReleased(MouseEvent e) {
                            verifyItemsListEdit();
                        }
                    });
            ++indexList;
        }
        repaint();

    }

    public ListaWindow(Key key){
        this.key = key;
        updateToDB();

        btnAdicionar.setBounds(0,0,100,20);
        btnAdicionar.setBackground(cinzaClaro);
        btnAdicionar.setForeground(corFonte);
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.addActionListener(this);

        header.setBounds(0,0,620,20);
        header.setBorder(new LineBorder(cinzaClaro, 2));
        header.setBackground(cinzaClaro);
        header.setLayout(null);
        header.add(btnAdicionar);

        setLayout(null);
        setTitle("Lista de tarefas");
        setSize(600,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        add(header);

        painel.setBackground(cinzaEscuro);
        painel.setPreferredSize(new Dimension(580,700));
        painel.setLayout(null);

        scrollPane = new JScrollPane(painel);
        scrollPane.setBounds(0,20,620,700);
        scrollPane.setBorder(new LineBorder(cinzaClaro, 2));
        add(scrollPane);
    }
}
