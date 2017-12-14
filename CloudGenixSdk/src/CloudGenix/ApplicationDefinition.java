
package CloudGenix;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApplicationDefinition 
{
    @SerializedName("id")
    public String Id;

    @SerializedName("ingress_traffic_pct")
    public int ingressTrafficPercent;

    @SerializedName("conn_idle_timeout")
    public int connectionIdleTimeout;

    @SerializedName("abbreviation")
    public String abbreviation;

    @SerializedName("path_affinity")
    public String pathAffinity;

    @SerializedName("display_name")
    public String displayName;

    @SerializedName("app_type")
    public String appType;

    @SerializedName("category")
    public String category;

    @SerializedName("transfer_type")
    public String transferType;

    @SerializedName("domains")
    public List<String> domains;

    @SerializedName("tcp_rules")
    public Object tcpRules;

    @SerializedName("udp_rules")
    public Object udpRules;
}
