import java.io.*;

public class Main {
    public static void main(String[] args) {
        Tile t = new Tile(1, 2);
        Structure base = new Structure(t);

        Melee m = new Melee(base);
        m.printUnit();
    }
}


