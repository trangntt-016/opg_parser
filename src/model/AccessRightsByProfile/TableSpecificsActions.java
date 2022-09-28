package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "nodePath", "accessRightTableAction"})
public class TableSpecificsActions {
	
    private String nodePath;
	
	
    private AccessRightTableAction accessRightTableAction;

	public TableSpecificsActions() {
		super();
	}
	
	@XmlElement(name="nodePath")
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	@XmlElement(name="accessRightTableAction")
	public AccessRightTableAction getAccessRightTableAction() {
		return accessRightTableAction;
	}

	public void setAccessRightTableAction(AccessRightTableAction accessRightTableAction) {
		this.accessRightTableAction = accessRightTableAction;
	}
	
	
}
