import java.util.ArrayList;
import java.util.List;

public class Farm extends ShopItem {
    int dimensions;
    List<Building> buildings;
    List<Land> lands;
    List<Animal> animals;
    List<Plant> plants;

    public Farm(String name, int price, int landSize){
        super(name, price);
        this.lands = new ArrayList<Land>();

        for( int i = 0; i < landSize; i++){
            this.lands.add(new Land(10));
        }

        this.buildings = new ArrayList<Building>();
        this.animals = new ArrayList<Animal>();
        this.plants = new ArrayList<Plant>();
    }
}
