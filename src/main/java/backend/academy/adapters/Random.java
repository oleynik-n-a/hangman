package backend.academy.adapters;

import java.security.SecureRandom;

public interface Random {
    static int generateRandomInteger(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt((max - min) + 1) + min;
    }
}
