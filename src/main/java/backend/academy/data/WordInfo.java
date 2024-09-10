package backend.academy.data;

import backend.academy.game_states.WordCategoryOption;

public record WordInfo(String word, WordCategoryOption wordCategory, String hint) {
}
