
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SecurityPolicyRule
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("action")
    public String action;

    @SerializedName("application_ids")
    public List<String> applicationIds;

    @SerializedName("disabled_flag")
    public Boolean disabled;

    @SerializedName("destination_filter_ids")
    public List<String> destinationFilterIds;

    @SerializedName("destination_zone_ids")
    public List<String> destinationZoneIds;

    @SerializedName("source_filter_ids")
    public List<String> sourceFilterIds;

    @SerializedName("source_zone_ids")
    public List<String> sourceZoneIds;
}
