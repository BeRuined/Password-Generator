// this section for backend and will generate the password

import java.util.Random;

public class PasswordGenerator {

    // character pools
    // we randomly take character in these pools to create random passwords
    public static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String SYMBOLS = "~`!@#$%^&*()_-+={}[]|:;'<>,.?/";

    // random class allows us to generate a random number which will be used to randomly choose the characters
    private final Random random;

    // constructor
    public PasswordGenerator(){random = new Random();}

    // lenght - lenght of the password to be generated
    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols){

        // string builder
        StringBuilder passwordBuilder = new StringBuilder();

        // store valid characters(toggle states)
        String validCharacters = "";
        if(includeUppercase) validCharacters += UPPERCASE_CHARACTERS;
        if(includeLowercase) validCharacters += LOWERCASE_CHARACTERS;
        if(includeNumbers) validCharacters += NUMBERS;
        if(includeSymbols) validCharacters += SYMBOLS;

        // build password
        for(int i = 0; i < length; i++){
            // generate a random index
            int randomIndex = random.nextInt(validCharacters.length());

            // get the char based on the random index
            char randomChar = validCharacters.charAt(randomIndex);
            
            // store char into password builder
            passwordBuilder.append(randomChar);

            // do this until we have reached the length that user has provided to us

        }
        // return the result
        return passwordBuilder.toString();
    }
    
}