package model;

import javax.xml.bind.annotation.*;
import java.util.List;

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
}
