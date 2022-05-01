package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueAccessSpecific {
    @SerializedName("nodePath")
    @Expose
    private String nodePath;

    @SerializedName("access")
    @Expose
    private String access;

}
