package controls.TypeInstance;

import java.util.ArrayList;

/**
 * Created by gavin on 2/4/17.
 */
class ListWithIndex {
    private ArrayList<?> list;
    private Integer index;

    ListWithIndex(ArrayList<?> list, Integer index) {
        this.list = list;
        this.index = index;
    }

    ArrayList<?> getList() {
        return list;
    }

    Integer getIndex() {
        return index;
    }
}
