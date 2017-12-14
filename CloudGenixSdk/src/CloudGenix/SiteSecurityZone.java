
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class SiteSecurityZone
{
    @SerializedName("id")
    public String id;

    @SerializedName("zone_id")
    public String zoneId;

    @SerializedName("networks")
    public Object networks;
}
