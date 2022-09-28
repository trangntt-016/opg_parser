package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "createChild", "createDuplicate", "deleteInstance", "activateInstance","createPerspective" })
public class InstanceActions { 
    private String createChild;
    
    private String createDuplicate;
    
    private String deleteInstance;
    
    private String activateInstance;
    
    private String createPerspective;

    public InstanceActions(){}

    @XmlElement(name="createChild")
	public String getCreateChild() {
		return createChild;
	}


	public void setCreateChild(String createChild) {
		this.createChild = createChild;
	}

	@XmlElement(name="createDuplicate")
	public String getCreateDuplicate() {
		return createDuplicate;
	}


	public void setCreateDuplicate(String createDuplicate) {
		this.createDuplicate = createDuplicate;
	}

	@XmlElement(name="deleteInstance")
	public String getDeleteInstance() {
		return deleteInstance;
	}


	public void setDeleteInstance(String deleteInstance) {
		this.deleteInstance = deleteInstance;
	}

	@XmlElement(name="activateInstance")
	public String getActivateInstance() {
		return activateInstance;
	}


	public void setActivateInstance(String activateInstance) {
		this.activateInstance = activateInstance;
	}

	@XmlElement(name="createPerspective")
	public String getCreatePerspective() {
		return createPerspective;
	}


	public void setCreatePerspective(String createPerspective) {
		this.createPerspective = createPerspective;
	};

    
}
