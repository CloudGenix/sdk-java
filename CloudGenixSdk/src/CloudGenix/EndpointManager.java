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
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

public class EndpointManager {
    // <editor-fold desc="Public Members">
    // </editor-fold>
    
    // <editor-fold desc="Private Members">
    
    private HashMap<String, String> versions;
    private HashMap<String, String> endpoints;
    
    // </editor-fold>
    
    // <editor-fold desc="Constructors and Factories">
    
    public EndpointManager() {
        
        versions = new HashMap<String, String>();
        endpoints = new HashMap<String, String>();
        
        addVersion("login", "v2.0");
        addVersion("logout", "v2.0");
        addVersion("permissions", "v2.0");
        addVersion("profile", "v2.0");
        addVersion("query_events", "v2.0");
        
        addEndpoint("login", "/%s/api/login");
        addEndpoint("logout", "/%s/api/logout");
        addEndpoint("permissions", "/%s/api/permissions");
        addEndpoint("profile", "/%s/api/profile");
        addEndpoint("flows_monitor", "/%s/api/tenants/%s/monitor/flows");
        addEndpoint("query_events", "/%s/api/tenants/%s/events/query");
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Public Methods">
    
    public void addVersion(String api, String version)
    {
        if (versions.containsKey(api))
        {
            // do not override static definitions
            return;
        }
        
        versions.put(api, version);
        return;
    }
    
    public String getVersion(String api)
    {
        if (versions.containsKey(api))
        {
            return versions.get(api);
        }
        
        return null;
    }
    
    public HashMap<String, String> getAllVersions()
    {
        return versions;
    }
    
    public void addEndpoint(String api, String endpoint)
    {
        if (endpoints.containsKey(api))
        {
            // do not override static definitions
            return;
        }
        
        if (versions.containsKey(api))
        {
            String version = versions.get(api);
            String amendedEndpoint = endpoint.replaceFirst("%s", version);
            endpoints.put(api, amendedEndpoint);
            return;
        }
    }
    
    public String getEndpoint(String api)
    {
        if (endpoints.containsKey(api))
        {
            return endpoints.get(api);
        }
        
        return null;
    }
    
    public HashMap<String, String> getAllEndpoints()
    {
        return endpoints;
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Private Methods">
    // </editor-fold>
}
