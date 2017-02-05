package controls.TypeInstance;

import game.entities.TileOccupant;

/**
 * Created by gavin on 2/4/17.
 */
class ItemWithIndex {
    private TileOccupant item;
    private Integer index;

    ItemWithIndex(TileOccupant item, Integer index) {
        this.item = item;
        this.index = index;
    }

    TileOccupant getItem() {
        return item;
    }

    Integer getIndex() {
        return index;
    }
}
