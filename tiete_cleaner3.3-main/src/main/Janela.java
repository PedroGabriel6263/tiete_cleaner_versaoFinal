package main;

import models.MenuInicial;

import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {

    public Janela(){

        MenuInicial menu = new MenuInicial(this);

        setTitle("Tietê Cleaner");

        Dimension tamanhoTela =
                Toolkit.getDefaultToolkit().getScreenSize();

        setSize(tamanhoTela);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new BorderLayout());

        add(menu, BorderLayout.CENTER);

        setVisible(true);
    }
}