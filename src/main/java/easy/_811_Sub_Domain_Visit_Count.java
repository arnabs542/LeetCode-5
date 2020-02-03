package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
 * </p>
 */
public class _811_Sub_Domain_Visit_Count {
    // core logic: use the map to keep a count of all domains and sub domains
    // loop through all the domains: 1) save the whole domain and its count in the map 2) loop through the split string and whenever you encounter the sub domain ("."), save its count in the map
    private static List<String> subDomainVisits(String[] domains) {
        List<String> result = new ArrayList<>();
        if (domains == null || domains.length == 0) {
            return result;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String domain : domains) {
            String[] domainSplit = domain.split(" ");

            /*map.putIfAbsent(domainSplit[1], 0);
            map.put(domainSplit[1], map.get(domainSplit[1]) + Integer.valueOf(domainSplit[0]));*/

            map.put(domainSplit[1], map.getOrDefault(domainSplit[1], 0) + Integer.valueOf(domainSplit[0])); // simple way of modifying a map (equivalent to above 2 statements)

            for (int i = 0; i < domainSplit[1].length(); i++) {
                if (domainSplit[1].charAt(i) == '.') {
                    String subDomain = domainSplit[1].substring(i + 1);
                    map.put(subDomain, map.getOrDefault(subDomain, 0) + Integer.valueOf(domainSplit[0]));   // simple way of modifying a map
                }
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] domains1 = new String[]{"9001 discuss.leetcode.com"};
        String[] domains2 = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        System.out.println(subDomainVisits(domains1));
        System.out.println(subDomainVisits(domains2));
    }
}