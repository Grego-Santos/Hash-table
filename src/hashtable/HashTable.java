/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author italo
 */
public class HashTable {

    private final List<String> hashTable = new ArrayList<>();
    private final int SIZE_WORD;

    public static void main(String[] args) {
        new HashTable();
    }

    public HashTable() {
        List<String> dicionario = Dicionario.getDicionario();
        SIZE_WORD = dicionario.size();

        for (String dicio : dicionario) {
            long hash = gerarHash(Dicionario.format(dicio));
            //System.out.println(p++ + " " + (hash < 0 ? dicio : ""));
            insert(hash, dicio);
        }

        /*String palavraPesquisa = dicionario[5];
        int index = searchP(palavraPesquisa);
        int indexOriginal = gerarHash(palavraPesquisa);
        
        System.out.println("Existe a palavra [" + palavraPesquisa + "]?" + (search(palavraPesquisa) ? " Sim, e está localizada na posição [" + index + "]" + (index == indexOriginal ? ", sua posição original de hash." : ", porém sua posição original é a [" + indexOriginal + "]") : "Não"));*/
    }

    private boolean search(String palavra) {
        long hash = gerarHash(palavra);

        while (!hashTable.get(Math.toIntExact(hash)).equals(palavra)) {
            hash = (hash + 1) % hashTable.size();
            if (hashTable.get(Math.toIntExact(hash)) == null) {
                return false;
            }
        }
        return true;
    }

    private long searchP(String palavra) {
        long hash = gerarHash(palavra);

        while (!hashTable.get(Math.toIntExact(hash)).equals(palavra)) {
            hash = (hash + 1) % hashTable.size();
            if (hashTable.get(Math.toIntExact(hash)) == null) {
                return hash--;
            }
        }
        return hash;
    }

    private void listAll() {
        for (int i = 0; i < hashTable.size(); i++) {
            System.out.print("[" + i + ": " + hashTable.get(i) + "] ");
        }
    }

    private void insert(boolean show) {
        String palavra = "a";

        for (int i = 0; i < 1000; i++) {
            long hashGerado = gerarHash(palavra);
            long hashInsert = insert(hashGerado, palavra);

            if (show) {
                System.out.println("  Hash gerado: " + hashGerado);
                System.out.println("Hash inserido: " + hashInsert);
                System.out.println("");
            }
        }
    }

    private long insert(long hash, String palavra) {
        while (hashTable.get(Math.toIntExact(hash)) != null) {
            hash++;
        }

        hashTable.add(Math.toIntExact(hash), palavra);

        return hash;
    }

    long pow(int a, int b) {
        switch (b) {
            case 0:
                return 1;
            case 1:
                return a;
            default:
                return a * pow(a, b - 1);
        }
    }

    private long gerarHash(String palavra) {
        long hash = 0;
        System.out.println("\n");
        for (int i = 0; i < palavra.length(); i++) {
            int c = palavra.charAt(i) - ('a' + 1);
            System.out.println(c);
            hash = + c * pow(27, i);
            System.out.println(hash);
        }
        System.out.println(hash % SIZE_WORD);
        System.out.println("\n");
        return hash % SIZE_WORD;
    }

}
