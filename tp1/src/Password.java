import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Password {
    /**
     * Hashes the provided password using the SHA-256 algorithm.
     * 
     * @param password the password to be hashed
     * @return a hexadecimal string representing the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Attempts a brute-force attack to find the 6-digit number
     * 
     * @param targetHash the target hash to match
     * @return the 6-digit number that matches, or null if no match is found
     */
    public static String bruteForce6Digit(String targetHash) {
        for (int a = 0; a < 999999; ++a) {
            String digit = String.format("%06d", a);
            String attempt = hashPassword(digit);
            if (attempt.equals(targetHash)) { // Comparaison correcte en Java
                System.out.println(digit);
                System.exit(0); // break Sort uniquement de la boucle `f`, les autres boucles continuent
            }
        }
        return null;
    }

    /**
     * Checks if the given password is strong according to the following criteria:
     * 
     * <ul>
     * <li>Minimum length of 12 characters</li>
     * <li>At least one uppercase letter</li>
     * <li>At least one lowercase letter</li>
     * <li>At least one digit</li>
     * <li>No whitespace characters</li>
     * </ul>
     * 
     * @param password the password to check
     * @return true if the password is strong, false otherwise
     */

    public static boolean isStrongPassword(String password) {
        boolean min_len = false;
        boolean uppercase = false;
        boolean lowercase = false;
        boolean one_digit = false;
        boolean no_whitespace = true;
        List<Character> charList = new ArrayList<>();

        for (char c : password.toCharArray()) {
            charList.add(c);
        }

        if (password.length() > 11) {
            min_len = true;
        }

        for (Character car : charList) {
            if (car.equals(' ')) {
                no_whitespace = false;
            } else if (Character.isUpperCase(car)) {
                uppercase = true;
            } else if (Character.isLowerCase(car)) {
                lowercase = true;
            } else if (Character.isDigit(car)) {
                one_digit = true;
            }
        }

        if (min_len && no_whitespace && uppercase && lowercase && one_digit) {
            System.out.println("Bon mot de passe");
            return true;
        } else {
            System.out.println("Mot de passe non conforme : "
                    + "min_len=" + min_len + ", "
                    + "no_whitespace=" + no_whitespace + ", "
                    + "uppercase=" + uppercase + ", "
                    + "lowercase=" + lowercase + ", "
                    + "one_digit=" + one_digit);
            return false;
        }
    }

    /**
     * Checks the strength of multiple passwords and stores the results in a
     * HashMap.
     *
     * @param passwords An ArrayList of passwords to be checked.
     * @return A HashMap where each password is mapped to a Boolean value:
     *         true if the password is strong, false otherwise
     */
    public static HashMap<String, Boolean> checkPasswordsList(ArrayList<String> passwords) {
        HashMap<String, Boolean> CheckPassword = new HashMap<String, Boolean>();
        for (String pwd : passwords) {
            CheckPassword.put(pwd, isStrongPassword(pwd));
        }
        return CheckPassword;
    }

    /**
     * Generates a secure random password with at least:
     * <ul>
     * <li>1 uppercase letter</li>
     * <li>1 lowercase letter</li>
     * <li>1 digit</li>
     * <li>1 special character</li>
     * </ul>
     * 
     * @param nbCar The desired length of the password (minimum 4).
     * @return A randomly generated password that meets the security criteria.
     */

    public static String generatePassword(int nbCar) {
        SecureRandom random = new SecureRandom();

        int nb_upper = random.nextInt(nbCar - 3) + 1; // Au moins 1 majuscule
        int nb_lower = random.nextInt(nbCar - nb_upper - 2) + 1;
        int nb_digit = random.nextInt(nbCar - nb_upper - nb_lower - 1) + 1;
        int nb_special = nbCar - nb_upper - nb_lower - nb_digit;

        // Listes des caractères possibles
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

        StringBuilder password = new StringBuilder();

        // Ajouter les majuscules
        for (int i = 0; i < nb_upper; i++) {
            password.append(upperChars.charAt(random.nextInt(upperChars.length())));
        }

        // Ajouter les minuscules
        for (int i = 0; i < nb_lower; i++) {
            password.append(lowerChars.charAt(random.nextInt(lowerChars.length())));
        }

        // Ajouter les chiffres
        for (int i = 0; i < nb_digit; i++) {
            password.append(digits.charAt(random.nextInt(digits.length())));
        }

        // Ajouter les caractères spéciaux
        for (int i = 0; i < nb_special; i++) {
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        }

        // Il faudrait rajouter une fonction pour mélanger le mdp
        String finalPassword = password.toString();
        return finalPassword;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            // No arguments provided, running all questions
            for (int i = 1; i <= 4; i++)
                runQuestion(String.valueOf(i));
        } else {
            for (String arg : args) {
                runQuestion(arg);
            }
        }
    }

    private static void runQuestion(String questionNumber) {

        System.out.println("\nQ" + questionNumber + "\n" + "-".repeat(20));
        switch (questionNumber) {
            case "1":
                String HashedPwd = "a97755204f392b4d8787b38d898671839b4a770a864e52862055cdbdf5bc5bee";
                String bruteForcedPwd = bruteForce6Digit(HashedPwd);
                System.out.println(bruteForcedPwd != null ? bruteForcedPwd : "No result found");
                break;

            case "2":
                System.out.println("Abc5          -> " + isStrongPassword("1234"));
                System.out.println("abcdef123456  -> " + isStrongPassword("abcdef123456"));
                System.out.println("AbCdEf123456  -> " + isStrongPassword("AbCdEf123456"));
                System.out.println("AbCdEf 123456 -> " + isStrongPassword("AbCdEf 123456"));
                break;

            case "3":
                ArrayList<String> passwords = new ArrayList<>(
                        List.of("Abc5", "abcdef123456", "AbCdEf123456", "AbCdEf 123456"));
                HashMap<String, Boolean> password_map = checkPasswordsList(passwords);

                if (password_map != null) {
                    for (Map.Entry<String, Boolean> entry : password_map.entrySet()) {
                        System.out.println(entry.getKey() + " -> " + entry.getValue());
                    }
                }
                break;

            case "4":
                System.out.println("Generated password: " + generatePassword(12));
                break;

            default:
                System.out.println("Invalid question number: " + questionNumber);
        }
    }

}
