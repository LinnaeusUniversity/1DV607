package Model.Boats;

import Model.LanguageType;

public enum BoatType {
    MOTORSAILER,
    SAILER,
    KAJAK,
    OTHER;


    public static String[] getNames(LanguageType lang) {
        String[] namesSWe = {"Segelboat med motor", "Segelboat", "Kajak", "Annan"};
        String[] namesEng = {"Motor Sailer", "Sailer", "Kayak", "Other"};

        if (lang == LanguageType.ENGLISH)
            return namesEng;
        else
            return namesSWe;
    }
}
