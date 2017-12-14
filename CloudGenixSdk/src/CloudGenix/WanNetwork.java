
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class WanNetwork 
{
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("type")
    public String type;
    
    @SerializedName("provider_as_numbers")
    public Object providerAsNumbers;
}
