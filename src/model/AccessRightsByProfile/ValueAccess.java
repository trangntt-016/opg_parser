package model.AccessRightsByProfile;


import java.util.List;
import java.util.Optional;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "valueAccessDefault", "valuesAccessSpecifics"})
public class ValueAccess {

    private String valueAccessDefault;


    private List<ValueAccessSpecific> valuesAccessSpecifics;

	public ValueAccess() {
		super();
	}

	@XmlElement(name="valueAccessDefault")
	public String getValueAccessDefault() {
		return valueAccessDefault;
	}

	public void setValueAccessDefault(String valueAccessDefault) {
		this.valueAccessDefault = valueAccessDefault;
	}

	@XmlElement(name="valuesAccessSpecifics")
	public List<ValueAccessSpecific> getValuesAccessSpecifics() {
		return valuesAccessSpecifics;
	}

	public void setValuesAccessSpecifics(List<ValueAccessSpecific> valuesAccessSpecifics) {
		this.valuesAccessSpecifics = valuesAccessSpecifics;
	}
	
	public void addValueAccessSpecificToList(ValueAccessSpecific valueAccessSpecific) {
		this.valuesAccessSpecifics.add(valueAccessSpecific);
	}
	
	
	public boolean containsNodePath(String nodePath) {
		return this.valuesAccessSpecifics.stream().anyMatch(access->access.getNodePath().equals(nodePath));
	}
	
	public ValueAccessSpecific getValueAccessSpecificFromNodePath(String nodePath) {
		Optional<ValueAccessSpecific>valueAccessSpecific = this.valuesAccessSpecifics.stream().filter(v->v.getNodePath().equals(nodePath)).findFirst();
		if(valueAccessSpecific.isPresent()) {
			return valueAccessSpecific.get();
		}
		return null;
	}
	
	public void removeValueAccessSpecific(ValueAccessSpecific valueAccessSpecific) {
		int idx = 0;

		for(ValueAccessSpecific v : this.valuesAccessSpecifics) {
			if(v.getNodePath().equals(valueAccessSpecific.getNodePath())&& v.getValuesAccessSpecific().equals(valueAccessSpecific.getValuesAccessSpecific())) {
				break;
			}
			idx++;
		}
		
		this.valuesAccessSpecifics.remove(idx-1);
		
	}
}
