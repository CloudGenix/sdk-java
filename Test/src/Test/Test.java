
package Test;

import CloudGenix.*; 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Scanner;

public class Test 
{ 
    public static CloudGenixSdk sdk;
    public static Boolean runForever = true;
    public static Scanner scanner = new Scanner(System. in); 
    
    public static MetricsQuery metricsQuery = null;
    public static TopNQuery topnQuery = null;
    public static FlowQuery flowQuery = null;
    public static EventQuery eventQuery = null;
    
    public static void main(String[] args) throws Exception 
    {
        System.out.println("CloudGenix Controller SDK"); 
        sdk = new CloudGenixSdk("demo@cloudgenix.com", "demo@cloudgenix.com", true);
        
        while (runForever)
        {
            System.out.print("Command [? for help] > ");
            String cmd = scanner. nextLine();
            if (cmd == null || cmd.length() < 1) continue;
            
            switch (cmd)
            {
                // <editor-fold Desc="General Commands">
                
                case "?":
                    menu();
                    break;
                    
                case "q":
                    runForever = false;
                    break;
                     
                case "logout":
                    System.out.println(sdk.logout());
                    break;
                
                // </editor-fold>
                    
                // <editor-fold Desc="Show Commands">

                case "show token":
                    System.out.println(sdk.authToken);
                    break;
                    
                case "show tenant_id":
                    System.out.println(sdk.tenantId);
                    break;
                    
                case "show versions":
                    System.out.println(serialize(sdk.getAllVersions()));
                    break;
                    
                case "show endpoints":
                    System.out.println(serialize(sdk.getAllEndpoints()));
                    break;
                    
                // </editor-fold>
                
                // <editor-fold Desc="Get Commands">
                
                case "get contexts":
                    System.out.println(serialize(sdk.getContexts()));
                    break;
                    
                case "get sites":
                    System.out.println(serialize(sdk.getSites()));
                    break;
                    
                case "get elements":
                    System.out.println(serialize(sdk.getElements()));
                    break;
                    
                case "get interfaces":
                    System.out.println(serialize(
                        sdk.getElementInterfaces(
                            inputString("Site ID: ", false), 
                            inputString("Element ID: ", false)
                        )));
                    break;
                    
                case "get ifstatus":
                    System.out.println(serialize(
                        sdk.getElementInterfaceStatus(
                            inputString("Site ID: ", false), 
                            inputString("Element ID: ", false),
                            inputString("Interface ID: ", false)
                        )));
                    break;
                    
                case "get wans":
                    System.out.println(serialize(sdk.getWanNetworks()));
                    break;
                    
                case "get lans":
                    System.out.println(serialize(
                        sdk.getLanNetworks(
                            inputString("Site ID: ", false)
                        )));
                    break;
                    
                case "get appdefs":
                    System.out.println(serialize(sdk.getApplicationDefinitions()));
                    break;
                    
                case "get policysets":
                    System.out.println(serialize(sdk.getPolicySets()));
                    break;
                    
                case "get policyrules":
                    System.out.println(serialize(
                        sdk.getPolicyRules(
                            inputString("Policy Set ID: ", false)
                        )));
                    break;

                case "get seczones":
                    System.out.println(serialize(sdk.getSecurityZones()));
                    break;

                case "get siteseczones":
                    System.out.println(serialize(
                        sdk.getSiteSecurityZones(
                            inputString("Site ID: ", false)
                        )));
                    break;

                case "get secpolsets":
                    System.out.println(serialize(sdk.getSecurityPolicySets()));
                    break;

                case "get secpolrules":
                    System.out.println(serialize(
                        sdk.getSecurityPolicyRules(
                            inputString("Security Policy Set ID: ", false)
                        )));
                    break;

                case "get sitewanifs":
                    System.out.println(serialize(
                        sdk.getSiteWanInterfaces(
                            inputString("Site ID: ", false)
                        )));
                    break;

                case "get topology":
                    System.out.println(serialize(
                        sdk.getSiteTopology(
                            inputString("Site ID: ", false)
                        )));
                    break;

                case "get snmpagents":
                    System.out.println(serialize(
                        sdk.getSnmpAgents(
                            inputString("Site ID: ", false),
                            inputString("Element ID: ", false)
                        )));
                    break;
                    
                // </editor-fold>
                    
                // <editor-fold Desc="Metrics Commands">
                    
                case "metrics clear":
                    clearMetricsQuery();
                    break;
                    
                case "metrics show":
                    showMetricsQuery();
                    break;
                    
                case "metrics build":
                    buildMetricsQuery();
                    break;
                    
                case "metrics addmetric":
                    addMetric();
                    break;
                    
                case "metrics addfilter":
                    addMetricsFilter();
                    break;
                    
                case "metrics submit":
                    System.out.println(serialize(sdk.getMetrics(metricsQuery)));
                    break;
                    
                // </editor-fold>
                    
                // <editor-fold Desc="TopN Commands">
                    
                case "topn clear":
                    clearTopNQuery();
                    break;
                    
                case "topn show":
                    showTopNQuery();
                    break;
                    
                case "topn build":
                    buildTopNQuery();
                    break;
                    
                case "topn addfilter":
                    addTopNFilter();
                    break;
                    
                case "topn submit":
                    System.out.println(serialize(sdk.getTopN(topnQuery)));
                    break;
                    
                // </editor-fold>
                    
                // <editor-fold Desc="Flows Commands">
                    
                case "flows clear":
                    clearFlowsQuery();
                    break;
                    
                case "flows show":
                    showFlowsQuery();
                    break;
                    
                case "flows build":
                    buildFlowsQuery();
                    break;
                    
                case "flows addfilter":
                    addFlowsFilter();
                    break;
                    
                case "flows submit":
                    System.out.println(serialize(sdk.getFlows(flowQuery)));
                    break;
                    
                // </editor-fold>
                    
                // <editor-fold Desc="Events Commands">
                    
                case "events clear":
                    clearEventsQuery();
                    break;
                    
                case "events show":
                    showEventsQuery();
                    break;
                    
                case "events build":
                    buildEventsQuery();
                    break;
                    
                case "events submit":
                    System.out.println(serialize(sdk.getEvents(eventQuery)));
                    break;

                // </editor-fold>
                
                default:
                    break;
            }
        }
    }
    
