package co.clean_architecture.model.user;

public class Password {


    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
