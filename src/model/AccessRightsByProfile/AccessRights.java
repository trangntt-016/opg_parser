package model.AccessRightsByProfile;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@XmlRootElement(name = "accessRights")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessRights {
    @XmlElement(name="accessRightsTable")
    private List<AccessRightsTable> accessRightsTable;

    public void setAccessRightsTable(List<AccessRightsTable>accessRightsTable){
        this.accessRightsTable = accessRightsTable;
    }


    public List<AccessRightsTable>getAccessRightsTable(){
        return this.accessRightsTable;
    }
    
    public String getAccessFromProfileAndNodePath(String profile, String nodePath) {
    	Optional<AccessRightsTable> filteredProfile = this.accessRightsTable.stream().filter(a->a.getProfile().equals(profile)).findFirst();
    	// check if this profile has the nodePath
    	if(filteredProfile.isPresent()) {
    		Optional<ValueAccessSpecific> access = filteredProfile.get().getValueAccess().getValuesAccessSpecifics().stream().filter(v->v.getNodePath().equals(nodePath)).findFirst();
    		if(access.isPresent()) {
    			return access.get().getValuesAccessSpecific();
    		}
    	}	
    	return null;
    }
}
