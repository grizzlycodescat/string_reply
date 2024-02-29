package com.beta.replyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

    @GetMapping("/reply")
    public ReplyMessage replying() {
        return new ReplyMessage("Message is empty");
    }

    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        return new ReplyMessage(message);
    }

    // new functionality at v2 endpoint
    @GetMapping("/v2/reply/{message}")
    public ReplyMessage replyingV2(@PathVariable String message) {

        // String Parsing logic
        String[] splitString = message.split("-", 2);
        String ruleString = splitString[0];
        String messageString = splitString[1];
        String processedString = messageString;

        // Check if message is empty or if the format is violated for this endpoint eg:
        if (splitString.length < 2) {
            return new ReplyMessage("Invalid input format. Expected format: {rule}-{string}");
        } else if (splitString[1].isEmpty()) {
            return new ReplyMessage("Message is empty");
        }

        // Iterate through array of rules
        for (char rules : ruleString.toCharArray()) {
            processedString = rulesSwitchCase(processedString, rules);
        }
        
        return new ReplyMessage(processedString);
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
                return "Invalid Input";
        }
    }

}