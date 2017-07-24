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
public class PMedidas {

    /**
     * @param args the command line arguments
     */
    
    private static int numeroMedianas = 0;
    private static int numeroVertices = 0;
    
    public static void main(String[] args) {
        abrirArquivo();
    }
    
    private static class Vertice{
        public static int x;
        public static int y;
        public static int cap;
        public static int dem;
        public static int posicao;
        public static int capLivre;  
        public static boolean estado;
        public static boolean mediana;
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
    }
    
    private static void abrirArquivo(){
        
        Scanner ler = new Scanner(System.in); 
        int contador = 0;
        
        System.out.printf("Digite o arquivo: ");
        
        int arquivo = ler.nextInt(); 
        numeroVertices = arquivo;
        
        arquivo = ler.nextInt();
        numeroMedianas = arquivo;
        
        Vertice vertice = new Vertice();
        ArrayList<Vertice> vertices = new ArrayList<>();
        
        
        System.out.println("Numero de vertices: " + numeroVertices);
        System.out.println("Numero de Medianas: " + numeroMedianas);
        
        while(contador < numeroVertices){
   
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
            contador++;
            System.out.println(vertice.getPosicao());
        }
        
        //populacaoInicial(vertices);
    }
    
    /*public static void lerArquivo(Vertice[] vetorDados) {

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
    }*/
      
    
    /*private static void populacaoInicial(Vertice[] vertices){
        
        //instância um objeto da classe Random usando o construtor básico
	Random gerador = new Random();
	int numero;
	//imprime sequência de 10 números inteiros aleatórios entre 0 e 25
	for (int i = 0; i < numeroMedianas; i++) {
            //imprime os numeros sorteados para verificacao
            //System.out.println(gerador.nextInt(100));
            numero = gerador.nextInt(100);
            //Alterado o atributo mediana para true
            if(!vertices[numero].mediana){
                vertices[numero].mediana = true;
                //Altera sua capacidadeLivre
                vertices[numero].capLivre = (vertices[numero].cap - vertices[numero].dem);
                //imprime o numero sorteado e o campo mediana para verificacao
                System.out.printf("numero: %d -> ",numero);
                System.out.println(vertices[numero].mediana);
                //salva no vetor medianas os numeros sortidos
                //medianas[i] = numero;
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
                    //vetorDados[medianas[j]].capLivre = (vetorVertices[medianas[j]].capLivre 
                    //                                        - vetorVertices[posicao].dem); 
                }
                posicao ++;
            }
        }//Levar em consideracao para os proximos testes q não consiga colocar 
         //todos osvertices nas medianas (penalizar conjunto de medianas escolhidas)
    }*/
    
}
