package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorExcel {
    Scanner sc = new Scanner(System.in);
    private String arq;

    public LeitorExcel() {
    }        //construtor feito para opção dentro do código, não necessariamente tendo que passar um parametro.
    public LeitorExcel(String arq) { // construtor feito para receber o nome do arquivo assim como o outro, mas necessariamente precisa se ter um parametro
        this.arq = arq;
    }

    public String caminhoArq(){
        System.out.println("\nAvaliable name files: shoes, tshirts, caps");
        System.out.println("Input the file name that you want to read>> ");
        arq = sc.next().toLowerCase();
        while (!arq.equals("shoes") && !arq.equals("tshirts") && !arq.equals("caps")){
            System.out.println("input the correct file name!");
            System.out.println("\nAvaliable name files: shoes, tshirts, caps");
            System.out.println("Input the file name that you want to read>> ");
            arq = sc.next();
        }
        String arquivoCSV = "C:\\Users\\44933006806\\Desktop\\jeshoes\\python\\" + arq + ".csv";
    return arquivoCSV;
    }
    private int[] calcularLargurasColunas(List<String[]> linhas) {    //função feita para arrumar a largura das linhas no terminal, utilizando array e repetição, retornando o array.
        int colunas = linhas.get(0).length;
        int[] larguras = new int[colunas];

        for (String[] linha : linhas) {
            for (int i = 0; i < colunas; i++) {
                int larguraAtual = linha[i].length();
                if (larguraAtual > larguras[i]) {
                    larguras[i] = larguraAtual;
                }
            }
        }

        return larguras;
    }
    public void mostrarCsv(String arquivoCSV){
        String linha;

        List<String[]> linhas = new ArrayList<>();   //ARRAY PARA RECEBER AS INFORMAÇÕES DAS LINHAS DO EXCEL

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                linhas.add(dados);
            }

            int[] largurasColunas = calcularLargurasColunas(linhas); // INSTANCIANDO A FUNÇÃO EM UM ARRAY

            for (String[] dados : linhas) {
                for (int i = 0; i < dados.length; i++) {
                    String coluna = dados[i];
                    System.out.printf("%-" + largurasColunas[i] + "s", coluna);
                    System.out.print("  ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace(); //exceção de erro do codigo
        }
    }
}
