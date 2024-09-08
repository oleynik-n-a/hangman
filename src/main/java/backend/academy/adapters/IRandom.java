package backend.academy.adapters;

import java.security.SecureRandom;

public interface IRandom {
    static int generateRandomInteger(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt((max - min) + 1) + min;
    }
}
