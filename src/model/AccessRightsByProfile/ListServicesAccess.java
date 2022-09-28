package model.AccessRightsByProfile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "serviceAccess" })
public class ListServicesAccess {
    private List<ServiceAccess> serviceAccess;

	public ListServicesAccess() {}

	@XmlElement(name="serviceAccess")
	public List<ServiceAccess> getServiceAccess() {
		return serviceAccess;
	}

	public void setServiceAccess(List<ServiceAccess> serviceAccess) {
		this.serviceAccess = serviceAccess;
	}
    
}
