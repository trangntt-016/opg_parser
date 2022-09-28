package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.AccessRightsByProfile.ValueAccessSpecific;

import java.util.List;

@Data
@AllArgsConstructor
public class Profile {
    @SerializedName("profile")
    @Expose
    private String profile;

    @SerializedName("valuesAccessSpecifics")
    @Expose
    private List<ValueAccessSpecific>valuesAccessSpecifics;

}
