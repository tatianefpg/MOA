/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author pedro
 */
class Main {

    /**
     * @param args the command line arguments
     */
    
    private static  TreeSet<Registro> conjAberto = new TreeSet<Registro>();
    private static  HashMap<String, Registro> tabelaHash = new HashMap<String, Registro>();
    private static final int[][] SOLUCAO = new int[][]{{ 1, 5, 9, 13 },
                                                        { 2, 6, 10, 14},
                                                        { 3, 7, 11, 15},
                                                        { 4, 8, 12, 0}};
    
    //private static final int[][] TESTE1 = new int[][]{{2, 1, 5, 9},{3, 6, 10, 13},{4, 7, 11, 14},{0, 8, 12, 15}};
    private static final int[][] TESTE1 = new int[][]{{5, 13, 6, 10},{1, 7, 2, 9}, {4, 3, 15, 14}, {8, 0, 11, 12}};
    private static class Registro implements Comparable<Registro>{
        
        private int[][] matriz = new int[4][4];
        private int f = 0;
        private int g = 0;
        private int h = 0;
        private String chave;
        
        public int[][] getMatriz(){
            return matriz;
        }
        public int getF(){
            return f;
        }
        public int getG(){
            return g;
        }
        public int getH(){
            return h;
        }
        public String getChave(){
            return chave;
        }
        public void setF(int f){
            this.f = f;
        }
        public void setG(int g){
            this.g = g;
        }
        public void setH(int h){
            this.h = h;
        }
        
        public boolean eSolucao(){
            return this.getChave().equals("|1|5|9|13|2|6|10|14|3|7|11|15|4|8|12|0");
        }
        
        void setChave() {
	    String hash = "";
	    for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
                    hash += "|"; 
		    hash += this.matriz[i][j];
		}
	    }
	    this.chave = hash;
	}
        
        @Override
        public int compareTo(Registro reg){
            if(this.chave.equals(reg.getChave())) return 0;
            if(f == reg.getF()) return -1;
            return f - reg.getF();
        } 
        
        public Registro(){
        }
        public Registro(Registro reg){
            this.chave = reg.getChave();
            this.g = reg.getG();
            this.matriz = reg.getMatriz();
        }
    }
    
    private static long aEstrela(Registro registro){
        ArrayList<Registro> sucessores = new ArrayList<>();
        Registro reg = null;
        
        conjAberto.add(registro);
        
        while(!conjAberto.isEmpty()){
            reg = conjAberto.first();
            conjAberto.remove(reg);
            
            if(reg.eSolucao()){
                //System.out.println("sol:");
                return reg.getG();
            }
            
            tabelaHash.put(reg.getChave(), reg); 
            sucessores = gerarSucessores(reg);
            
            while(!sucessores.isEmpty()){
                Registro sucessor = sucessores.get(0);
                
                
                Registro aberto = conjAberto.lower(sucessor);
                Registro fechado = tabelaHash.get(sucessor.getChave());
                
                if(fechado != null){
                    if(sucessor.getG() < fechado.getG()){
                        tabelaHash.remove(fechado);
                        sucessor.setH(heuristicaUm(sucessor));
                        sucessor.setF(sucessor.getG() + sucessor.getH());
                        conjAberto.add(sucessor);
                    }    
                }else if(aberto != null){
                    if(sucessor.getG() < aberto.getG()){
                        conjAberto.remove(aberto);
                        sucessor.setH(heuristicaUm(sucessor));
                        sucessor.setF(sucessor.getG() + sucessor.getH());
                        conjAberto.add(sucessor);
                    }
                }else{
                    sucessor.setH(heuristicaUm(sucessor));
                    sucessor.setF(sucessor.getG() + sucessor.getH());
                    conjAberto.add(sucessor);
                }     
                sucessores.remove(sucessor);
            }
        }
        return 0;
    }
    
    private static void mostrarMatriz(Registro reg){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(reg.getMatriz()[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
   

    private static Registro criaSucessor(Registro pai,int i,int j, int ii, int jj){
        Registro filho = new Registro();
        filho.setG(pai.getG() + 1);
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
               if(x==i && y==j) filho.getMatriz()[x][y] = pai.getMatriz()[ii][jj];
               else filho.getMatriz()[x][y] = pai.getMatriz()[x][y];
            }
        }
        filho.getMatriz()[ii][jj] = 0;
        filho.setChave();
        return filho;
    }
 
    private static ArrayList<Registro> gerarSucessores(Registro registro){
        ArrayList<Registro> sucessores = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(registro.getMatriz()[i][j] == 0){
                    if(i-1 >= 0){
                        Registro cima = new Registro(criaSucessor(registro, i, j, i-1, j));
                        sucessores.add(cima);
                    }
                    if(i+1 <= 3){
                        Registro baixo = new Registro(criaSucessor(registro, i, j, i+1, j));
                        sucessores.add(baixo);
                    }
                    if(j-1 >= 0){
                        Registro esq = new Registro(criaSucessor(registro, i, j, i, j-1));
                        sucessores.add(esq);
                    }
                    if(j+1 <= 3){
                        Registro dir = new Registro(criaSucessor(registro, i, j, i, j+1));
                        sucessores.add(dir);
                    }
                }
            }
        }
        return sucessores;
    }
    
    private static int heuristicaUm(Registro registro){
        int errados = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(registro.getMatriz()[i][j] != SOLUCAO[i][j]) errados++;
            }
        }
        return errados;
    }
    
    private static int heuristicaTres(Registro registro){
        int passos = 0;
        int matriz[][] = new int[16][2];
        
            matriz[0][0] = 3;
            matriz[0][1] = 3;
            matriz[1][0] = 0;
            matriz[1][1] = 0;
            matriz[2][0] = 1;
            matriz[2][1] = 0; 
            matriz[3][0] = 2;
            matriz[3][1] = 0;
            matriz[4][0] = 3;
            matriz[4][1] = 0; 
            matriz[5][0] = 0;
            matriz[5][1] = 1;
            matriz[6][0] = 1;
            matriz[6][1] = 1;  
            matriz[7][0] = 2;
            matriz[7][1] = 1;  
            matriz[8][0] = 3;
            matriz[8][1] = 1; 
            matriz[9][0] = 0;
            matriz[9][1] = 2; 
            matriz[10][0] = 1;
            matriz[10][1] = 2; 
            matriz[11][0] = 2;
            matriz[11][1] = 2;   
            matriz[12][0] = 3;
            matriz[12][1] = 2;  
            matriz[13][0] = 0;
            matriz[13][1] = 3;
            matriz[14][0] = 1;
            matriz[14][1] = 3;  
            matriz[15][0] = 2;
            matriz[15][1] = 3;
            
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    int valor = registro.getMatriz()[i][j];
                    int l = matriz[valor][0];
                    int c = matriz[valor][1];
                    int distancia = Math.abs(l - i) + Math.abs(c - j);
                    passos += distancia;
                    
                }
            }
            return passos;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Registro registro = new Registro();

        long start = System.currentTimeMillis();
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int valor = scan.nextInt();
                registro.getMatriz()[i][j] = valor;
            }
        }
        registro.setG(0);
        registro.setH(heuristicaUm(registro));
        registro.setF(registro.getG() + registro.getH());
        registro.setChave();
        
        System.out.println("Passos: " + aEstrela(registro));
        System.out.println("Tempo em ms: " + (System.currentTimeMillis() - start));
        System.out.println("Memoria Usada: "
		+ new DecimalFormat("#.##").format(
			((double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576))
		+ "Mb");
    
    }
}
