package com.kreitek.refactor.mal;

class  Main
{
    public static void main(String args[])
    {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

        DocumentValidator doc = DocumentValidator.getInstance();

        System.out.println(doc.DNIE("11111111H"));
        System.out.println(doc.DNIE("24324356A"));
        System.out.println(doc.DNIE("X0932707B"));
        System.out.println(doc.DNIE("Z2691139Z"));
        System.out.println(doc.CIF("W9696294I"));
        System.out.println(doc.CIF("W9696294A"));
    }
}
