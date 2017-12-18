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
import java.util.ArrayList;
import java.util.List;

public class MetricsQuery 
{ 
    @SerializedName("start_time") 
    public String startTime;

    @SerializedName("end_time") 
    public String endTime;

    @SerializedName("interval")
    public String interval;

    @SerializedName("metrics")
    public List<MetricsSettings> metrics;

    @SerializedName("view")
    public ViewSettings view;

    @SerializedName("filter")
    public FilterSettings filter;
 
    public MetricsQuery(
        String startTime,
        String endTime,
        String interval,
        String view
        )
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;

        this.view = new ViewSettings();
        this.view.individual = view;

        this.metrics = new ArrayList<MetricsSettings>();
        this.view = new ViewSettings();
        this.filter = new FilterSettings();
    }

    public MetricsQuery()
    {
        this.startTime = null;
        this.endTime = null;
        this.interval = null;
        this.metrics = new ArrayList<MetricsSettings>();
        this.view = new ViewSettings();
        this.filter = new FilterSettings();
    }
 
    public void AddMetric(String name, List<String> statistics, String unit) throws Exception
    {
        if (stringNullOrEmpty(name)) throw new NullPointerException("name must not be null");
        if (statistics == null || statistics.size() < 1) throw new NullPointerException("statistics must not be null");
        if (stringNullOrEmpty(unit)) throw new NullPointerException("unit must not be null");
        if (!ValidateName(name)) throw new Exception("Invalid value for name");
        if (!ValidateStatistics(statistics)) throw new Exception("Invalid value for statistics");
        if (!ValidateUnit(unit)) throw new Exception("Invalid value for unit");

        MetricsSettings m = new MetricsSettings();
        m.name = name;
        m.statistics = statistics;
        m.unit = unit;

        this.metrics.add(m);
    }

    public void AddSiteFilter(String siteId)
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        this.filter.siteIds.add(siteId);
    }

    public void AddAppFilter(String appId)
    {
        if (stringNullOrEmpty(appId)) throw new NullPointerException("appId must not be null");
        this.filter.appIds.add(appId);
    }

    public void AddPathTypeFilter(String pathType) throws Exception
    {
        if (stringNullOrEmpty(pathType)) throw new NullPointerException("pathType must not be null");
        if (!ValidatePathType(pathType)) throw new Exception("Invalid path type");
        this.filter.pathTypes.add(pathType);
    }

    public void AddDirectionFilter(String direction) throws Exception
    {
        if (stringNullOrEmpty(direction)) throw new NullPointerException("direction must not be null");
        if (!ValidateDirection(direction)) throw new Exception("Invalid direction");
        this.filter.direction = direction;
    }
 
    private Boolean ValidateStatistics(List<String> statistics)
    {
        List<String> values = new ArrayList<>();
        values.add("average");
        values.add("max");
        values.add("sum");
        values.add("min");

        return statistics.stream().noneMatch((curr) -> (!values.contains(curr)));
    }

    private Boolean ValidateName(String name)
    {
        List<String> values = new ArrayList<>();
        values.add("BandwidthUsage");
        values.add("AppSuccessfulConnections");
        values.add("AppSuccessfulTransactions");
        values.add("AppFailedToEstablish");
        values.add("AppTransactionFailures");
        values.add("AppUnreachable");
        values.add("AppSiteHealth");
        values.add("AppNormalizedNetworkTransferTime");
        values.add("AppRoundTripTime");
        values.add("AppServerResponseTime");
        values.add("AppUDPTransactionResponseTime");
        values.add("TCPFlowCount");
        values.add("UDPFlowCount");
        values.add("TCPConcurrentFlows");
        values.add("UDPConcurrentFlows");
        values.add("AppPerfUDPAudioBandwidth");
        values.add("AppPerfUDPVideoBandwidth");
        values.add("AppPerfUDPAudioJitter");
        values.add("AppPerfUDPVideoJitter");
        values.add("AppPerfUDPAudioPacketLoss");
        values.add("AppAudioMos");
        values.add("LqmLinkHealth");
        values.add("LqmLatency");
        values.add( "LqmJitter");
        values.add("LqmPacketLoss");
        values.add("LqmMos");

        return values.contains(name);
    }

    private Boolean ValidateUnit(String unit)
    {
        List<String> values = new ArrayList<>();
        values.add("Mbps");
        values.add("count");
        values.add("milliseconds");
        values.add("Percentage");

        return values.contains(unit);
    }

    private Boolean ValidateDirection(String direction)
    {
        List<String> values = new ArrayList<>();
        values.add("ingress");
        values.add("egress");

        return values.contains(direction);
    }

    private Boolean ValidatePathType(String pathType)
    {
        List<String> values = new ArrayList<>();
        values.add("DirectInternet");
        values.add("VPN");
        values.add("PrivateVPN");
        values.add("PrivateWAN");

        return values.contains(pathType);
    }
 
    public class MetricsSettings
    {
        @SerializedName("name")
        public String name;

        @SerializedName("statistics")
        public List<String> statistics;

        @SerializedName("unit")
        public String unit;

        public MetricsSettings()
        {
            statistics = new ArrayList<>();
        } 
    }

    public class ViewSettings
    {
        @SerializedName("individual")
        public String individual;  // app, site, path_type 
    }

    public class FilterSettings
    {
        @SerializedName("site")
        public List<String> siteIds;

        @SerializedName("app")
        public List<String> appIds;

        @SerializedName("path_type")
        public List<String> pathTypes;

        @SerializedName("direction")
        public String direction;

        public FilterSettings()
        {
            siteIds = new ArrayList<>();
            appIds = new ArrayList<>();
            pathTypes = new ArrayList<>();
        } 
    }

    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    } 
}
