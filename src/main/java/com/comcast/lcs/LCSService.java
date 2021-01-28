package com.comcast.lcs;

import com.comcast.lcs.domain.LCValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class LCSService {

    private final static Logger logger = LoggerFactory.getLogger(LCSService.class);

    /**
     * finds the longest common substring within a list of strings
     * @param values
     * @return
     */
    public List<LCValue> findLongest(List<LCValue> values)  {

        List<LCValue>  lcs = new ArrayList<>();  //  final list for return to client
        List<String> subs = new ArrayList<>();  // holds common substrings

        String valueOne = values.get(0).getValue();  //  get the first string in list get all substrings from

        String  longest = "";    //  holds the longest common substring
        boolean inAllInd = true;  //  indicator that substring is found in all substring arguments

        for(int i=0; i < valueOne.length(); ++i)
        {
            for(int j=valueOne.length(); j > i ; --j)  // load longest substrings first
            {
               String subst = valueOne.substring(i,j);
                for (LCValue v : values) {
                    if(!v.getValue().contains(subst))
                    {
                        inAllInd = false;   //  not found in this one, no good
                        break;              // no need to spin through the rest
                    }
                }
                if(inAllInd)   // found substring in all values
                {
                    if(subst.length() >= longest.length())  // winnow out shorter ones
                    {
                        longest = subst;
                        subs.add(subst);
                    }
                }  else  {
                    inAllInd = true;  //  reset indicator for next substring
                }
            }
        }

        Collections.sort(subs);     //  sort the ones found, even a few short ones
        for(String s : subs) {      //  collect any ties for longest substring
            if(s.length() == longest.length())  // only longest or tied for longest
            {
                LCValue lcv = new LCValue(s);
                lcs.add(lcv);
            }
        }

        return lcs;
    }
}
