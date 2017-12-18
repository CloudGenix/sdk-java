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

public class TopNQuery 
{ 
    @SerializedName("topn_basis") 
    public String basis;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("end_time") 
    public String endTime;

    @SerializedName("top_n")
    public TopNSettings topn;

    @SerializedName("filter")
    public FilterSettings filter;
 
    public TopNQuery(
        String startTime,
        String endTime, 
        String basis,
        String topnType,
        int limit
        ) throws Exception
    {
        if (stringNullOrEmpty(startTime)) throw new NullPointerException("startTime must not be null");
        if (stringNullOrEmpty(endTime)) throw new NullPointerException("endTime must not be null");
        if (stringNullOrEmpty(basis)) throw new NullPointerException("basis must not be null");
        if (stringNullOrEmpty(topnType)) throw new NullPointerException("topnType must not be null");

        if (!ValidateBasis(basis)) throw new Exception("Invalid basis");
        if (!ValidateType(topnType)) throw new Exception("Invalid top N type");

        this.startTime = startTime;
        this.endTime = endTime;
        this.basis = basis;

        this.topn = new TopNSettings();
        this.topn.type = topnType;
        this.topn.limit = limit;

        this.filter = null;
    }

    public TopNQuery()
    {
        this.startTime = null;
        this.endTime = null;
        this.basis = null;
        this.topn = new TopNSettings();
        this.filter = new FilterSettings();
    }
 
    public void AddSiteFilter(String siteId)
    {
        if (stringNullOrEmpty(siteId)) throw new NullPointerException("siteId must not be null");
        this.filter.siteIds.add(siteId);
    }
 
    private Boolean ValidateBasis(String name)
    {
        List<String> values = new ArrayList<>();
        values.add("traffic_volume");
        values.add("initiation_failure");
        values.add("transaction_failure");
        values.add("tcp_flow");
        values.add("udp_flow");

        return values.contains(name);
    }

    private Boolean ValidateType(String name)
    {
        List<String> values = new ArrayList<>();
        values.add("app");
        values.add("site");
        
        return values.contains(name);
    }
 
    public class TopNSettings
    {
        @SerializedName("type")
        public String type;

        @SerializedName("limit")
        public int limit;

        public TopNSettings()
        {

        } 
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
            this.siteIds = new ArrayList<>();
            this.appIds = new ArrayList<>();
            this.pathTypes = new ArrayList<>();
        } 
    }

    private Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    } 
}
