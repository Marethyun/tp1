package fr.univ_amu.iut.exercice6;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static fr.univ_amu.iut.exercice6.ArgsException.ErrorCode.*;

public class Args {

    private LinkedHashMap<Character, String> parsedSchema = new LinkedHashMap<>();
    private LinkedHashMap<Character, Object> parsedArguments = new LinkedHashMap<>();
    private int nextArgument;

    public Args(String schema, String[] args) throws ArgsException {
        parseSchema(schema);
        parseArgumentStrings(args);

        for (int i = 0; i < args.length; i++) {
            if (!isFlag(args[i])) {
                nextArgument = i + 1;
                break;
            }
        }
    }

    public boolean has(char arg) {
        return parsedArguments.containsKey(arg);
    }

    public int nextArgument() {
        return nextArgument;
    }

    public boolean getBoolean(char arg) {
        return (Boolean) parsedArguments.get(arg);
    }

    public String getString(char arg) {
        return (String) parsedArguments.get(arg);
    }

    public int getInt(char arg) {
        return (Integer) parsedArguments.get(arg);
    }

    public double getDouble(char arg) {
        return (Double) parsedArguments.get(arg);
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private void parseSchemaElement(String element) throws ArgsException {
        if (element.isEmpty()) throw new ArgsException("Element cannot be empty.");

        char letter = element.charAt(0);
        validateSchemaElementId(letter);

        if (element.length() == 1) {
            this.parsedSchema.put(letter, "");
        }

        this.parsedSchema.put(letter, element.length() == 1 ? "" : element.substring(1));
    }

    private void parseArgumentStrings(String[] argsList) throws ArgsException {
        for (int i = 0; i < argsList.length; ++i) {
            String s = argsList[i];

            if (isFlag(s)) {
                char letter = s.charAt(1);

                if (!parsedSchema.containsKey(letter)) throw new ArgsException("Unrecognized flag");

                String type = parsedSchema.get(letter);

                switch (type) {
                    case "":
                        parsedArguments.put(letter, true);
                        break;

                    case "*":
                        if (i + 1 < argsList.length && !isFlag(argsList[i + 1])) {
                            parsedArguments.put(letter, argsList[i + 1]);
                            ++i;
                        }
                        else throw new ArgsException(MISSING_STRING, letter, null);
                        break;

                    case "#":
                        if (i + 1 < argsList.length && !isFlag(argsList[i + 1])) {
                            parsedArguments.put(letter, Integer.parseInt(argsList[i + 1]));
                            ++i;
                        }
                        else throw new ArgsException(MISSING_INTEGER, letter, null);
                        break;
                    case "##":
                        int a = i + 1;
                        boolean b = isFlag(argsList[i + 1]);
                        if (i + 1 < argsList.length && !isFlag(argsList[i + 1])) {
                            parsedArguments.put(letter, Double.parseDouble(argsList[i + 1]));
                            ++i;
                        }
                        else throw new ArgsException(MISSING_DOUBLE, letter, null);
                        break;
                    case "[*]":
                        ArrayList<String> params = new ArrayList<>();
                        for (int j = i + 1; j < argsList.length; j++) {
                            if (isFlag(argsList[j])) {
                                i += j;
                                break;
                            }

                            params.add(argsList[j]);
                        }
                        parsedArguments.put(letter, params);
                        break;
                }
            }
        }
    }

    private boolean isFlag(String s) {
        return s.length() == 2 && s.charAt(0) == '-' && Character.isLetter(s.charAt(1));
    }

    public static void main(String[] args) throws ArgsException {
        Args arg = new Args("x##", new String[]{"-x", "42.3"});
    }
}
