package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        LeitorExcel le = new LeitorExcel(); // instaciando o objeto LE

        Scanner sc = new Scanner(System.in);
        String resp = "Y";
        System.out.println("WELCOME TO THE FILES READER CSV!!");
        System.out.println("INPUT JUST THE NAME OF THE FILE, WITHOUT .CSV\n");
        while (resp.equals("Y")) {
            String arqCSV = le.caminhoArq();   // instanciando o objeto a uma variavel o caminho do arquivo;
            le.mostrarCsv(arqCSV); // função para mostrar os dados do arquivo

            System.out.println("Do you want to read another file? [Y/N]");
            resp = sc.next().toUpperCase();
        }
    }
}