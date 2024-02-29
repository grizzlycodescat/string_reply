package com.beta.replyservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

    @GetMapping("/reply")
    public ResponseEntity<ReplyMessage> replying() {
        return new ResponseEntity<>(new ReplyMessage("Message is empty"), HttpStatus.OK);
    }

    @GetMapping("/reply/{message}")
    public ResponseEntity<ReplyMessage> replying(@PathVariable String message) {
        return new ResponseEntity<>(new ReplyMessage(message), HttpStatus.OK);
    }

    // new functionality at v2 endpoint
    @GetMapping("/v2/reply/{message}")
    public ResponseEntity<?> replyingV2(@PathVariable String message) {

        // String Parsing logic
        String[] splitString = message.split("-", 2);
        String ruleString = splitString[0];
        String messageString = splitString[1];
        String processedString = messageString;

        // Check if message is empty or if the format is violated for this endpoint eg:
        if (splitString.length < 2) {
            return ResponseEntity.badRequest().body("Invalid Input");
        } else if (splitString[1].isEmpty()) {
            return ResponseEntity.ok().body("Empty Message");
        }

        // Iterate through array of rules
        for (char rules : ruleString.toCharArray()) {
            try {
                processedString = rulesSwitchCase(processedString, rules);
            } catch(IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        
        return new ResponseEntity<>(new ReplyMessage(processedString), HttpStatus.OK);
    }
    
    // Rules selection function
    private String rulesSwitchCase(String input, char rule) {
        switch (rule) {
            case '1':
                input = ReplyMessage.reverseString(input);
                return input;
            case '2':
                input = ReplyMessage.encodeStringtoMD5(input);
                return input;
            default:
                throw new IllegalArgumentException("Invalid Input");
        }
    }

}