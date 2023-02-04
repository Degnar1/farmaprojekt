import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop {
    HashMap<String, List<ShopItem>> items;

    List<Farm> farms;
    List<Land> lands;
    List<Building> buildingList;

    public Shop(){
        this.items = new HashMap<String, List<ShopItem>>();
        this.farms = new ArrayList<>();
        this.lands = new ArrayList<>();
        initialize();
    }

    private void initialize(){
        this.items.put("Plants", new ArrayList<>());
        this.items.put("Farms", new ArrayList<>());

        this.lands.add(new Land(100));
    }

    public void addFarm(Farm farm){
        this.farms.add(farm);
    }

    public boolean purchase(ShopItem item, Player player){
        if (player.cash < item.price){
            System.out.printf("[ ] You have no enought money. You have only %s", player.cash);
            return false;
        }

        player.cash -= item.price;
        System.out.printf("[ ] You purchased %s", item.name);
        return true;
    }

    public void sell(ShopItem item, Player player){
        player.cash += item.price * 0.9;
    }

    public void sellNItems(ShopItem item, Player player, int numberOfItems){
        player.cash += item.price * numberOfItems * 0.9;
    }

}
