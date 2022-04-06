package com.kreitek.refactor.mal;

class  Main
{
    public static void main(String args[])
    {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

        DocumentValidator doc = DocumentValidator.getInstance();

        // creamos un DNI correcto
        DOCUMENTYPE.DNINIE.DocumentValidator(doc, "11111111H", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

        DOCUMENTYPE.DNINIE.DocumentValidator(doc, "24324356A", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

        DOCUMENTYPE.DNINIE.DocumentValidator(doc, "X0932707B", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

        DOCUMENTYPE.DNINIE.DocumentValidator(doc, "Z2691139Z", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

        DOCUMENTYPE.CIF.DocumentValidator(doc, "W9696294I", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

        DOCUMENTYPE.CIF.DocumentValidator(doc, "W9696294A", null);
        System.out.println( "DNI " + doc.codigo + " es: " + (doc.validaDocuments() == 1));

    }
}