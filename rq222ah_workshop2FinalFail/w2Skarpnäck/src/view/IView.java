package view;

public interface IView {

    void startPageHeader();

    void addBoatHeader();

    void editBoatHeader();

    void deleteBoatHeader();

    void memberInfoHeader(int id);

    void addConfirmation(String in);

    void deleteConfirmation(String in);

    void addBoatConfirmation(String in);

    void editBoatConfirmation(String in);

    void editBoatMessage(String old, String changed);

    void deleteBoatConfiramtion(String in);

    void errorMessage(String str);

    void outputMessage(String str);
}