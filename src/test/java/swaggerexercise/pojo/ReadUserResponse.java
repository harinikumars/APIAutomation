package swaggerexercise.pojo;

public class ReadUserResponse {
    private int code;
    private String type;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printUserCreationResponseBody(){
        System.out.println("The response is :\n\n"+"Type : " + getType()+"\n"+
                "Code : " +getCode()+"\n"+
                "Message : " +getMessage()+"\n");
    }
}
