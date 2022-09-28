package model.AccessRightsByProfile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "tableDefaultActions", "tableSpecificsActions" })
public class TableActions {	
    private TableDefaultActions tableDefaultActions;
	
    private List<TableSpecificsActions>tableSpecificsActions;

	public TableActions() {
		super();
	}
	
	@XmlElement(name="tableDefaultActions")
	public TableDefaultActions getTableDefaultActions() {
		return tableDefaultActions;
	}

	public void setTableDefaultActions(TableDefaultActions tableDefaultActions) {
		this.tableDefaultActions = tableDefaultActions;
	}
	
	@XmlElement(name="tableSpecificsActions")
	public List<TableSpecificsActions> getTableSpecificsActions() {
		return tableSpecificsActions;
	}

	public void setTableSpecificsActions(List<TableSpecificsActions> tableSpecificsActions) {
		this.tableSpecificsActions = tableSpecificsActions;
	}
	
	
}
