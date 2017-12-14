
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class Address 
{
    @SerializedName("street")
    public String street1;
    
    @SerializedName("street2")
    public String street2;
    
    @SerializedName("city")
    public String city;
    
    @SerializedName("state")
    public String state;
    
    @SerializedName("post_code")
    public String postalCode;
    
    @SerializedName("country")
    public String country;
}
