import java.util.ArrayList;
import java.util.List;

public class FarmGameController {
    ArrayList<String> options;

    List<Player> players;
    Shop shop;
    UI ui;

    boolean isGameOn;
    int week;
    int year;

    boolean isWeekOver;
    Contex ctx;
    Animal animal = new Animal("krowa", 10, 30,false, 2, 2, 2, "big");

    public FarmGameController() throws NoSuchMethodException, SecurityException{
        this.options = new ArrayList<>();
        this.shop = new Shop();

        this.ui = new UI();
        this.players = new ArrayList<Player>();

        this.players.add(new Player(2000));
        this.ctx = new Contex(this.players.get(0));

        this.year = 2020;
        this.week = 1;

        this.isWeekOver = false;

        for(int i = 0; i < 3; i++){
            this.shop.addFarm(FarmGenerator.generateFarm());
        }
        initializeActions();
    }

    private void initializeActions() throws NoSuchMethodException, SecurityException{
        System.out.println("Initializing game..\n");

        options.add("Purchase farm");
        options.add("Show my cash");
        options.add("Sell land");
        options.add("Buy land");
        options.add("Buy building");
        options.add("Sell building");
        options.add("Show my items");
        options.add("Next week");
    }
    public void ShowMyCash(){
        System.out.println(String.format("[ ] You have: %s money", this.players.get(0).cash));
    }
    public void NextWeek(){
        this.isWeekOver = true;
    }
    public void showFarmsToPurchase(){
        ArrayList<String> options = new ArrayList<>();
        shop.farms.forEach( farm -> {
            options.add(String.format("Farm: size[%d], price[%d]", farm.lands.size(), farm.price));
        });
        ui.printMenu(options);
        int idx = ui.getInputAndValidate("[ ] Choose option: ", shop.farms.size());
        Farm farm = shop.farms.get(idx);
        if (shop.purchase(farm, players.get(0))) players.get(0).farms.add(farm);

    }

    public void sellLand(){
        Player player = players.get(0);

        System.out.println("[ ] Your farms:");
        for (int i = 0; i < player.farms.size(); i++){
            System.out.println(String.format("[%d] Farm[%d]", i, player.farms.get(i).lands.size()));
        }
        int idx = ui.getInputAndValidate( "[ ] Choose farm: ", player.farms.size());

        Farm farm = player.farms.get(idx);
        System.out.println(String.format("[ ] You have [%d] lands", farm.lands.size()));

        idx = ui.getInputAndValidate( "[ ] How many lands do you want to sell? ", farm.lands.size());
        shop.sellNItems(farm.lands.get(0), player, idx);
        farm.lands = farm.lands.subList(0, farm.lands.size() - idx);
    }
    public void buyLand(){
        Player player = players.get(0);

        System.out.println("[ ] Your farms:");
        for (int i = 0; i < player.farms.size(); i++){
            System.out.println(String.format("[%d] Farm[%d]", i, player.farms.get(i).lands.size()));
        }
        int idx = ui.getInputAndValidate( "[ ] Choose farm: ", player.farms.size());

        Farm farm = player.farms.get(idx);
        System.out.println(String.format("[ ] You have [%d] lands", farm.lands.size()));

        idx = ui.getInputAndValidate( "[ ] How many lands do you want to buy? ", farm.lands.size());
        shop.buyNItems(farm.lands.get(0), player, idx);
        for (int i = 0; i < idx; i++) {
            farm.lands.add(new Land(1));
        }


        for (int i = 0; i < player.lands.size(); i++) {
            if (farm.lands.size() >= 20) {
                System.out.println("Congratulations! You have won the game by owning 20 or more lands.");
                isGameOn = false;
            } else {
                System.out.println("You don't have 20 or more lands yet.");
                isGameOn = true;
            }
        }


    }

    public void sellBuilding(){
        Player player = players.get(0);

        System.out.println("[ ] Your buildings:");
        for (int i = 0; i < player.buildings.size(); i++){
            System.out.println(String.format("[%d] Building[%d]", i, player.buildings.get(i).buildings.size()));
        }
        int idx = ui.getInputAndValidate( "[ ] Choose building: ", player.buildings.size());

        Farm farm = player.buildings.get(idx);
        System.out.println(String.format("[ ] You have [%d] buildings", farm.buildings.size()));

        idx = ui.getInputAndValidate( "[ ] How many buildings do you want to sell? ", farm.buildings.size());
        shop.sellNItems(farm.buildings.get(0), player, idx);
        farm.buildings = farm.buildings.subList(0, farm.buildings.size() - idx);
    }
    public void buyBuilding(){

    }

    public void startGame(){
        this.isGameOn = true;
            while (isGameOn) {
                System.out.println("\nCurrent year and week: " + this.year + " " + this.week);
                ui.printMenu(options);

                int idx = ui.getInputAndValidate("[ ] Choose option: ", options.size());

                switch (options.get(idx)) {
                    case "Purchase farm":
                        showFarmsToPurchase();
                        break;
                    case "Show my cash":
                        ShowMyCash();
                        break;
                    case "Sell land":
                        sellLand();
                        break;
                    case "Buy land":
                        buyLand();
                        break;
                    case "Buy building":
                        buyBuilding();
                        break;
                    case "Sell building":
                        sellBuilding();
                        break;
                    case "Show my items":
                        showPlayerItems();
                        break;
                    case "Next week":
                        NextWeek();
                        break;
                }
                if (this.isWeekOver) {
                    if (this.week == 52) {
                        this.year++;
                        this.week = 0;
                    }
                    this.week++;
                    this.isWeekOver = false;
                }
        }
    }

    public void showPlayerItems(){
        List<Farm> farms = this.players.get(0).farms;

        System.out.println("[ ] Your Items:");

        System.out.println();
        System.out.println("[ ] Farms:");
        for(int i = 0; i < farms.size(); i++){
            Farm farm = farms.get(i);
            System.out.println(String.format("[%d] %s[%d]", i, farm.name, farm.lands.size()));
        }
    }
}
