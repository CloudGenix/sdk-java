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

public class FlowQuery 
{ 
    @SerializedName("start_time") 
    public String startTime;

    @SerializedName("end_time") 
    public String endTime;

    @SerializedName("debug_level")
    public String debugLevel;

    @SerializedName("filter")
    public FilterSettings filter;
 
    public FlowQuery(
        String startTime,
        String endTime,
        String debugLevel 
        )
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.debugLevel = debugLevel;

        this.filter = new FilterSettings();
    }

    public FlowQuery()
    {
        this.startTime = null;
        this.endTime = null;
        this.debugLevel = null; 
        this.filter = null;
    }
 
    public void AddSiteFilter(String siteId)
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        if (filter == null) filter = new FilterSettings();
        if (filter.siteIds == null) filter.siteIds = new ArrayList<>();
        filter.siteIds.add(siteId);
    }

    public void AddAppFilter(String appId)
    {
        if (stringNullOrEmpty(appId)) throw new NullPointerException("appId must not be null");
        if (filter == null) filter = new FilterSettings();
        if (filter.appIds == null) filter.appIds = new ArrayList<>();
        filter.appIds.add(appId);
    }

    public void AddPathTypeFilter(String pathType) throws Exception
    {
        if (stringNullOrEmpty(pathType)) throw new NullPointerException("pathType must not be null");
        if (!ValidatePathType(pathType)) throw new Exception("Invalid path type");
        if (filter == null) filter = new FilterSettings();
        if (filter.pathTypes == null) filter.pathTypes = new ArrayList<>();
        filter.pathTypes.add(pathType);
    }

    public void AddDirectionFilter(String direction) throws Exception
    {
        if (stringNullOrEmpty(direction)) throw new NullPointerException("direction must not be null");
        if (!ValidateDirection(direction)) throw new Exception("Invalid direction");
        if (filter == null) filter = new FilterSettings();
        filter.direction = direction;
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
            siteIds = null;
            appIds = null;
            pathTypes = null;
            direction = null;
        } 
    } 
    
    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    } 
}
