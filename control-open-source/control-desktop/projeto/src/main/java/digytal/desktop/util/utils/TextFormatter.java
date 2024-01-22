package digytal.desktop.util.utils;

public class TextFormatter {
    public static String substring(String string, int maxLength) {
        return string.substring(0, Math.min(maxLength, string.length()));
    }

    public static String complete(String string, String symbol, int maxLength) {
        String replace = replace(symbol,maxLength);
        return substring(string.concat(replace), maxLength);
    }
    public static String hide(String string, String symbol, int chars) {
        int half = (chars/2);
        String replace = replace(symbol,half);
        String text = replace.concat(string.substring(half, half+string.length()-chars)).concat(replace);
        return text;
    }
    private static String replace(String symbol, int size){
        String text = String.format("%0" + size + "d", 0);
        text = text.replace("0", symbol);
        return text;
    }
    public static String complete(String string, int maxLength) {
        return complete(string," ", maxLength);
    }
    public static String cpfCnpj(String cpfCnpj) {
    	if(cpfCnpj.length() ==11)
    		cpfCnpj = cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    	else
    		cpfCnpj = cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    	return cpfCnpj;
    }
    public static void main(String[] args) {
		System.out.println(cpfCnpj("10431245000127"));
	}

}
