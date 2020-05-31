package entity;

public class ComboboxItem {
    public String Text;
    public int Value;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    @Override
    public String toString() {
        return Text;
    }
}
