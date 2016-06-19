import java.util.Random;

public class Jar {

    private String mItemType;
    private int mMaxAmountPerJar;
    private int mCurrentAmountInJar;

    public Jar(String itemType, int amountPerJar){

        mItemType        = itemType;
        mMaxAmountPerJar = amountPerJar;

        System.out.printf("New item-type has been set: %d \"%s\" will fit in a Jar!\n", mMaxAmountPerJar, mItemType);

    }

    public int getMaxAmountPerJar(){
        return mMaxAmountPerJar;
    }

    public String getItemType(){
        return mItemType;
    }

    public int getCurrentAmountInJar() {
        return mCurrentAmountInJar;
    }

    public void setCurrentAmountInJar() {
        Random random = new Random();
        mCurrentAmountInJar = random.nextInt(getMaxAmountPerJar()) + 1; // add 1 because of Java-idx starting at 0
    }

}
