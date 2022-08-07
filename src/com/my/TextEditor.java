//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.my;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JButton fontColorButton;
    JComboBox fontBox;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    TextEditor() {
        this.setTitle("My text Editor");
        this.setDefaultCloseOperation(3);
        this.setSize(800, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo((Component)null);
        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setFont(new Font("Times New Roman", 0, 14));
        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setPreferredSize(new Dimension(750, 450));
        this.scrollPane.setVerticalScrollBarPolicy(22);
        this.fontLabel = new JLabel("Font: ");
        this.fontSizeSpinner = new JSpinner();
        this.fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        this.fontSizeSpinner.setValue(14);
        this.fontSizeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                TextEditor.this.textArea.setFont(new Font(TextEditor.this.textArea.getFont().getFamily(), 0, (Integer)TextEditor.this.fontSizeSpinner.getValue()));
            }
        });
        this.fontColorButton = new JButton("Color");
        this.fontColorButton.addActionListener(this);
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        this.fontBox = new JComboBox(fonts);
        this.fontBox.addActionListener(this);
        this.fontBox.setSelectedItem("Times New Roman");
        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.openItem = new JMenuItem("Open");
        this.saveItem = new JMenuItem("Save");
        this.exitItem = new JMenuItem("Exit");
        this.openItem.addActionListener(this);
        this.saveItem.addActionListener(this);
        this.exitItem.addActionListener(this);
        this.fileMenu.add(this.openItem);
        this.fileMenu.add(this.saveItem);
        this.fileMenu.add(this.exitItem);
        this.menuBar.add(this.fileMenu);
        this.setJMenuBar(this.menuBar);
        this.add(this.fontLabel);
        this.add(this.fontSizeSpinner);
        this.add(this.fontColorButton);
        this.add(this.fontBox);
        this.add(this.scrollPane);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.fontColorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog((Component)null, "Choose a color", Color.black);
            this.textArea.setForeground(color);
        }

        if (e.getSource() == this.fontBox) {
            this.textArea.setFont(new Font((String)this.fontBox.getSelectedItem(), 0, this.textArea.getFont().getSize()));
        }

        JFileChooser fileChooser;
        if (e.getSource() == this.openItem) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", new String[]{"txt"});
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog((Component)null);
            if (response == 0) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner inFile = null;

                try {
                    inFile = new Scanner(file);
                    if (file.isFile()) {
                        while(inFile.hasNextLine()) {
                            String line = inFile.nextLine() + "\n";
                            this.textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException var17) {
                    var17.printStackTrace();
                } finally {
                    inFile.close();
                }
            }
        }

        if (e.getSource() == this.saveItem) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showSaveDialog((Component)null);
            if (response == 0) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    PrintWriter outFile = new PrintWriter(file);

                    try {
                        outFile.println(this.textArea.getText());
                    } catch (Throwable var15) {
                        try {
                            outFile.close();
                        } catch (Throwable var14) {
                            var15.addSuppressed(var14);
                        }

                        throw var15;
                    }

                    outFile.close();
                } catch (FileNotFoundException var16) {
                    var16.printStackTrace();
                }
            }
        }

        if (e.getSource() == this.exitItem) {
            System.exit(0);
        }

    }
}
