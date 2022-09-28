package model.AccessRightsByProfile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "nodePath", "valuesAccessSpecific"})
public class ValueAccessSpecific {

    private String nodePath;


    private String valuesAccessSpecific;

	
    public ValueAccessSpecific() {};
    
	public ValueAccessSpecific(String nodePath, String valuesAccessSpecific) {
		this.nodePath = nodePath;
		this.valuesAccessSpecific = valuesAccessSpecific;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodePath == null) ? 0 : nodePath.hashCode());
		result = prime * result + ((valuesAccessSpecific == null) ? 0 : valuesAccessSpecific.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueAccessSpecific other = (ValueAccessSpecific) obj;
		if (nodePath == null) {
			if (other.nodePath != null)
				return false;
		} else if (!nodePath.equals(other.nodePath))
			return false;
		if (valuesAccessSpecific == null) {
			if (other.valuesAccessSpecific != null)
				return false;
		} else if (!valuesAccessSpecific.equals(other.valuesAccessSpecific))
			return false;
		return true;
	}



	@XmlElement(name="nodePath")
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	@XmlElement(name="valuesAccessSpecific")
	public String getValuesAccessSpecific() {
		return valuesAccessSpecific;
	}

	public void setValuesAccessSpecific(String valuesAccessSpecific) {
		this.valuesAccessSpecific = valuesAccessSpecific;
	}
	
	
}
