package com.digytal.control.infra.http.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "Menssagem da resposta")
public class ResponseMessage {
    public static final String R200 = "Solicitação bem-sucedida!";
    public static final String R201 = "Registro inserido com sucesso!";
    public static final String R204 = "Registro não localizado na base de dados!";
    public static final String R400 = "Registro inválido!";
    public static final String R401 = "Você não está autenticado ou não possui permissões adequadas para acessar o recurso!";
    public static final String R403 = "Você está autenticado, mas não possui permissões adequadas para acessar o recurso solicitado!";
    public static final String R404 = "Registro não encontrado!";
    public static final String R409 = "Mensagens relacionadas as validações com base nas regras definidas";
    public static final String R500 = "Erro de servidor, contacte o suporte técnico";

    public static String inclusao(String entidade) {
        return String.format("%s inserido(a) com sucesso!", entidade);
    }
    public static String busca(String entidade) {
        return String.format("Solicitação de busca em %s bem-sucedida(a)!", entidade);
    }
    public static String alteracao(String entidade) {
        return String.format("Registro de %s alterado com sucesso!", entidade);
    }
    public static String consulta(String entidade) {
        return String.format("Solicitação de consulta em %s bem-sucedida(a)!", entidade);
    }
    public static String listagem(String entidade) {
        return String.format("Solicitação de listagem em %s bem-sucedida(a)!", entidade);
    }

    public static String exclusao(String entidade) {
        return String.format("Registro de %s deletado com sucesso!", entidade);
    }

    //inclusao, alteracao, exclusao, impressao, consulta, busca, listagem, visualizacao
}
