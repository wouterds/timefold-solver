package org.optaplanner.examples.machinereassignment.domain;

import java.util.List;

import org.optaplanner.examples.common.domain.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MrService")
public class MrService extends AbstractPersistable {

    private List<MrService> toDependencyServiceList;
    private List<MrService> fromDependencyServiceList;

    private int locationSpread;

    public MrService() {
    }

    public MrService(long id) {
        super(id);
    }

    public List<MrService> getToDependencyServiceList() {
        return toDependencyServiceList;
    }

    public void setToDependencyServiceList(List<MrService> toDependencyServiceList) {
        this.toDependencyServiceList = toDependencyServiceList;
    }

    public List<MrService> getFromDependencyServiceList() {
        return fromDependencyServiceList;
    }

    public void setFromDependencyServiceList(List<MrService> fromDependencyServiceList) {
        this.fromDependencyServiceList = fromDependencyServiceList;
    }

    public int getLocationSpread() {
        return locationSpread;
    }

    public void setLocationSpread(int locationSpread) {
        this.locationSpread = locationSpread;
    }

}
