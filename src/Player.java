import java.util.ArrayList;
import java.util.List;

public class Player {
    public int cash;
    public List<Land> lands;
    public List<Farm> farms;

    public Player(int cash){
        this.cash = cash;
        this.lands = new ArrayList<>();
        this.farms = new ArrayList<>();
    }


}
    