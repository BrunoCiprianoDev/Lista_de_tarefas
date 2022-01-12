package br.com.dominio.classes.interfaceGrafica;

import br.com.dominio.classes.Connection.Key;
import br.com.dominio.classes.objects.Tarefa;
import br.com.dominio.classes.validadores.ValidadorData;
import br.com.dominio.classes.validadores.ValidadorTitulo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class NewTaskWindow extends JFrame{

    private Color cinzaEscuro = new Color(55,55,55);
    private Color cinzaClaro = new Color(105,105,105);
    private Color cinzaFonte = new Color(220,220,220);
    private Font fonte = new Font("Roboto", Font.BOLD,15);
    private JPanel painel = new JPanel();
    private JLabel lbTitulo = new JLabel("Insira uma data e um titulo para a tarefa:");
    protected JTextField areaData;
    protected JTextArea areaTitulo = new JTextArea();
    protected JButton btnSalvar = new JButton("Salvar");
    protected JButton btnCancelar = new JButton("Cancelar");
    private boolean status;

    public NewTaskWindow() {

        btnCancelar.setBounds(300,130,100,20);
        btnCancelar.setBackground(cinzaClaro);
        btnCancelar.setForeground(cinzaFonte);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        painel.add(btnCancelar);

        btnSalvar.setBounds(160,130,100,20);
        btnSalvar.setBackground(cinzaClaro);
        btnSalvar.setForeground(cinzaFonte);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setFocusPainted(false);
        painel.add(btnSalvar);

        lbTitulo.setBounds(65,20, 400,20);
        lbTitulo.setForeground(cinzaFonte);
        painel.add(lbTitulo);

        areaTitulo.setBounds(65, 90, 450, 20);
        areaTitulo.setForeground(cinzaFonte);
        areaTitulo.setBackground(cinzaClaro);
        areaTitulo.setFont(fonte);
        painel.add(areaTitulo);

        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        areaData = new JFormattedTextField(mascaraData);
        areaData.setBounds(65, 50, 80, 20);
        areaData.setBorder(new LineBorder(cinzaClaro, 2));
        areaData.setForeground(cinzaFonte);
        areaData.setBackground(cinzaClaro);
        areaData.setFont(fonte);
        painel.add(areaData);

        painel.setBackground(cinzaEscuro);
        painel.setBounds(0, 0, 600, 200);
        painel.setLayout(null);
        add(painel);

        setLayout(null);
        setTitle("Nova tarefa");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    public NewTaskWindow(Tarefa tarefaAntiga){
        new NewTaskWindow();
        areaData.setText(tarefaAntiga.getData());
        areaTitulo.setText(tarefaAntiga.getTitulo());
    }
}
