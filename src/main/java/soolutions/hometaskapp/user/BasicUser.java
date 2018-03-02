package soolutions.hometaskapp.user;

public class BasicUser implements User {
  private final String name;

  public BasicUser() {
    this("");
  }

  public BasicUser(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
