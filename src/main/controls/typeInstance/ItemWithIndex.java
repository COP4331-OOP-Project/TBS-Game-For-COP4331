package controls.typeInstance;

import game.entities.ICommandable;

/**
 * Created by gavin on 2/4/17.
 */
class ItemWithIndex {
    private ICommandable item;
    private Integer index;

    ItemWithIndex(ICommandable item, Integer index) {
        this.item = item;
        this.index = index;
    }

    ICommandable getItem() {
        return item;
    }

    Integer getIndex() {
        return index;
    }
}
