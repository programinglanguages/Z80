/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sunny
 */
public class RelocatorTable extends JFrame {
    
    public void hexMemory(String[][] data){
        //array bidimensional de objetos con los datos de la tabla
        //array de String's con los títulos de las columnas
        String[] columnNames = {"Addr", "Opcode", "Label",
        "Instruction"};
        //se crea la Tabla
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //manejamos la salida
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });  
    }
    
        public void hexMemory(String[][] data, int lineCode){
        //array bidimensional de objetos con los datos de la tabla
        //array de String's con los títulos de las columnas
        
            String[] columnNames = {"Addr", "Opcode", "Label",
            "Instruction"};
            //se crea la Tabla
            final JTable table = new JTable(data, columnNames);
        if (lineCode == 1){
            
            table.setPreferredScrollableViewportSize(new Dimension(500, 400));
            //Creamos un JscrollPane y le agregamos la JTable
            JScrollPane scrollPane = new JScrollPane(table);
            //Agregamos el JScrollPane al contenedor
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            //manejamos la salida
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }else{
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.getDataVector().clear();
            model.addRow(data);
        }
        
    }
     
    public Object[][] AddData(){
         return null;
    }
    public void hexFilled(String[][] data){
        //array bidimensional de objetos con los datos de la tabla
        //array de String's con los títulos de las columnas
        String[] columnNames = {"0", "1", "2",
        "3","4","5","6","7","8","9","A","B","C","D","E","F"};
        //se crea la Tabla
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700, 700));
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //manejamos la salida
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });  
    }
    
    public void hexFilled(String[][] data, int lineCode){
        //array bidimensional de objetos con los datos de la tabla
        //array de String's con los títulos de las columnas
        String[] columnNames = {"0", "1", "2",
        "3","4","5","6","7","8","9","A","B","C","D","E","F"};
        //se crea la Tabla
        final JTable table = new JTable(data, columnNames);
        if (lineCode == 1){
            table.setPreferredScrollableViewportSize(new Dimension(700, 700));
            //Creamos un JscrollPane y le agregamos la JTable
            JScrollPane scrollPane = new JScrollPane(table);
            //Agregamos el JScrollPane al contenedor
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            //manejamos la salida
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
        });
        }else{
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.getDataVector().clear();
            model.addRow(data);
        }  
    }
        
    
    public ArrayList<String> AddData(String memory,String hex, String etiquete, String instruction){
        ArrayList<String> line = new ArrayList<String>();  
        line.add(memory);
        line.add(hex);
        line.add(etiquete);
        line.add(instruction);
        return line;
    }
    
    public ArrayList<String> AddData(String memory){
        ArrayList<String> line = new ArrayList<String>();  
        line.add(memory);
        return line;
    }
}
