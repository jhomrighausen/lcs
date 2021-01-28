package com.comcast.lcs;

import com.comcast.lcs.domain.LCRequest;
import com.comcast.lcs.domain.LCSList;
import com.comcast.lcs.domain.LCValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class LCSController {

    final static String NO_BODY = "There is no body to the POST request";
    final static String NO_STRINGS = "There are no strings to compare in the request";
    final static String NOT_A_SET = "There are duplicate strings, must be a set";
    final static String ONLY_ONE = "There is only one string in the set";

    private final static Logger logger = LoggerFactory.getLogger(LCSController.class);

    @Autowired
    LCSService lcsService;

    @CrossOrigin
    @RequestMapping(value="lcs", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> longestCommonString(@RequestHeader HttpHeaders headers, @RequestBody final LCRequest lcRequest)
    {
        //   Return no body, error message
        if(lcRequest == null || lcRequest.getSetOfStrings() == null)
        {
            return new ResponseEntity<>(NO_BODY, HttpStatus.BAD_REQUEST);
        }

        //   Return no strings, error message
        if(lcRequest.getSetOfStrings().isEmpty())
        {
            return new ResponseEntity<>(NO_STRINGS, HttpStatus.BAD_REQUEST);
        }

        //   Return only one string
        if(lcRequest.getSetOfStrings().size() == 1)
        {
            return new ResponseEntity<>(ONLY_ONE, HttpStatus.BAD_REQUEST);
        }

       boolean isASet = true;
       Set<LCValue> valueSet = new HashSet<>();
       for(LCValue v : lcRequest.getSetOfStrings())
       {
           if(!valueSet.add(v))  // trying to add duplicate
           {
               isASet = false;
           }
       }
       //  Return not a set, error message
       if(!isASet)
       {
           return new ResponseEntity<>(NOT_A_SET, HttpStatus.BAD_REQUEST);
       }

       LCSList list = new LCSList();
       List<LCValue> lcStrings = lcsService.findLongest(lcRequest.getSetOfStrings());
       list.setLcs(lcStrings);

       return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * show the headers sent with the request
     * @param headers
     */
    private void showHeaders(HttpHeaders headers)
    {
        headers.entrySet().forEach(v -> logger.info("headers {}", v));
    }

}
