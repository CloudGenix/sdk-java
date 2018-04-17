/*

    CloudGenix Controller SDK
    (c) 2018 CloudGenix, Inc.
    All Rights Reserved

    https://www.cloudgenix.com

    This SDK is released under the MIT license.
    For support, please contact us on:

        NetworkToCode Slack channel #cloudgenix: http://slack.networktocode.com
        Email: developers@cloudgenix.com

 */

// To run:
// java -classpath "lib/*" -jar MspTest.jar
// 

package msptest;
 
import CloudGenix.*;
import java.util.List;
import java.util.Scanner;

public class MspTest 
{
    public static CloudGenixSdk sdk;
    
    public static void main(String[] args) throws Exception 
    {
        Scanner scanner = new Scanner(System. in); 
            
        try
        {    
            System.out.println("CloudGenix Controller SDK"); 
            System.out.print("Email: ");
            String email = scanner.nextLine();

            sdk = new CloudGenixSdk(email, true);

            String samlUrl = sdk.loginSamlStart();
            System.out.println("Copy and paste the following URL in your browser.");
            System.out.println("Press ENTER when done.");
            System.out.println(samlUrl);
            scanner.nextLine();

            if (sdk.loginSamlFinish())
            {
                System.out.println("Login success.");
            }
            else
            {
                System.out.println("Login failure.");
            }
            
            System.out.println("Retrieving clients...");
            List<Client> clients = sdk.getClients();
            if (clients == null || clients.size() < 1)
            {
                System.out.println("No clients retrieved");
                return;
            }
            else
            {
                for (Client curr : clients)
                {
                    System.out.println("  " + curr.id + " " + curr.name + " [" + curr.canonicalName + "]");
                }
            }
            
            System.out.print("Client ID: ");
            String id = scanner.nextLine();

            if (!sdk.loginAsClient(id))
            {
                System.out.println("Login failed for client ID " + id);
                return;
            }
            else
            {
                System.out.println("Login succeeded for client ID " + id); 
            }
            
            List<Site> sites = sdk.getSites();
            if (sites == null || sites.size() < 1)
            {
                System.out.println("No sites retrieved");
                return;
            }
            else
            {
                System.out.println("Sites:");
                for (Site curr : sites)
                {
                    System.out.println("  " + curr.id + " " + curr.name);
                }
            }
            
            return;
        }
        finally
        {
            System.out.println("Press ENTER to exit.");
            scanner.nextLine();
        }
    } 
}
