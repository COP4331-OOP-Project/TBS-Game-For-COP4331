package controls.structure;

/**
 * Created by gavin on 2/2/17.
 */
public enum StructureEnum {
    BASE;

    public StructureEnum getNext() {
        return this.ordinal() < StructureEnum.values().length - 1
                ? StructureEnum.values()[this.ordinal() + 1]
                : StructureEnum.values()[0];
    }

    public StructureEnum getPrevious() {
        return this.ordinal() > 0
                ? StructureEnum.values()[this.ordinal() - 1]
                : StructureEnum.values()[StructureEnum.values().length-1];
    }
}