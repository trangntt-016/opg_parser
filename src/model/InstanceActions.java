package model;

import javax.xml.bind.annotation.XmlElement;

public class InstanceActions {
    @XmlElement(name="createChild")
    private String createChild;

    @XmlElement(name="createDuplicate")
    private String createDuplicate;

    @XmlElement(name="deleteInstance")
    private String deleteInstance;

    @XmlElement(name="activateInstance")
    private String activateInstance;

    @XmlElement(name="createPerspective")
    private String createPerspective;


    public InstanceActions(){};

    public String getCreateChild() {
        return createChild;
    }

    public void setCreateChild(String createChild) {
        this.createChild = createChild;
    }

    public String getCreateDuplicate() {
        return createDuplicate;
    }

    public void setCreateDuplicate(String createDuplicate) {
        this.createDuplicate = createDuplicate;
    }

    public String getDeleteInstance() {
        return deleteInstance;
    }

    public void setDeleteInstance(String deleteInstance) {
        this.deleteInstance = deleteInstance;
    }

    public String getActivateInstance() {
        return activateInstance;
    }

    public void setActivateInstance(String activateInstance) {
        this.activateInstance = activateInstance;
    }

    public String getCreatePerspective() {
        return createPerspective;
    }

    public void setCreatePerspective(String createPerspective) {
        this.createPerspective = createPerspective;
    }
}
