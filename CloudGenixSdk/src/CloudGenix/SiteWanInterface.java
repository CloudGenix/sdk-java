
package CloudGenix;

import com.google.gson.annotations.SerializedName;

public class SiteWanInterface
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("network_id")
    public String networkId;

    @SerializedName("link_bw_down")
    public int linkBandwidthDownload;

    @SerializedName("link_bw_up")
    public int linkBandwidthUpload;

    @SerializedName("bw_config_mode")
    public String bandwidthConfigMode;

    @SerializedName("bfd_mode")
    public String bfdMode;

    @SerializedName("label_id")
    public String labelId;

    @SerializedName("lqm_enabled")
    public Object lqmEnabled;

    @SerializedName("BwcEnabled")
    public Object bwcEnabled;
}
