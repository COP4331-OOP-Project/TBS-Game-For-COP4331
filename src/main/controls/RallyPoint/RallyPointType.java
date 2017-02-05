package controls.RallyPoint;

import controls.Type;

/**
 * Created by gavin on 2/4/17.
 */
public class RallyPointType implements Type {
    private RallyPointEnum currentRallyPointType;

    public RallyPointType() {
        this.currentRallyPointType = RallyPointEnum.RALLY_POINT;
    }

    public RallyPointEnum getType() {
        return this.currentRallyPointType;
    }

    public RallyPointEnum cycleForward() {
        return this.currentRallyPointType;
    }

    public RallyPointEnum cycleBackward() {
        return this.currentRallyPointType;
    }
}