    private static void menu()
    {
        System.out.println("CloudGenix Controller -- Available Commands");
        System.out.println("  ?              help, this menu");
        System.out.println("  q              quit"); 
        System.out.println("  logout         logout of the controller session");
        System.out.println("  show <cmd>     show commands");
        System.out.println("                 | token   tenant_id   versions   endpoints");
        System.out.println("  get <cmd>      retrieve objects");
        System.out.println("                 | contexts   sites   elements   interfaces   ifstatus");
        System.out.println("                 | wans   lans   appdefs   policysets   policyrules");
        System.out.println("                 | seczones   siteseczones   secpolsets   secpolrules");
        System.out.println("                 | sitewanifs   topology   snmpagents");
        System.out.println("  metrics <cmd>  retrieve metrics");
        System.out.println("                 | clear   show   build   addmetric   addfilter   submit");
        System.out.println("  topn <cmd>     retrieve top N statistics");
        System.out.println("                 | clear   show   build   addfilter   submit");
        System.out.println("  flows <cmd>    retrieve flows");
        System.out.println("                 | clear   show   build   addfilter   submit");
        System.out.println("  events <cmd>   retrieve events");
        System.out.println("                 | clear   show   build   submit");
        System.out.println("");
    }
     
    private static String serialize(Object o) 
    {
        if (o == null) return "(null)";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(o);    
        return json;
    }
    
    private static String inputString(String prompt, Boolean allowNull)
    {
        System.out.print(prompt);
        
        String val = null;
        
        while (true)
        {
            val = scanner.nextLine();
            if (!allowNull && stringNullOrEmpty(val)) continue;
            break;
        }
        
        return val;
    }
    
    private static int inputInteger(String prompt)
    {
        System.out.print(prompt);
        
        String val = scanner.nextLine();
        
        return Integer.parseInt(val);
    }
    
    private static Boolean inputBoolean(String prompt)
    {
        System.out.print(prompt);
        
        String val = scanner.nextLine();
        
        return Boolean.parseBoolean(val);
    }
    
    private static Boolean stringNullOrEmpty(String s) 
    {
        if (s == null) return true;
        return s.isEmpty();
    }
     
    private static void clearMetricsQuery()
    {
        metricsQuery = null;
        System.out.println("Cleared");
    }

    private static void showMetricsQuery()
    {
        if (metricsQuery == null)
        {
            System.out.println("Please build a metrics query first");
            return;
        }

        System.out.println(serialize(metricsQuery));
    }

    private static void buildMetricsQuery()
    {
        System.out.println("Supply timestamps in the form of yyyy-MM-ddTHH:mm:ss.zzzZ");

        String startTime = inputString("Start time :", false);
        String endTime = inputString("End time   :", false);
        String interval = inputString("Interval   :", false);
        String view = inputString("View       :", true); 
        metricsQuery = new MetricsQuery(startTime, endTime, interval, view); 
    }

    private static void addMetric()
    {
        if (metricsQuery == null)
        {
            System.out.println("Please build a metrics query first");
            return;
        }

        String name = inputString("Name       :", false);
        String stat = inputString("Statistics :", false);
        String unit = inputString("Unit       :", false);

        MetricsQuery.MetricsSettings metric = metricsQuery.new MetricsSettings();
        metric.name = name;
        metric.unit = unit;
        metric.statistics = new ArrayList<>();
        metric.statistics.add(stat);

        metricsQuery.metrics.add(metric); 
    }

