package View;

import java.util.HashMap;

public interface ILanguageView {
    HashMap<LangStringID, String> loadEnglishStrings();

    HashMap<LangStringID, String> loadSwedishStrings();
}
