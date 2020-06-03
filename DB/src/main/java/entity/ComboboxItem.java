package entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComboboxItem)) return false;
        ComboboxItem that = (ComboboxItem) o;
        return Value == that.Value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Value);
    }
}
