
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class SnmpAgent 
{
    @SerializedName("id")
    public String id;
     
    @SerializedName("tags")
    public Object tags;

    @SerializedName("description")
    public String description;

    @SerializedName("v2_config")
    public Object v2Config;

    @SerializedName("v3_config")
    public Object v3Config;
}
