/*

    CloudGenix Controller SDK
    (c) 2017 CloudGenix, Inc.
    All Rights Reserved

    https://www.cloudgenix.com

    This SDK is released under the MIT license.
    For support, please contact us on:

        NetworkToCode Slack channel #cloudgenix: http://slack.networktocode.com
        Email: developers@cloudgenix.com

 */

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
