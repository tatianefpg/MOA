/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Tatiane Paz e Pedro Moraes
 */
public class PMedidas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vertice[] vetorVertice = new Vertice[101];
        Mediana[] vetorMediana = new Mediana[10];
        lerArquivo(vetorVertice);
        populacaoInicial(vetorVertice,vetorMediana);
    }
    
    private static class Mediana{
        private Vertice mediana;
        private ArrayList<Vertice> vertices;
        private int posicao = 0;

        public Vertice getMediana() {
            return mediana;
        }

        public void setMediana(Vertice Mediana) {
            this.mediana = Mediana;
        }

        public ArrayList<Vertice> getVertices() {
            return vertices;
        }

        public void setVertices(ArrayList<Vertice> vertices) {
            this.vertices = vertices;
        }
        
        
    }
    
     public static void lerArquivo(Vertice[] vetorDados) {

        Scanner ler = new Scanner(System.in);
        
        System.out.printf("Digite o arquivo: ");
        int arquivo = ler.nextInt();

        int posicao = 0;
        
        //Le o 1 dado da 1 linha e armazena o dado na sua respectiva variavel
        vetorDados[posicao].nVertices = arquivo;
        //imprime o dado para verificacao
        System.out.printf("nº vertices[%d]: %d\n",posicao,vetorDados[posicao].nVertices);
        
        //Le o 2 dado da 1 linha e armazena o dado na sua respectiva variavel
        arquivo = ler.nextInt();
        vetorDados[posicao].nMedianas = arquivo;
        //imprime o dado para teste
        System.out.printf("nº medianas[%d]: %d\n",posicao,vetorDados[posicao].nMedianas);
        
        //le a segunda linha ate a ultima linha -> "vetor vai 1 até 100"
        for(posicao = 1; posicao < 101; posicao++){
            //le o 1 dado da linha e armazena o dado na sua respectiva variavel
            arquivo = ler.nextInt();
            vetorDados[posicao].x = arquivo;
            //imprime o dado para verificacao
            System.out.printf("x[%d]: %d\n",posicao,vetorDados[posicao].x);
            //le o 2 dado da linha e armazena o dado na sua respectiva variavel
            arquivo = ler.nextInt();
            vetorDados[posicao].y = arquivo;
            //imprime o dado para verificacao
            System.out.printf("y[%d]: %d\n",posicao,vetorDados[posicao].y);
            //le o 3 dado da linha e armazena o dado na sua respectiva variavel
            arquivo = ler.nextInt();
            vetorDados[posicao].cap = arquivo;
            //imprime o dado para verificacao
            System.out.printf("cap[%d]: %d\n",posicao,vetorDados[posicao].cap);
            //le o 4 dado da linha e armazena o dado na sua respectiva variavel
            arquivo = ler.nextInt();
            vetorDados[posicao].dem = arquivo;
            //imprime o dado para verificacao
            System.out.printf("dem[%d]: %d\n",posicao,vetorDados[posicao].dem);
        }
        
        System.out.println();
    }
    
    
    static class Vertice{
        public static int x = 0;
        public static int y = 0;
        public static int cap = 0;
        public static int dem = 0;
        public static int nVertices = 0;
        public static int nMedianas = 0;
        // estado = 0 -> nao visitado
        // estado = 1 -> visitado
        public static boolean estado;
        // mediana = 0 -> nao e mediana
        // mediana = 1 -> e mediana
        public static boolean mediana = false;
        public static int capLivre = 0;

        public static int getCapLivre() {
            return capLivre;
        }

        public static void setCapLivre(int capLivre) {
            Vertice.capLivre = capLivre;
        }
        
        public static int getX() {
            return x;
        }

        public static void setX(int x) {
            Vertice.x = x;
        }

        public static int getY() {
            return y;
        }

        public static void setY(int y) {
            Vertice.y = y;
        }

        public static int getCap() {
            return cap;
        }

        public static void setCap(int cap) {
            Vertice.cap = cap;
        }

        public static int getDem() {
            return dem;
        }

        public static void setDem(int dem) {
            Vertice.dem = dem;
        }

        public static int getnVertices() {
            return nVertices;
        }

        public static void setnVertices(int nVertices) {
            Vertice.nVertices = nVertices;
        }

        public static int getnMedianas() {
            return nMedianas;
        }

        public static void setnMedianas(int nMedianas) {
            Vertice.nMedianas = nMedianas;
        }

        public static boolean isEstado() {
            return estado;
        }

        public static void setEstado(boolean estado) {
            Vertice.estado = estado;
        }

        public static boolean isMediana() {
            return mediana;
        }

        public static void setMediana(boolean mediana) {
            Vertice.mediana = mediana;
        }
        
    }
    
    public static void populacaoInicial(Vertice[] vetorDados,Mediana[] vetorMediana){
        
        //instância um objeto da classe Random usando o construtor básico
	Random gerador = new Random();
	int numero;
	//imprime sequência de 10 números inteiros aleatórios entre 0 e 25
	for (int i = 0; i < vetorDados[0].nMedianas; i++) {
            //imprime os numeros sorteados para verificacao
            //System.out.println(gerador.nextInt(100));
            numero = gerador.nextInt(100);
            //não pode ser 0 pois no vetor  os elementos comecao na posicao 1
            //na posicao 0 so se encontra o num vertices e num medianas
            //se o numero gerado aleatoriamente for 0 gera outro novamente
            while(numero == 0)
                numero = gerador.nextInt(100);
            //Alterado o atributo mediana para true
            if(!vetorDados[numero].mediana){
                
                vetorDados[numero].mediana = true;
                //Altera sua capacidadeLivre
                vetorDados[numero].capLivre = (vetorDados[numero].cap - vetorDados[numero].dem);
                //imprime o numero sorteado e o campo mediana para verificacao
                System.out.printf("numero: %d -> ",numero);
                System.out.println(vetorDados[numero].mediana);
                //salva no vetor medianas os numeros sortidos

                vetorMediana[i].posicao = numero;
                vetorMediana[i].mediana = vetorDados[numero];
            }
	}
        
        int posicao = 1;
        double distancia;
        int x ;
        int y;
        boolean verifica = true;
        int bestMediana = 0;
        double bestDistancia = 0; //ARRUMAR POIS A MELHOR DISTANCIA N PODE SER 0
        
        //gera a distancia do vertice para a mediana
        while(posicao < 101){
            //verifica se o vertice e mediana
            if(vetorDados[posicao].mediana)
                posicao ++;
            //se o vertice não for mediana,calcula a distancia para todas as medianas
            else{
                for(int j = 0; j < vetorDados[0].nMedianas;j++){
                    
                    //Verificar se a mediana tem capacidade
                    if(vetorDados[vetorMediana[j].posicao].capLivre >= vetorDados[posicao].dem ){
                        
                        //mediana - vertice
                        x = Math.abs(vetorDados[vetorMediana[j].posicao].x - vetorDados[posicao].x);
                        y = Math.abs(vetorDados[vetorMediana[j].posicao].y - vetorDados[posicao].y);
                        //distancia = raiz(pow(x2 - x2) + pow(y2 - y1) );
                        distancia = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                        
                        //Verifica se a bestDistancia já recebeu algum valor
                        if(verifica){
                            //Atribui valor inicial para a bestDistancia
                            bestDistancia = distancia;
                            verifica = false;
                        }
                        //se a distancia calculada for melhor do que a bestDistancia
                        if(distancia < bestDistancia){
                            //atualizo a melhor distancia
                            bestDistancia = distancia;
                            //salvo sua mediana
                            bestMediana = vetorMediana[j].posicao;
                        }
                        //Adicionar o vertice a mediana
                        vetorMediana[j].vertices.add(vetorDados[posicao]);
                        
                    }
                    //Se não tiver capacidade passar para a proxima melhor
                    
                    //colocar o vertice na mediana de menor distancia q possui capacidade
                    //vetorDados[vetorMediana[j].posicao].capLivre = (vetorVertice[vetorMediana[j].posicao].capLivre 
                    //                                        - vetorVertice[posicao].dem); 
                }
                posicao ++;
            }
        }//Levar em consideracao para os proximos testes q não consiga colocar 
         //todos osvertices nas medianas (penalizar conjunto de medianas escolhidas)
    }
    
}