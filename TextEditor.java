import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
   private JTextArea textArea;
   private JFileChooser fileChooser;

   public TextEditor() {
      // Set up the main window
      setTitle("Text Editor");
      setSize(500, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create the text area
      textArea = new JTextArea();
      JScrollPane scrollPane = new JScrollPane(textArea);

      // Create the menu bar
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      JMenuItem openMenuItem = new JMenuItem("Open");
      openMenuItem.addActionListener(this);
      fileMenu.add(openMenuItem);
      JMenuItem saveMenuItem = new JMenuItem("Save");
      saveMenuItem.addActionListener(this);
      fileMenu.add(saveMenuItem);
      menuBar.add(fileMenu);
      setJMenuBar(menuBar);

      // Create the file chooser
      fileChooser = new JFileChooser();

      // Add the components to the main window
      add(scrollPane, BorderLayout.CENTER);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Open")) {
         // Handle the "Open" menu item
         int returnVal = fileChooser.showOpenDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
               BufferedReader br = new BufferedReader(new FileReader(file));
               textArea.read(br, null);
               br.close();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
         }
      } else if (e.getActionCommand().equals("Save")) {
         // Handle the "Save" menu item
         int returnVal = fileChooser.showSaveDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
               BufferedWriter bw = new BufferedWriter(new FileWriter(file));
               textArea.write(bw);
               bw.close();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Error writing file", "Error", JOptionPane.ERROR_MESSAGE);
            }
         }
      }
   }

   public static void main(String[] args) {
      TextEditor editor = new TextEditor();
      editor.setVisible(true);
   }
}
