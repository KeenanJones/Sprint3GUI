package software_masters.planner_networking;

public class tableComment
{
	
    private String firstName = null;
    private String lastName = null;

    public tableComment() {
    }

    public tableComment(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
