package api.java.backend.annotation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(final ValidPassword arg0){
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator  validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8,16),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new LowercaseCharacterRule(1),
                //new NumericalSequenceRule(3,false),
                //new AlphabeticalSequenceRule(3,false),
                //new QwertySequenceRule(3,false),
                new WhitespaceRule()
         ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }

        List<String> messages = validator.getMessages(result );
        //String messageTemplate = messages
         //       .stream()
         //       .collect(Collectors.joining(","));

        String messageTemplate = "La contraseña debe tener minimo 8 caracteres, un número, una letra mayúscula, una letra minuscula y un caracter especial.";

        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }


}
// https://www.baeldung.com/registration-password-strength-and-rules
// https://stackabuse.com/spring-custom-password-validation/
