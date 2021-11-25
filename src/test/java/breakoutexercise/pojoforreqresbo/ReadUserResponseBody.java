package breakoutexercise.pojoforreqresbo;

public class ReadUserResponseBody {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void printUserCreationResponseBody(){
        System.out.println("The response is :\n\n"+"name : " + getName()+"\n"+
                "job : " +getJob()+"\n"+
                "id : " +getId()+"\n"+
                "created : " +getCreatedAt()+"\n");
    }
}
