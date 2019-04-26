package Wasp_Extermination;
import java.util.Random;

public class Demo {
    
    public static void main(String args[]){
        
        Random rn = new Random();

        GeneticAlgorithm ga = new GeneticAlgorithm();

        //Initialize population
        ga.getPopulation().initializePopulation(3);
             
        for(int i=0;i<3;i++){
            GeneticAlgorithm.fillAttic();
            ga.getPopulation().getIndividuals(i).calcFitness(ga.getAttic());
        }
               
        //Calculate fitness of each individual
        GeneticAlgorithm.fillAttic();
        ga.getPopulation().calculateFitness(ga.getAttic());
            
        System.out.println("Generation: " + ga.getGenerationCount() +"\n"+ ga.toString());
            
        //Run for 1000 generations
        while (ga.getGenerationCount()<10000) {
            
            ga.setGenerationCount(ga.getGenerationCount()+1);
            
            //Do selection
            ga.selection();
            
            //Do crossover
            ga.crossover();

            //Do mutation under a random probability
            if (Math.abs(rn.nextInt(100))<80) {
                ga.mutation();
            }

            //Add fittest offspring to population
            ga.addFittestOffspring();

            //Calculate fitness of each individual
            GeneticAlgorithm.fillAttic();
            ga.getPopulation().calculateFitness(ga.getAttic());
            
            System.out.println("Generation: " + ga.getGenerationCount() +"\n"+ ga.toString());  
        }
        
        System.out.println("Maximum efficiency of "+ga.getMax()+" kills achieved at generation "+ga.getMaxGeneration());
    }
}
