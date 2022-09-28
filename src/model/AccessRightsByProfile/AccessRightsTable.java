package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "profile", "isRestrictionPolicy", "instanceActions", "tableActions", "valueAccess", "listServicesAccess" })
public class AccessRightsTable {    
    private String profile;

    private String isRestrictionPolicy;

    private InstanceActions instanceActions;
    
    private TableActions tableActions;
    
    private ValueAccess valueAccess;
        
    private ListServicesAccess listServicesAccess;

	public AccessRightsTable() {}

	@XmlElement(name="profile")
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@XmlElement(name="isRestrictionPolicy")
	public String getIsRestrictionPolicy() {
		return isRestrictionPolicy;
	}

	public void setIsRestrictionPolicy(String isRestrictionPolicy) {
		this.isRestrictionPolicy = isRestrictionPolicy;
	}

	@XmlElement(name="instanceActions")
	public InstanceActions getInstanceActions() {
		return instanceActions;
	}

	public void setInstanceActions(InstanceActions instanceActions) {
		this.instanceActions = instanceActions;
	}

	@XmlElement(name="tableActions")
	public TableActions getTableActions() {
		return tableActions;
	}

	public void setTableActions(TableActions tableActions) {
		this.tableActions = tableActions;
	}

	@XmlElement(name="valueAccess")
	public ValueAccess getValueAccess() {
		return valueAccess;
	}

	public void setValueAccess(ValueAccess valueAccess) {
		this.valueAccess = valueAccess;
	}

	@XmlElement(name="listServicesAccess")
	public ListServicesAccess getListServicesAccess() {
		return listServicesAccess;
	}

	public void setListServicesAccess(ListServicesAccess listServicesAccess) {
		this.listServicesAccess = listServicesAccess;
	}

}
