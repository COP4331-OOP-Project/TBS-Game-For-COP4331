package controls.TypeInstance;

/**
 * Created by gavin on 2/4/17.
 */
class ItemWithIndex {
    private Object item;
    private Integer index;

    ItemWithIndex(Object item, Integer index) {
        this.item = item;
        this.index = index;
    }

    Object getItem() {
        return item;
    }

    Integer getIndex() {
        return index;
    }
}
