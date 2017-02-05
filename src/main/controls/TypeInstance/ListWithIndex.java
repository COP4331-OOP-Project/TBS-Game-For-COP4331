package controls.TypeInstance;

import game.entities.TileOccupant;

import java.util.ArrayList;

/**
 * Created by gavin on 2/4/17.
 */
class ListWithIndex {
    private ArrayList<? extends TileOccupant> list;
    private Integer index;

    ListWithIndex(ArrayList<? extends TileOccupant> list, Integer index) {
        this.list = list;
        this.index = index;
    }

    ArrayList<? extends TileOccupant> getList() {
        return list;
    }

    Integer getIndex() {
        return index;
    }
}
