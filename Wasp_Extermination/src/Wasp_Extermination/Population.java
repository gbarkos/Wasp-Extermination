package Wasp_Extermination;

public class Population {
    
    private Individual[] individuals;
    private Individual fittest;
    private Individual secondFittest;
    private Individual thirdFittest;

    public Population() {
        fittest= new Individual();
        secondFittest= new Individual();
    }

    public Individual[] getIndividuals() {
        return individuals;
    }
    
    public Individual getIndividuals(int i) {
        return individuals[i];
    }

    public void setIndividuals(int i,Individual individual) {
        this.individuals[i] = individual;  
    }

    public Individual getFittest() {
        return fittest;
    }

    public Individual getSecondFittest() {
        return secondFittest;
    }
    
    //Initialize population
    public void initializePopulation(int size) {
        individuals=new Individual[size];
        for (int i = 0; i < size; i++) {
            individuals[i] = new Individual();
        }
    }

    //Get the fittest individual
    public Individual findFittest() {
        double maxFit = -1.0;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex];
        return individuals[maxFitIndex];
    }

    //Get the second most fittest individual
    public Individual findSecondFittest() {
        double maxFit2 = -1.0;
        int maxFitIndex2 = 0;
        double same=-1.0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit2 <= individuals[i].getFitness() && this.fittest.getFitness() >= individuals[i].getFitness() ) {
                if(this.fittest != individuals[i]){
                    maxFit2 = individuals[i].getFitness();
                    maxFitIndex2 = i;
                }
            }
        }
        secondFittest = individuals[maxFitIndex2];
        return individuals[maxFitIndex2];
    }

    //Get index of least fittest individual
    public int findLeastFittestIndex() {
        double minFitVal = 10000.0;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        thirdFittest=individuals[minFitIndex];
        return minFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness(double[][] attic) {
        GeneticAlgorithm.fillAttic();
        for (int i = 0; i < 3; i++) {  
            GeneticAlgorithm.fillAttic();
            this.getIndividuals(i).calcFitness(attic);
        }
        findFittest();
        findSecondFittest();
        GeneticAlgorithm.fillAttic();
    }
}
