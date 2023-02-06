
public class Pet {

    private String name;
    private double age;
    private Type type;

    public Pet(String name, double age, Type type) {
        this.name = name;
        this.age = age;
        this.type = type;
        // data validation?
    }

    @Override
    public String toString() {
        return name + " is a " + type.toString().toLowerCase() + " age " + (int) age;
    }

    public static void main(String[] args) {
        Pet MyPets[] = new Pet[4];

        MyPets[0] = new Pet("Toby", 17, Type.Dog);
        MyPets[1] = new Pet("MooMoo", 2, Type.Cat);
        MyPets[2] = new Pet("Horangi", 13, Type.Tiger);
        MyPets[3] = new Pet("Tyler", 22, Type.Bear);

        for (Pet pet : MyPets) {
            System.out.println(pet);
        }

    }

}
