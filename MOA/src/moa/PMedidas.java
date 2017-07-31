/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * @author Tatiane Paz e Pedro Moraes
 */
class PMedidas {

    /**
     * @param args the command line arguments
     */
    
    private static int numeroMedianas = 0;
    private static int numeroVertices = 0;
   
    public static void main(String[] args) {
        abrirArquivo();
    }
    
    private static class Vertice{
        private int x;
        private int y;
        private int cap;
        private int dem;
        private int posicao;
        private int capLivre;  
        private boolean estado;
        private boolean mediana;
        // estado = 0 -> nao visitado
        // estado = 1 -> visitado  
        // mediana = 0 -> nao e mediana
        // mediana = 1 -> e mediana

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getCap() {
            return cap;
        }

        public void setCap(int cap) {
            this.cap = cap;
        }

        public int getDem() {
            return dem;
        }

        public void setDem(int dem) {
            this.dem = dem;
        }

        public int getCapLivre() {
            return capLivre;
        }

        public void setCapLivre(int capLivre) {
            this.capLivre = capLivre;
        }

        public boolean isEstado() {
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }

        public boolean isMediana() {
            return mediana;
        }

        public void setMediana(boolean mediana) {
            this.mediana = mediana;
        }

        public int getPosicao() {
            return posicao;
        }

        public void setPosicao(int posicao) {
            this.posicao = posicao;
        }
    }
    
    private static class Mediana{
        private int posicao;
        private Vertice mediana;
        private int posicaoMediana;
        private ArrayList<Vertice> vertices;

        public int getPosicao() {
            return posicao;
        }
        public void setPosicao(int posicao) {
            this.posicao = posicao;
        }
        public Vertice getMediana() {
            return mediana;
        }
        public void setMediana(Vertice mediana) {
            this.mediana = mediana;
        }
        public ArrayList<Vertice> getVertices() {
            return vertices;
        }
        public void setVertices(ArrayList<Vertice> vertices) {
            this.vertices = vertices;
        }   

        public int getPosicaoMediana() {
            return posicaoMediana;
        }

        public void setPosicaoMediana(int posicaoMediana) {
            this.posicaoMediana = posicaoMediana;
        }
        
    }
    
    private static void abrirArquivo(){
        
        Scanner ler = new Scanner(System.in); 
        int contador = 0;
        
        System.out.printf("Digite o arquivo: ");
        
        int arquivo = ler.nextInt(); 
        numeroVertices = arquivo;      
        arquivo = ler.nextInt();
        numeroMedianas = arquivo;
        
        Vertice vertice;
        ArrayList<Vertice> vertices = new ArrayList<>();
        
        
        System.out.println("Numero de vertices: " + numeroVertices);
        System.out.println("Numero de Medianas: " + numeroMedianas);
        
        while(contador < numeroVertices){
            
            vertice = new Vertice();
            arquivo = ler.nextInt();
            vertice.setX(arquivo);
            arquivo = ler.nextInt();
            vertice.setY(arquivo);
            arquivo = ler.nextInt();
            vertice.setCap(arquivo);
            arquivo = ler.nextInt();
            vertice.setDem(arquivo);
            vertice.setPosicao(contador);
            vertices.add(contador, vertice);
            
            System.out.println("Pos: " + contador +
                               "\tX: " + vertice.getX() +
                               "\tY: " + vertice.getY() +
                               "\tCap: " + vertice.getCap() +
                               "\tDem: " + vertice.getDem());

            contador++;
            if(contador == numeroVertices)
                criaMedianas(vertices);     
        }      
    }
    
    private static void criaMedianas(ArrayList<Vertice> vertices){
        //instância um objeto da classe Random usando o construtor básico
	Random gerador = new Random();
        Mediana mediana;
        ArrayList<Mediana> medianas;
        medianas = new ArrayList<>();
        int contador = 0;
	int numero;
        
	//imprime sequência de 10 números inteiros aleatórios entre 0 e 25
        while(contador < numeroMedianas) {
            //imprime os numeros sorteados para verificacao
            //System.out.println(gerador.nextInt(100));
            numero = gerador.nextInt(99);
            //System.out.println(numero);
            //Alterado o atributo mediana para true
            //System.out.println(vertices.get(numero).isMediana());
            if(!vertices.get(numero).isMediana()){
                
                mediana = new Mediana();
                vertices.get(numero).setMediana(true);
                //Altera sua capacidadeLivre
                vertices.get(numero).setCapLivre(vertices.get(numero).getCap() - 
                                                 vertices.get(numero).getDem());
                //imprime o numero sorteado e o campo mediana para verificacao
                System.out.printf("numero: " + numero + " --> ");
                System.out.println(vertices.get(numero).isMediana() + 
                                  "\tCap Livre: " + vertices.get(numero).getCapLivre());
                
                mediana.setPosicao(numero);
                mediana.setMediana(vertices.get(numero));
                mediana.setPosicaoMediana(contador);
                medianas.add(contador, mediana);
                //System.out.println(medianas.get(contador).getPosicao());
                contador++;
            }
	}    
        populacaoInicial(vertices, medianas);
    }
    
    private static void populacaoInicial(ArrayList<Vertice> vertices, ArrayList<Mediana> medianas){
        
        int x;
        int y;
        int posicao = 0;
        double distancia;
        double bestDistancia;
        boolean verifica = true;
        
        //gera a distancia do vertice para a mediana
        while(posicao < numeroVertices){
            //verifica se o vertice e mediana
            if(vertices.get(posicao).isMediana()) posicao++;
            //se o vertice não for mediana,calcula a distancia para todas as medianas
            else{
                for(int m = 0; m < numeroMedianas; m++){
                    
                    //Verificar se a mediana tem capacidade
                    if(medianas.get(m).getMediana().getCapLivre() >= vertices.get(posicao).getDem()){      
                        x = medianas.get(m).getMediana().getX() - vertices.get(posicao).getX();
                        y = medianas.get(m).getMediana().getY() - vertices.get(posicao).getY();
                        distancia = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                        
                        //Verifica se a bestDistancia já recebeu algum valor
                        if(verifica){
                            //Atribui valor inicial para a bestDistancia
                            bestDistancia = distancia;
                            verifica = false;
                        }
                        //se a distancia calculada for melhor do que a bestDistancia
                        //if(distancia < bestDistancia){
                            //atualizo a melhor distancia
                        //    bestDistancia = distancia;
                            //salvo posicao da sua mediana
                        //    bestMediana = m;
                        //}               
                    }
                }
                posicao ++;
            }
        }
    }
    
}
