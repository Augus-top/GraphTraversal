/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFileChooser;

/**
 *
 * @author Augustop
 */
public class JFileChooserController {
    
    public static final String fileName = "JFileChooserOptions.prop";
    
    public static void storeLastDirectory(JFileChooser fc) throws IOException{
        File file = new File(fileName);
        Properties p = new Properties();
        p.setProperty("UltimoDiretorio", fc.getCurrentDirectory().toString());
        BufferedWriter writter = new BufferedWriter(new FileWriter(file));
        p.store(writter, "Properties of the user frame");
    }

    public static String getLastDirectory() throws IOException{
        File file = new File(fileName);
        if(!file.exists()){
            return "";
        }
        Properties p = new Properties();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        p.load(reader);
        return p.getProperty("UltimoDiretorio");
    }
}
