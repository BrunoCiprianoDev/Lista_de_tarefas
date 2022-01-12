package br.com.dominio.classes.interfaceGrafica;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DeletePane extends JFrame{

    private Color backgroundJText = new Color(105,105,105);
    private Color fontTextColor = new Color(220,220,220);
    private Color cinzaEscuro = new Color(55,55,55);
    private JPanel painel = new JPanel();
    private JLabel labelTexto = new JLabel("Quer mesmo deletar essa tarefa?");
    private JLabel labelTitulo = new JLabel();
    protected JButton btnConfirmar  = new JButton("Sim");
    protected JButton btnNegar  = new JButton("Não");

    public DeletePane(){

        setLayout(null);
        setTitle("Login");
        setSize(400,180);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        labelTexto.setBounds(75,20,300,40);
        labelTexto.setForeground(fontTextColor);
        painel.add(labelTexto);

        btnConfirmar.setBounds(70,105, 100,20);
        btnConfirmar.setBackground(backgroundJText);
        btnConfirmar.setForeground(fontTextColor);
        btnConfirmar.setBorder(new LineBorder(backgroundJText, 2));
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setFocusPainted(false);
        painel.add(btnConfirmar);

        btnNegar.setBounds(220,105, 100,20);
        btnNegar.setBackground(backgroundJText);
        btnNegar.setForeground(fontTextColor);
        btnNegar.setBorder(new LineBorder(backgroundJText, 2));
        btnNegar.setBorderPainted(false);
        btnNegar.setFocusPainted(false);
        painel.add(btnNegar);

        painel.setLayout(null);
        painel.setBounds(0,0,400, 180);
        painel.setBackground(cinzaEscuro);
        painel.add(btnConfirmar);
        add(painel);

    }
}
