package main;

import java.util.HashSet;
import java.util.Set;

public class VirtualProductCodeManager {
    private static VirtualProductCodeManager instance;
    private final Set<String> usedCodes;

    private VirtualProductCodeManager() {
        usedCodes = new HashSet<>();
    }

    public static void useCode(String code) {
        getInstance().usedCodes.add(code);
    }

    public static boolean isCodeUsed(String code) {
        return getInstance().usedCodes.contains(code);
    }

    private static VirtualProductCodeManager getInstance() {
        if (instance == null) {
            instance = new VirtualProductCodeManager();
        }
        return instance;
    }
}
