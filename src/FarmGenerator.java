public class FarmGenerator {
    public static Farm generateFarm(){
        int land = Utils.getRandomNumber(1,10);
        int price = land * 10;
        return new Farm("Farm", price, land);
    }
}
