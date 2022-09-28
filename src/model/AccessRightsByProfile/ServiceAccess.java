package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "serviceName", "servicePermission" })
public class ServiceAccess {
    private String serviceName;
    
    private String servicePermission;
    
	public ServiceAccess() {};
	
	@XmlElement(name="serviceName")
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@XmlElement(name="servicePermission")
	public String getServicePermission() {
		return servicePermission;
	}
	
	public void setServicePermission(String servicePermission) {
		this.servicePermission = servicePermission;
	}
    
    
}
