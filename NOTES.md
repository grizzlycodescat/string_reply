# Notes

1. I added the logic for reversing and encoding the string to `ReplyMessage.java` as it is cleaner, and allows the controller to focus on handling requests, and the class deals with manipulating the message.
2. The switch case for rules is moved to a private function so that its easier to read and modify in the future. 
3. Same for iterating over an array of characers once the request string has been split. There's no need to have additional code there. This and the rule above help in case there are more rules added in the future.


# Environment Setup

I used opensdk-java11, and VS code. 

