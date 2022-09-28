package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "createAllowed", "overwriteAllowed", "occultAllowed", "deleteAllowed" })
public class AccessRightTableAction {	
	private String createAllowed;
		
    private String overwriteAllowed;
		
    private String occultAllowed;
		
    private String deleteAllowed;

	public AccessRightTableAction() {
		super();
	}

	@XmlElement(name="createAllowed")
	public String getCreateAllowed() {
		return createAllowed;
	}

	public void setCreateAllowed(String createAllowed) {
		this.createAllowed = createAllowed;
	}

	@XmlElement(name="overwriteAllowed")
	public String getOverwriteAllowed() {
		return overwriteAllowed;
	}

	public void setOverwriteAllowed(String overwriteAllowed) {
		this.overwriteAllowed = overwriteAllowed;
	}

	@XmlElement(name="occultAllowed")
	public String getOccultAllowed() {
		return occultAllowed;
	}

	public void setOccultAllowed(String occultAllowed) {
		this.occultAllowed = occultAllowed;
	}

	@XmlElement(name="deleteAllowed")
	public String getDeleteAllowed() {
		return deleteAllowed;
	}

	public void setDeleteAllowed(String deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}
    
	
}
