
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class Location 
{
    @SerializedName("longitude")
    public Double longitude;
    
    @SerializedName("latitude")
    public Double latitude;
    
    @SerializedName("description")
    public String description;
}
