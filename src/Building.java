import java.util.ArrayList;
import java.util.List;

public class Building extends ShopItem{

    String type;
    int landsSize;
    int aniamalsCapacity;

    List<Animal> animals;

    public Building(String name, int price, String type) {
        super(String.format("%s [%s]", name, type), price);
        this.type = type;

        if (type == "BulidingForBigAnimals") {
            this.aniamalsCapacity = 5;
            this.landsSize = 2;
        }
        if (type == "BulidingForSmallAnimals") {
            this.aniamalsCapacity = 5;
            this.landsSize = 1;
        }
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        if (animals.size() < this.aniamalsCapacity) animals.add(animal);
        else System.out.println(String.format("[ ] You can't have more animals for this building. Max capacity is [%s]", this.aniamalsCapacity));
    }

}
