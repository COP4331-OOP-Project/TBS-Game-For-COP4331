import java.io.*;

public class Main {
    public static void main(String[] args) {
        Tile t = new Tile(1, 2);
        Structure base = new Structure(t);

<<<<<<< f9111a21e12a7294e89984b20c1c1e7e689dc7f5
        Unit m = new Melee(base);
        m.printUnit();

        Unit r = new Ranged(base);
        r.printUnit();

        Unit c = new Colonist(base);
        c.printUnit();

        Unit w = new Worker(base);
        w.printUnit();

        Unit e = new Explorer(base);
        e.printUnit();

        Army a = new Army(m);
        a.addSoldier(r);
        a.addSoldier(c);
        a.addSoldier(w);
        a.addSoldier(e);
        a.printArmy();
=======
        Melee m = new Melee(base);
        m.printUnit();
>>>>>>> Added basic Unit files
    }
}


