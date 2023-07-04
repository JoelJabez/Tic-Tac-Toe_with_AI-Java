class Util {
    static int index = 0;
    static String source;

    public static int indexOf(String src, String tgt) {
        if (index == 0) {
            source = src;
        }

        if (source.length() == index) {
            return -1;
        }

        if (src.startsWith(tgt)) {
            return index;
        }

        index++;
        return indexOf(src.substring(1), tgt);
    }
}
