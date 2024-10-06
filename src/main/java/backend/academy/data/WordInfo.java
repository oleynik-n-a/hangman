package backend.academy.data;

import backend.academy.stages.category.WordCategoryOption;

public record WordInfo(String word, WordCategoryOption wordCategory, String hint) {
}
