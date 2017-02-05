package controls.TypeInstance;

import game.entities.ICommandable;

import java.util.ArrayList;

/**
 * Created by gavin on 2/4/17.
 */
class ListWithIndex {
    private ArrayList<? extends ICommandable> list;
    private Integer index;

    ListWithIndex(ArrayList<? extends ICommandable> list, Integer index) {
        this.list = list;
        this.index = index;
    }

    ArrayList<? extends ICommandable> getList() {
        return list;
    }

    Integer getIndex() {
        return index;
    }
}
