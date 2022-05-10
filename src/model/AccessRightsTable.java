package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


public class AccessRightsTable {
    @XmlElement(name="profile")
    private String profile;

    @XmlElement(name="isRestrictionPolicy")
    private String isRestrictionPolicy;

    @XmlElement(name="instanceActions")
    private InstanceActions instanceActions;

    @XmlElement(name="tableActions")
    private TableActions tableActions;

    @XmlElement(name="valueAccess")
    private ValueAccess valueAccess;

    public AccessRightsTable() {};

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIsRestrictionPolicy() {
        return isRestrictionPolicy;
    }

    public void setIsRestrictionPolicy(String isRestrictionPolicy) {
        this.isRestrictionPolicy = isRestrictionPolicy;
    }

    public InstanceActions getInstanceActions() {
        return instanceActions;
    }

    public void setInstanceActions(InstanceActions instanceActions) {
        this.instanceActions = instanceActions;
    }
}
