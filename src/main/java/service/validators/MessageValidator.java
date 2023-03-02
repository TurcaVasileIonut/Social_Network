package service.validators;

import service.validators.exceptions.MessageInvalidException;

public class MessageValidator {
    public static void validate(String sender, String receiver, String messageContent) throws Exception{
        String message = new String();
        if(sender.isEmpty())
            message = message + "Sender can not be empty! ";
        if(receiver.isEmpty())
            message = messageContent + "Receiver can not be empty! ";
        if(messageContent.isEmpty())
            message = message + "Message content can not be empty!";
        SqlInjectionValidator.validate(sender, receiver, messageContent);
        if(!message.isEmpty())
            throw new MessageInvalidException(message);
    }
}
