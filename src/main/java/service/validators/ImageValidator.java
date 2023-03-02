package service.validators;

public class ImageValidator {
    public static void validate(String imagePath) throws Exception{
        SqlInjectionValidator.validate(imagePath);
    }
}
