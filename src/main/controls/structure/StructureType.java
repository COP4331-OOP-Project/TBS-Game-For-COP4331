package controls.structure;

import controls.Type;

/**
 * Created by gavin on 2/1/17.
 */
public class StructureType implements Type {


    private StructureEnum currentStructureType;

    public StructureType() {
        this.currentStructureType = StructureEnum.BASE;
    }

    public StructureEnum getType() {
        return this.currentStructureType;
    }

    public StructureEnum cycleForward() {
        this.currentStructureType = currentStructureType.getNext();
        return this.currentStructureType;
    }

    public StructureEnum cycleBackward() {
        this.currentStructureType = currentStructureType.getPrevious();
        return this.currentStructureType;
    }
}
