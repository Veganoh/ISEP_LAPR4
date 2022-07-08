package eapli.base.productmanagement.domain;

import java.util.regex.Pattern;

public enum CodingStandard {

    EAN13("[0-9]{13}"),UPC("[0-9]{12}");

    private final String standard;

    private CodingStandard(String standard) {
        this.standard = standard;
    }

    public static boolean validate(String barcode, CodingStandard code){
        return Pattern.matches(code.standard, barcode);
    }
}
