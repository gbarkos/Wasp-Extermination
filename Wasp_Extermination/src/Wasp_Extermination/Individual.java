package Wasp_Extermination;

import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

public class Individual{
    
    private double fitness;
    private int[] genes;
    private int geneLength;
    double[] bombs;

    public Individual() {
        fitness = 0;
        geneLength = 42;
        genes = new int[42];
        bombs = new double[3];
        Random rn = new Random();
        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public double getFitness() {
        return fitness;
    }
     
    public int getGeneLength() {
        return geneLength;
    }
    
    public int getGenes(int i){
        return genes[i];
    }
     public void setGenes(int i,int g){
         this.genes[i]=g;
    }
     
    public double getBombs(int i){
        return bombs[i];
    }
    
    public double[] getBombs(){
        return bombs;
    }
    //Calculate fitness
    public void calcFitness(double dummyAttic[][]) {
        
        for(int i=0; i<bombs.length; i++){
            bombs[i]=0;
        }
        
        fitness = 0;
        double k;
        double dmax = 141.42;
        double d;
        double[] coordinates=calcCoordinates();
        double xdiffsqrd;
        double ydiffsqrd;
        int cord=0;
        
        for (int i = 0; i < dummyAttic.length; i++) {
            
            for(int j=0; j<3; j++){
                xdiffsqrd = round(Math.pow(coordinates[cord]-dummyAttic[i][1],2),2);
                ydiffsqrd = round(Math.pow(coordinates[cord+1]-dummyAttic[i][2],2),2);
                d = round(sqrt(xdiffsqrd+ydiffsqrd),2);
                k = round(dummyAttic[i][0]*(dmax/((20*d)+0.00001)),0);
            
                if(k>dummyAttic[i][0]){
                    this.fitness += dummyAttic[i][0];
                    this.bombs[j]+=dummyAttic[i][0];
                    dummyAttic[i][0]=0;
                }else{
                    dummyAttic[i][0]-=k;
                    this.fitness += k;
                    this.bombs[j]+=k;
                }
                cord+=2;
            }
            cord=0;
        }
        
    }
    
    public double[] calcCoordinates(){
        
        double coordinates[] = new double[6];
        String xbinstr="";
        String ybinstr="";
        int xdec;
        int ydec;
        double xbomb;
        double ybomb;
        
        //first 0-13
        int first[] = Arrays.copyOfRange(genes, 0, genes.length/3);
        //second 14-41
        int tempTwoThree[] = Arrays.copyOfRange(genes, (genes.length/3), genes.length);
        //second 14-27
        int second[] = Arrays.copyOfRange(tempTwoThree, 0, (tempTwoThree.length/2));
        //third 28-41
        int third[] = Arrays.copyOfRange(tempTwoThree,(tempTwoThree.length/2), tempTwoThree.length);
        
        
        for(int i=0; i<(first.length/2); i++){
            ybinstr+=first[i];
            xbinstr+=first[i+7];   
        }
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[0]=xbomb;
        coordinates[1]=ybomb;
        
        xbinstr="";
        ybinstr="";        
        for(int i=0;i<(second.length/2);i++){
            ybinstr +=second[i];
            xbinstr +=second[i+7];
        } 
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[2]=xbomb;
        coordinates[3]=ybomb;
        
        xbinstr="";
        ybinstr="";        
        for(int i=0;i<(third.length/2);i++){
            ybinstr +=third[i];
            xbinstr +=third[i+7];
        } 
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[4]=xbomb;
        coordinates[5]=ybomb;
        return coordinates;
    }
    public double calcCoordinates(int j){
        
        double coordinates[] = new double[6];
        String xbinstr="";
        String ybinstr="";
        int xdec;
        int ydec;
        double xbomb;
        double ybomb;
        
        //first 0-13
        int first[] = Arrays.copyOfRange(genes, 0, genes.length/3);
        //second 14-41
        int tempTwoThree[] = Arrays.copyOfRange(genes, (genes.length/3), genes.length);
        //second 14-27
        int second[] = Arrays.copyOfRange(tempTwoThree, 0, (tempTwoThree.length/2));
        //third 28-41
        int third[] = Arrays.copyOfRange(tempTwoThree,(tempTwoThree.length/2), tempTwoThree.length);
        
        
        for(int i=0; i<(first.length/2); i++){
            ybinstr+=first[i];
            xbinstr+=first[i+7];   
        }
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[0]=xbomb;
        coordinates[1]=ybomb;
        
        xbinstr="";
        ybinstr="";        
        for(int i=0;i<(second.length/2);i++){
            ybinstr +=second[i];
            xbinstr +=second[i+7];
        } 
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[2]=xbomb;
        coordinates[3]=ybomb;
        
        xbinstr="";
        ybinstr="";        
        for(int i=0;i<(third.length/2);i++){
            ybinstr +=third[i];
            xbinstr +=third[i+7];
        } 
        xdec = Integer.parseInt(xbinstr, 2);
        ydec = Integer.parseInt(ybinstr, 2);
        xbomb = round((xdec/128.0)*100,2);
        ybomb = round((ydec/128.0)*100,2);
        
        coordinates[4]=xbomb;
        coordinates[5]=ybomb;
        return coordinates[j];
    }
    
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
