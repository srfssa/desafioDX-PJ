package br.com.duxusdesafio.model.enums;

public enum InfoMessageEnum {

    MSG_REGISTRO_PERIODO("Não há registros para o período selecionado"),
    MSG_REGISTRO_DATA("Não há registros para a data selecionada"),
    MSG_REGISTRO_TIME("Selecione ao menos um integrante");

    public String label;

    InfoMessageEnum(String label) { this.label = label; }
}
