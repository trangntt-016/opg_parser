package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class uValueAccessSpecific {
    @SerializedName("nodePath")
    @Expose
    private String nodePath;

    @SerializedName("valuesAccessSpecific")
    @Expose
    private String valuesAccessSpecific;

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getValuesAccessSpecific() {
        return valuesAccessSpecific;
    }

    public void setValuesAccessSpecific(String valuesAccessSpecific) {
        this.valuesAccessSpecific = valuesAccessSpecific;
    }
}
