import java.util.*;
import java.lang.*;

class AsciiCharSequence implements CharSequence {
    byte[] string;

    AsciiCharSequence(byte[] string) {
        this.string = string;
    }

    @Override
    public int length() {
        return string.length;
    }

    @Override
        public char charAt(int index) {
        return (char) string[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        byte[] sequence = Arrays.copyOfRange(string, start, end);
        return new AsciiCharSequence(sequence);
    }

    @Override
    public String toString() {
    String sequence = "";
    for (byte b : string) {
        sequence += (char) b;
    }

	return sequence;
    }
}
