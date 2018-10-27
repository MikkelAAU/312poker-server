package Main_Package;

public class HandResult {

    private int position;
    private int value;

    public HandResult(int position, int value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

