package com.kreitek.refactor.mal;

import java.util.Date;

public enum DOCUMENTYPE {
    DNINIE,
    CIF,;

    public void DocumentValidator(DocumentValidator documentValidator, String codigo, Date fchValidez) {
        documentValidator.enumTipo = this;           // tipo de documento
        documentValidator.codigo = codigo;           // asignamos el DNI
        documentValidator.fchValidez = fchValidez;   // asignamos la fecha de validez
    } 
}