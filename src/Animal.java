class Animal {
    String name;
    int purchaseCost;
    int timeToMaturity;
    boolean isMature;
    int acceptedFood;
    int chanceToReproduction;
    int acceptedBuildings;

    public Animal(String name, int purchaseCost, int timeToMaturity, boolean isMature, int acceptedFood, int chanceToReproduction, int acceptedBuildings) {
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.timeToMaturity = timeToMaturity;
        this.isMature = isMature;
        this.acceptedFood = acceptedFood;
        this.chanceToReproduction = chanceToReproduction;
        this.acceptedBuildings = acceptedBuildings;
    }
}