    private static void addMetricsFilter()
    {
        if (metricsQuery == null)
        {
            System.out.println("Please build a metrics query first");
            return;
        }
 
        String appId =  inputString("App ID    :", true);
        String dir =    inputString("Direction :", true);
        String pType =  inputString("Path type :", true);
        String siteId = inputString("Site ID   :", true);

        MetricsQuery.FilterSettings filter = metricsQuery.new FilterSettings();
        
        filter.direction = dir;

        filter.appIds = new ArrayList<>();
        if (!stringNullOrEmpty(appId)) filter.appIds.add(appId);

        filter.pathTypes = new ArrayList<>();
        if (!stringNullOrEmpty(pType)) filter.pathTypes.add(pType);

        filter.siteIds = new ArrayList<>();
        if (!stringNullOrEmpty(siteId)) filter.siteIds.add(siteId);

        metricsQuery.filter = filter;
    }
 
    private static void clearTopNQuery()
    {
        topnQuery = null;
        System.out.println("Cleared");
    }

    private static void showTopNQuery()
    {
        if (topnQuery == null)
        {
            System.out.println("Please build a topn query first");
            return;
        }

        System.out.println(serialize(topnQuery));
    }

    private static void buildTopNQuery() throws Exception
    {
        System.out.println("Supply timestamps in the form of yyyy-MM-ddTHH:mm:ss.zzzZ");

        String startTime = inputString("Start time :", false);
        String endTime =   inputString("End time   :", false);
        String basis =     inputString("Basis      :", false);
        String topNType =  inputString("Type       :", false);
        int limit =       inputInteger("Limit      :");
 
        topnQuery = new TopNQuery(startTime, endTime, basis, topNType, limit); 
    }

    private static void addTopNFilter()
    {
        if (topnQuery == null)
        {
            System.out.println("Please build a topn query first");
            return;
        }

        String appId =  inputString("App ID    :", true);
        String dir =    inputString("Direction :", true);
        String pType =  inputString("Path type :", true);
        String siteId = inputString("Site ID   :", true);

        TopNQuery.FilterSettings filter = topnQuery.new FilterSettings();
        
        filter.direction = dir;

        filter.appIds = new ArrayList<>();
        if (!stringNullOrEmpty(appId)) filter.appIds.add(appId);

        filter.pathTypes = new ArrayList<>();
        if (!stringNullOrEmpty(pType)) filter.pathTypes.add(pType);

        filter.siteIds = new ArrayList<>();
        if (!stringNullOrEmpty(siteId)) filter.siteIds.add(siteId);

        topnQuery.filter = filter; 
    }

    private static void clearFlowsQuery()
    {
        flowQuery = null;
        System.out.println("Cleared");
    }

    private static void showFlowsQuery()
    {
        if (flowQuery == null)
        {
            System.out.println("Please build a flows query first");
            return;
        }

        System.out.println(serialize(flowQuery));
    }

    private static void buildFlowsQuery()
    {
        System.out.println("Supply timestamps in the form of yyyy-MM-ddTHH:mm:ss.zzzZ");

        String startTime =  inputString("Start time  :", false);
        String endTime =    inputString("End time    :", false);
        String debugLevel = inputString("Debug level :", false);
 
        flowQuery = new FlowQuery(startTime, endTime, debugLevel); 
    }

    private static void addFlowsFilter()
    {
        if (flowQuery == null)
        {
            System.out.println("Please build a flows query first");
            return;
        }
 
        String appId =  inputString("App ID    :", true);
        String dir =    inputString("Direction :", true);
        String pType =  inputString("Path type :", true);
        String siteId = inputString("Site ID   :", true);

        FlowQuery.FilterSettings filter = flowQuery.new FilterSettings();
        
        if (!stringNullOrEmpty(dir)) filter.direction = dir;

        if (!stringNullOrEmpty(appId))
        {
            filter.appIds = new ArrayList<>();
            filter.appIds.add(appId);
        }

        if (!stringNullOrEmpty(pType))
        {
            filter.pathTypes = new ArrayList<>();
            filter.pathTypes.add(pType);
        }

        if (!stringNullOrEmpty(siteId))
        {
            filter.siteIds = new ArrayList<>();
            filter.siteIds.add(siteId);
        }

        flowQuery.filter = filter; 
    }
 
    private static void clearEventsQuery()
    {
        eventQuery = null;
        System.out.println("Cleared");
    }

    private static void showEventsQuery()
    {
        if (eventQuery == null)
        {
            System.out.println("Please build an events query first");
            return;
        }

        System.out.println(serialize(eventQuery));
    }

    private static void buildEventsQuery()
    {
        System.out.println("Supply timestamps in the form of yyyy-MM-ddTHH:mm:ss.zzzZ");

        String startTime = inputString("Start time  :", false);
        String endTime =   inputString("End time    :", false);
        String offset =    inputString("Offset      :", true);
        String queryType = inputString("Query type  :", true);
        Boolean summary = inputBoolean("Summary     :");
 
        eventQuery = new EventQuery(startTime, endTime, offset, queryType, summary); 
    }

}
