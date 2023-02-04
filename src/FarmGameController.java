import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FarmGameController {
    int price = FarmGenerator.generateFarm().price;
    ArrayList<String> options;

    List<Player> players;
    //<Action> defaultActions;
    //List<Action> farmActions;

    Shop shop;
    //FarmGenerator farmGenerator;
    UI ui;

    boolean isGameOn;
    int week;
    int year;
    int cash;

    boolean isWeekOver;
    Contex ctx;
    Animal animal = new Animal("krowa", 10, 30,false, 2, 2, 2);

    public FarmGameController() throws NoSuchMethodException, SecurityException{
        this.options = new ArrayList<>();
        this.shop = new Shop();
       // this.defaultActions = new ArrayList<Action>();
       // this.farmActions = new ArrayList<Action>();

        this.ui = new UI();
        this.players = new ArrayList<Player>();

        this.players.add(new Player(2000));
        //this.players.get(0).cash = cash;
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
        options.add("Purchase/sell land");
        options.add("Show my items");
        options.add("Next week");
    }
    public void ShowMyCash(){
        System.out.println(String.format("[ ] You have: %s money", this.players.get(0).cash));
    }
    public void Purchasesellland(){
        System.out.println(String.format("[ ] Purchase or sell land", this.players.get(0).cash));
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

    public void purchaseBuilding(){

    }

    public void startGame(){
        this.isGameOn = true;

        while(isGameOn){
            System.out.println("\nCurrent year and week: " + this.year + " " + this.week);
            ui.printMenu(options);

            int idx = ui.getInputAndValidate("[ ] Choose option: ", options.size());

            switch (options.get(idx)){
                case "Purchase farm":
                    showFarmsToPurchase();
                    break;
                case "Show my cash":
                    ShowMyCash();
                    break;
                case "Purchase/sell land":
                    sellLand();
                    break;
                case "Show my items":
                    showPlayerItems();
                    break;
                case "Next week":
                    NextWeek();
                    break;
            }

            if(this.isWeekOver){
                if(this.week == 52) {
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
