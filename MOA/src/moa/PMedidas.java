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
    private static int posMediana = -1;
    private static double bestDistancia = -1;
    private static double distanciaTotal = 0;
    
    public static void main(String[] args) {
        /*ArrayList<Vertice> v = new ArrayList<>();
        numeroMedianas = 1;
        numeroVertices = 3;
        Vertice v1 = new Vertice();
        v1.setX(409154);
        v1.setY(435528);
        v1.setCap(720);
        v1.setDem(50);
        v.add(0, v1);
        Vertice v2 = new Vertice();
        v2.setX(409151);
        v2.setY(435683);
        v2.setCap(720);
        v2.setDem(4);
        v.add(1, v2);
        Vertice v3 = new Vertice();
        v3.setX(409277);
        v3.setY(435420);
        v3.setCap(720);
        v3.setDem(33);
        v.add(2, v3);
        
        ArrayList<Mediana> m = new ArrayList<>();
        Mediana m1 = new Mediana();
        m1.setMediana(v2);
        m1.setPosicao(1);
        m1.setPosicaoMediana(0);
        m1.getMediana().setCapLivre(v2.getCap() - v2.getDem());
        m1.getMediana().setMediana(true);
        m.add(0, m1);
        populacaoInicial(v, m);
        System.out.println(m.get(0).getVertices().get(0).getX());
        System.out.println(m.get(0).getVertices().get(1).getX());
        System.out.println(m.get(0).getTotalDistancia());
        System.out.println(v1.isEstado());
        System.out.println(v2.isMediana());
        System.out.println(v3.isEstado());
        System.out.println(m.get(0).getMediana().getCapLivre());*/
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
        private double totalDistancia = 0;
        private ArrayList<Vertice> vertices = new ArrayList<>();

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
        
        public void adicionaVertice(Vertice vertice){
            this.vertices.add(vertice);
        }

        public double getTotalDistancia() {
            return totalDistancia;
        }

        public void setTotalDistancia(double totalDistancia) {
            this.totalDistancia = totalDistancia;
        }          
    }
    
    private static void abrirArquivo(){
        
        Scanner ler = new Scanner(System.in); 
        int contador = 0;
        
        System.out.printf("Digite o arquivo: ");
        
        int arquivo = ler.nextInt(); //adiciona numero de vertices 
        numeroVertices = arquivo;      
        arquivo = ler.nextInt(); //adiciona numero de medianas
        numeroMedianas = arquivo;
        
        Vertice vertice;
        ArrayList<Vertice> vertices = new ArrayList<>();
        
        
        //System.out.println("Numero de vertices: " + numeroVertices);
        //System.out.println("Numero de Medianas: " + numeroMedianas);
        
        //atribui para cada vertices a pos X e Y, a capacidade e a demanda e adiciona numa lista
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
            
            /*System.out.println("Pos: " + contador +
                               "\tX: " + vertice.getX() +
                               "\tY: " + vertice.getY() +
                               "\tCap: " + vertice.getCap() +
                               "\tDem: " + vertice.getDem());*/

            contador++;
            if(contador == numeroVertices)
                //chama a funcao para criar as medias
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
        
	//transforma vertices aleatorios em medias ate o total de medianas pedidas
        while(contador < numeroMedianas) {
            //Sorteia um vertice dentro da lista de vertices
            numero = gerador.nextInt(numeroVertices - 1);
            //System.out.println(numero);
            //verifica se o vertice sorteado já é mediana
            if(!vertices.get(numero).isMediana()){
                //transforma em mediana
                mediana = new Mediana();
                vertices.get(numero).setMediana(true);
                //Altera sua capacidadeLivre
                vertices.get(numero).setCapLivre(vertices.get(numero).getCap() - 
                                                 vertices.get(numero).getDem());
                //imprime o numero sorteado e o campo mediana para verificacao
                //System.out.printf("numero: " + numero + " --> ");
                //System.out.println(vertices.get(numero).isMediana() + 
                //                  "\tCap Livre: " + vertices.get(numero).getCapLivre());
                
                //atribui a medina a uma lista de medianas
                mediana.setPosicao(numero);
                mediana.setMediana(vertices.get(numero));
                mediana.setPosicaoMediana(contador);
                medianas.add(contador, mediana);
                //System.out.println(medianas.get(contador).getPosicao());
                contador++;
            }
	}    
        //chama a funcao que cria a populacao inicial
        populacaoInicial(vertices, medianas);
    }
        
    private static void populacaoInicial(ArrayList<Vertice> vertices, ArrayList<Mediana> medianas){
        
        int x, y;
        double distancia;
        
        for(int i = 0; i < numeroVertices; i++){
            //verifica cada vertice para ver qual nao é mediana
            if(!vertices.get(i).isMediana()){
                for(int m = 0; m < numeroMedianas; m++){
                    //para cada mediana pega o vertice seleciona e verifica qual fica com a melhor distancia
                    if(medianas.get(m).getMediana().getCapLivre() >= vertices.get(i).getDem()){
                        x = medianas.get(m).getMediana().getX() - vertices.get(i).getX();
                        y = medianas.get(m).getMediana().getY() - vertices.get(i).getY();
                        distancia = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                    
                        if(m == 0){
                            bestDistancia = distancia;
                            posMediana = m;
                        }else if(bestDistancia > distancia){
                            bestDistancia = distancia;
                            posMediana = m;
                        }
                    } 
                }
                //verfica se o vertice realmente cabe na mediana
                if(medianas.get(posMediana).getMediana().getCapLivre() >= vertices.get(i).getDem()){
                    //atribui o vertice com menor distancia para aquela mediana
                    medianas.get(posMediana).adicionaVertice(vertices.get(i));
                    //adiciona ao total da distancia da mediana a distancia daquele vertice
                    medianas.get(posMediana).setTotalDistancia(medianas.get(posMediana).getTotalDistancia()
                                                               + bestDistancia);
                    //decrementa a capacidade livre da mediana
                    medianas.get(posMediana).getMediana().setCapLivre(medianas.get(posMediana).getMediana().getCapLivre()
                                                                      - vertices.get(i).getDem());
                    //marca o vertice como já utilizado
                    vertices.get(i).setEstado(true);
                }
            }
        }
        
        //verifica quais vertices não entraram em mediana
        for(int v = 0; v < numeroVertices; v++){
            if(!vertices.get(v).isMediana())
                if(!vertices.get(v).isEstado()){
                    //System.out.println("Pos: " + v + " Estado: " + vertices.get(v).isEstado());
                    //chama funcao  parav adicionalos
                    adicionaVertice(vertices, medianas, v);
                }
        }
        //printa todas as medianas com seus vertices sua capacidade livre e a distancia total final
        for(int m = 0; m < numeroMedianas; m++){
            System.out.print("Med: " + medianas.get(m).getPosicao());
            for(Vertice v: medianas.get(m).getVertices()){
                //System.out.print("\tPos: " + v.getPosicao());
            }
            System.out.println("\nCap Livre: " + medianas.get(m).getMediana().getCapLivre());
            distanciaTotal = distanciaTotal + medianas.get(m).getTotalDistancia();
        }
        System.out.println("Dist: " + distanciaTotal);
    }
    
    //funcao para verricar se o vertice que sobrou cabe em alguma mediana 
    //pega a primeira q couber o vertice
    private static void adicionaVertice(ArrayList<Vertice> vertices, ArrayList<Mediana> medianas, int pos){
        for(Mediana m: medianas){
            if((m.getMediana().getCapLivre() >= vertices.get(pos).getDem()) && !vertices.get(pos).isEstado()){
                m.adicionaVertice(vertices.get(pos));
                m.setTotalDistancia(m.getTotalDistancia() + 
                        calculaDistancia(m.getMediana().getX(), m.getMediana().getY(), 
                                vertices.get(pos).getX(), vertices.get(pos).getY()));
                m.getMediana().setCapLivre(m.getMediana().getCapLivre() - 
                        vertices.get(pos).getDem());
                vertices.get(pos).setEstado(true);  
            }
        }
        System.out.println("Vertice sobrou: " + vertices.get(pos).isEstado());
    }
    
    //calcula distancia de dois vertices
    private static double calculaDistancia(int x1, int y1, int x2, int y2){
        int x = x1 - x2;
        int y = y1 - y2;
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
