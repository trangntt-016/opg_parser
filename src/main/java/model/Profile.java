package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Profile {
    @SerializedName("profile")
    @Expose
    private String profile;

    @SerializedName("valueAccessSpecifics")
    @Expose
    private List<ValueAccessSpecific>valueAccessSpecificList;

}
