import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BiodataMahasiswa extends JFrame {

    JLabel lnim, lnama, lprodi;
    JTextField tnim, tnama, tprodi;
    JButton tampilkan, reset;
    JTextArea output;


    public BiodataMahasiswa() {

        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Layout utama
        setLayout(new BorderLayout(10,10));


        // ================= INPUT DATA =================

        JPanel inputPanel = new JPanel(new GridLayout(3,2,5,5));

        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");
        lprodi = new JLabel("Program Studi");

        tnim = new JTextField();
        tnama = new JTextField();
        tprodi = new JTextField();


        inputPanel.add(lnim);
        inputPanel.add(tnim);

        inputPanel.add(lnama);
        inputPanel.add(tnama);

        inputPanel.add(lprodi);
        inputPanel.add(tprodi);



        JPanel panelAtas = new JPanel(new BorderLayout());

        panelAtas.add(new JLabel("Input Data"), BorderLayout.NORTH);
        panelAtas.add(inputPanel, BorderLayout.CENTER);



        // ================= BUTTON =================

        tampilkan = new JButton("Tampilkan");
        reset = new JButton("Reset");


        JPanel tombolPanel = new JPanel();

        tombolPanel.add(tampilkan);
        tombolPanel.add(reset);



        // ================= OUTPUT =================

        output = new JTextArea(8,30);
        output.setEditable(false);

        JScrollPane scroll = new JScrollPane(output);


        JPanel outputPanel = new JPanel(new BorderLayout());

        outputPanel.add(new JLabel("Output"), BorderLayout.NORTH);
        outputPanel.add(scroll, BorderLayout.CENTER);



        // Gabungkan tombol + output

        JPanel tengah = new JPanel(new BorderLayout());

        tengah.add(tombolPanel, BorderLayout.NORTH);
        tengah.add(outputPanel, BorderLayout.CENTER);



        add(panelAtas, BorderLayout.NORTH);
        add(tengah, BorderLayout.CENTER);



        // ================= EVENT TAMPILKAN =================

        tampilkan.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                output.setText(
                    "========== BIODATA MAHASISWA ==========\n\n" +
                    "NIM              : " + tnim.getText() + "\n" +
                    "Nama             : " + tnama.getText() + "\n" +
                    "Program Studi    : " + tprodi.getText()
                );

            }

        });



        // ================= EVENT RESET =================

        reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                output.setText("");

            }

        });


        setVisible(true);

    }



    public static void main(String[] args) {

        new BiodataMahasiswa();

    }

}