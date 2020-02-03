package random_practice;

import java.util.HashMap;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * Write a function that takes this input as a parameter and returns a data structure containing the number of clicks that were recorded on each domain AND each subdomain under it.
 * For example, a click on "mail.yahoo.com" counts toward the totals for "mail.yahoo.com", "yahoo.com", and "com". (Subdomains are added to the left of their parent domain. So "mail" and "mail.yahoo" are not valid domains. Note that "mobile.sports" appears as a separate domain near the bottom of the input.)
 * </p>
 */
public class _Indeed_Sub_Domain_Count {
    private static HashMap<String, Integer> countOfDomains(String[] domains) {
        if (domains == null || domains.length == 0) {
            return null;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String domain : domains) {
            String[] domainSplit = domain.split(",");
            map.putIfAbsent(domainSplit[1], 0);
            map.put(domainSplit[1], map.get(domainSplit[1]) + Integer.valueOf(domainSplit[0]));

            for (int i = 0; i < domainSplit[1].length(); i++) {
                if (domainSplit[1].charAt(i) == '.') {
                    String subDomain = domainSplit[1].substring(i + 1);
                    map.putIfAbsent(subDomain, 0);
                    map.put(subDomain, map.get(subDomain) + Integer.valueOf(domainSplit[0]));
                }
            }
        }

        return map;
    }

    public static void main(String[] args) {
        String[] counts = {
                "900,google.com",
                "60,mail.yahoo.com",
                "10,mobile.sports.yahoo.com",
                "40,sports.yahoo.com",
                "300,yahoo.com",
                "10,stackoverflow.com",
                "20,overflow.com",
                "2,en.wikipedia.org",
                "1,m.wikipedia.org",
                "1,mobile.sports",
                "1,google.co.uk"
        };
        System.out.println(countOfDomains(counts));
    }
}
