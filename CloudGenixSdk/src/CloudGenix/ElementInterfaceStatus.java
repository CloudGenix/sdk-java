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
import java.util.List;

public class ElementInterfaceStatus 
{
    @SerializedName("id")
    public String id;

    @SerializedName("_etag")
    public int etag;

    @SerializedName("region")
    public String region;

    @SerializedName("element_id")
    public String elementId;

    @SerializedName("name")
    public String name;

    @SerializedName("negotiated_mtu")
    public int negotiatedMtu;

    @SerializedName("extended_state")
    public Object extendedState;

    @SerializedName("device")
    public String device;

    @SerializedName("pppoe_remote_v4_addr")
    public Object pppoeRemoteV4Address;

    @SerializedName("ipv4_addresses")
    public List<String> ipV4Addresses;

    @SerializedName("ipv6_addresses")
    public List<String> ipV6Addresses;

    @SerializedName("dns_v4_config")
    public Object dnsV4Config;

    @SerializedName("dns_v6_config")
    public Object dnsV6Config;

    @SerializedName("routes")
    public Object routes;

    @SerializedName("operational_state")
    public String operationalState;

    @SerializedName("mac_address")
    public String macAddress;

    @SerializedName("port")
    public Object port;
}
