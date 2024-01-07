// this section for password strength algoritm calculate

public class PasswordGeneratorController {
    private PasswordGenerator passwordGenerator;
    

    public PasswordGeneratorController() {
        this.passwordGenerator = new PasswordGenerator();
    }

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols) {
        return passwordGenerator.generatePassword(length, includeUppercase, includeLowercase, includeNumbers, includeSymbols);
    }

    public int calculatePasswordStrength(String password) {
        // password strength algoritm
        

        // Örnek bir güçlük hesaplaması:
        int strength = 0;
        int lengthScore = Math.min(password.length() * 2, 45); // Password length score
        int upperCaseScore = (password.matches(".*[A-Z].*")) ? 10 : 5; // uppercase score
        int lowerCaseScore = (password.matches(".*[a-z].*")) ? 10 : 5; // lowercase score
        int digitScore = (password.matches(".*\\d.*")) ? 15 : 10; // numbers score
        int symbolScore = (password.matches(".*[!@#$%^&*()_+={}\\[\\]|:;'<>,.?/].*")) ? 20 : 15; // symbols score

        if(password.length() < 4)
        {
            lengthScore -= 10;
            lowerCaseScore -= 10;
            upperCaseScore -= 10;
            digitScore -= 10;
            symbolScore -= 10;
        }
        else if(password.length() >= 4 && password.length() < 8)
        {
            lengthScore += 0;
            lowerCaseScore -= 8;
            upperCaseScore -= 8;
            digitScore -= 8;
            symbolScore -= 8;
        }
        else if(password.length() >= 8 && password.length() < 12)
        {
    
            lengthScore += 0;
            lowerCaseScore -= 3;
            upperCaseScore -= 3;
            digitScore -= 3;
            symbolScore -= 3;
        }
        else if(password.length() < 20 && password.length() >= 12 )
        {
            lengthScore += 0;
        }
        else
        {
            lengthScore *= 1.5;
        }

        strength = lengthScore + upperCaseScore + lowerCaseScore + digitScore + symbolScore;

        return strength;
    }
}
