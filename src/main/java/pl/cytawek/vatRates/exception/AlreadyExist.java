package pl.cytawek.vatRates.exception;

public class AlreadyExist extends Exception {
    String message = "Record for this country already exist in DB";

    public AlreadyExist(String message){
        super(message);
    }
}