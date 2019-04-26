package Wasp_Extermination;

import java.util.Random;

public class GeneticAlgorithm {

    private Population population;
    private int generationCount;
    private static double[][] attic;
    private Individual first;
    private Individual second;
    double max= -1.0;
    int maxGeneration = -1;
    
    public GeneticAlgorithm() {
        population = new Population();
        generationCount = 0;
        attic = new double[12][3];
        fillAttic();
    }
    
    public Population getPopulation() {
        return population;
    }

    public Individual getFittest() {
        return population.getFittest();
    }

    public Individual getSecondFittest() {
        return population.getSecondFittest();
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public double[][] getAttic() {
        return attic;
    }

    public double getMax() {
        return max;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }
    
    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }
    public static void fillAttic(){
        attic[0][0]=100;  attic[0][1]=25;  attic[0][2]=65;
        attic[1][0]=200;  attic[1][1]=23;  attic[1][2]=8;
        attic[2][0]=327;  attic[2][1]=7;   attic[2][2]=13;
        attic[3][0]=440;  attic[3][1]=95;  attic[3][2]=53;
        attic[4][0]=450;  attic[4][1]=3;   attic[4][2]=3;
        attic[5][0]=639;  attic[5][1]=54;  attic[5][2]=56;
        attic[6][0]=650;  attic[6][1]=67;  attic[6][2]=78;
        attic[7][0]=678;  attic[7][1]=32;  attic[7][2]=4;
        attic[8][0]=750;  attic[8][1]=24;  attic[8][2]=76;
        attic[9][0]=801;  attic[9][1]=66;  attic[9][2]=89;
        attic[10][0]=945; attic[10][1]=84; attic[10][2]=4;
        attic[11][0]=967; attic[11][1]=34; attic[11][2]=23;
    }
    
    //Selection
    public void selection() {
        //Select the most fit individual
        population.findFittest();
        //Select the second most fittest individual
        population.findSecondFittest();
    }

    //Crossover
    public void crossover() {
        Random rn = new Random();
        //Select a random crossover point
        int crossOverPoint = Math.abs(rn.nextInt(42));
        //Swap values among parents
        first= new Individual();
        second= new Individual();
        
        for (int i = 0; i < crossOverPoint; i++) {
            first.setGenes(i,population.getSecondFittest().getGenes(i));
            second.setGenes(i,population.getFittest().getGenes(i));
        }
        for (int i = crossOverPoint; i < 42; i++) {
            first.setGenes(i,population.getFittest().getGenes(i));
            second.setGenes(i,population.getSecondFittest().getGenes(i));
        }
       
    }

    //Mutation
    void mutation() {
        Random rn = new Random();
        System.out.println("mutated");
        //Select a random mutation point
        int mutationPoint = Math.abs(rn.nextInt(population.getIndividuals(0).getGeneLength()));
        //Flip values at the mutation point
        if (first.getGenes(mutationPoint) == 0) {
            first.setGenes(mutationPoint,1);
        } else {
            first.setGenes(mutationPoint,0);
        }
        mutationPoint = Math.abs(rn.nextInt(population.getIndividuals(0).getGeneLength()));
        if (second.getGenes(mutationPoint) == 0) {
            second.setGenes(mutationPoint,1);
        } else {
            second.setGenes(mutationPoint,0);
        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (first.getFitness() > second.getFitness()) {
            return first;
        }
        return second;
    }

    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {
        //Update fitness values of offspring
        fillAttic();
        first.calcFitness(getAttic());
        fillAttic();
        second.calcFitness(getAttic());
        fillAttic();
        //Get index of least fit individual
        int leastFittestIndex = population.findLeastFittestIndex();
        //Replace least fittest individual from most fit offspring
        Individual a = getFittestOffspring();
        population.setIndividuals(leastFittestIndex,a);
    }

    @Override
    public String toString() {
        double bestFitness = population.getFittest().getFitness();
        double secondBestFitness = population.getSecondFittest().getFitness();
        Individual leastFittest = population.getIndividuals(population.findLeastFittestIndex());
        double thirdBestFitness = leastFittest.getFitness();
        double sum = bestFitness+secondBestFitness+thirdBestFitness;
        
        if(bestFitness>max){
            max=bestFitness;
            maxGeneration = generationCount;
        }
        if(secondBestFitness>max){
            max=secondBestFitness;
            maxGeneration = generationCount;
        }
        if(thirdBestFitness>max){
            max=thirdBestFitness;
            maxGeneration = generationCount;
        }
            
        String str="The first placement contributed to: "+ bestFitness +" wasp kills. \n";
               str+="First Bomb: "+population.getFittest().getBombs(0)+" kills.\n";
               str+="X: "+ population.getFittest().calcCoordinates(0) + "\nY: "+ population.getFittest().calcCoordinates(1)+"\n";
               str+="Second Bomb: "+population.getFittest().getBombs(1)+" kills.\n";
               str+="X: "+ population.getFittest().calcCoordinates(2) + "\nY: "+ population.getFittest().calcCoordinates(3)+"\n";
               str+="Third Bomb: "+population.getFittest().getBombs(2)+" kills.\n";
               str+="X: "+ population.getFittest().calcCoordinates(4) + "\nY: "+ population.getFittest().calcCoordinates(5)+"\n";
               
               str+="\n";
               
               str+="The second placement contributed to: "+ secondBestFitness +" wasp kills. \n";
               str+="First Bomb: "+population.getSecondFittest().getBombs(0)+" kills.\n";
               str+="X: "+ population.getSecondFittest().calcCoordinates(0) + "\nY: "+ population.getFittest().calcCoordinates(1)+"\n";
               str+="Second Bomb: "+population.getSecondFittest().getBombs(1)+" kills.\n";
               str+="X: "+ population.getFittest().calcCoordinates(2) + "\nY: "+ population.getFittest().calcCoordinates(3)+"\n";
               str+="Third Bomb: "+population.getSecondFittest().getBombs(2)+" kills.\n";
               str+="X: "+ population.getFittest().calcCoordinates(4) + "\nY: "+ population.getFittest().calcCoordinates(5)+"\n";
               
               str+="\n";
               
               str+="The third placement contributed to: "+ thirdBestFitness +" wasp kills. \n";
               str+="First Bomb: "+leastFittest.getBombs(0)+" kills.\n";
               str+="X: "+ leastFittest.calcCoordinates(0) + "\nY: "+ population.getFittest().calcCoordinates(1)+"\n";
               str+="Second Bomb: "+leastFittest.getBombs(1)+" kills.\n";
               str+="X: "+ leastFittest.calcCoordinates(2) + "\nY: "+ population.getFittest().calcCoordinates(3)+"\n";
               str+="Third Bomb: "+leastFittest.getBombs(2)+" kills.\n";
               str+="X: "+ leastFittest.calcCoordinates(4) + "\nY: "+ population.getFittest().calcCoordinates(5)+"\n";
               
               str+="\n";
               str+="-----------------------------------------------------";
               str+="\n";
               
               return str;
    }

}
