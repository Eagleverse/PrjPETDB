/*

 */
package petDB;

/**
 *
 * @author kaifa
 */
public class Pet {
  private String name;
  private String age;
  private int ID;

  public Pet(String pet_name, String pet_age, int pet_ID) {
    name = pet_name;
    age = pet_age;
    ID = pet_ID;
  }

  public String getName() {
    return name;
  }

  public String getAge() {
    return age;
  }
  public int getID(){
      return ID;
  }
  public void setName(String new_name) {
    name = new_name;
  }

  public void setAge(String new_age) {
    age = new_age;
  }
  public void setID(int new_ID) {
    ID = new_ID;
  }
}