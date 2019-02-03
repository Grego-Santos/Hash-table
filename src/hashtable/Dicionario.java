/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author italo
 */
public class Dicionario {

    private final static String NAME_DICIONARIO = "dicionario_xl.txt";
    
    public static void main(String[] args) {
        //System.out.println(hasWord("batata"));
        getDicionario().forEach((p) -> {
            System.out.println(p);
        });
    }
    
    /**
     * Este método remove todos os espaços, hífens, acentuações e ç
     * @param str
     * @return 
     */
    public static String format(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll(" ", "").replace("-", "");
    }
    
    public static boolean hasWord(String word) {
        return getDicionario().stream().anyMatch((p) -> (p.equals(word)));
    }
    
    // for (String p : getDicionario()) {
    //    return p.equals(word);
    // }
    // for (String p : getDicionario()) if (p.equals(word)) return true; return false;
    // return getDicionario().stream().anyMatch((p) -> (p.equals(word)));
    
    /**
     * Este método substitui o conteúdo do dicionário
     * @param texto 
     */
    public static void setDicionario(String texto) {
        try {
            FileWriter fw = new FileWriter(new File(NAME_DICIONARIO).getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(texto);
            }
        } catch (IOException e) {
        }
    }
    
    /**
     * Este método pega os dados do dicionario
     * @return 
     */
    public static List<String> getDicionario() {
        try {
            //File file = new File("dicionario.txt");
            List<String> teste = new ArrayList();
            int i = 0;
            //if (!file.exists()) {
            //    file.createNewFile();
            //}
            FileReader ler = new FileReader(NAME_DICIONARIO);
            BufferedReader reader = new BufferedReader(ler);
            while (reader.ready()) {
                teste.add(reader.readLine());
            }
            return  teste;
        } catch (IOException e) {
            System.err.println("Falha ao ler");
            return new ArrayList<>();
        }
    }
    
}
