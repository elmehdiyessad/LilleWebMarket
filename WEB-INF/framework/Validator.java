package framework;


import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;



public class Validator
{
    private HashMap<String, String> errors;



    public Validator()
    {
        errors = new HashMap<String, String>();
    }



    public boolean isValid()
    {
        return errors.isEmpty();
    }

    public void addError(String fieldName, String errorMsg)
    {
        errors.put(fieldName.replaceAll("(.)([A-Z])", "$1_$2").toLowerCase(), errorMsg);
    }

    public boolean hasError(String fieldName)
    {
        return errors.get(fieldName) != null;
    }

    public String getError(String fieldName)
    {
        return errors.get(fieldName);
    }



    public static boolean isBlank(Object o)
    {
        return o == null || o.toString().trim().length() <= 0;
    }

    public static boolean hasMinLength(String s, int minLength)
    {
        return s.trim().length() >= minLength;
    }

    public static boolean hasMaxLength(String s, int maxLength)
    {
        return s.trim().length() <= maxLength;
    }
}