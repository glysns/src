package com.digytal.control.infra.business;

import org.springframework.http.HttpStatus;

public enum BusinessMessage {
    E100("100", "Usuário ou senha inválida","Verifique se os campos foram digitados corretamente"),
    E101("101", "Campo obrigatório: %s","Preencha o campo obrigatório"),
    E102("102", "Já existe um registro com %s igual a(o) %s","O registro deve ser único"),
    E404("404", "Não existe um(a) %s com o(a) %s informado(a)","O registro deve existir previamente"){
        @Override
        public int getHttpStatus() {
            return HttpStatus.NOT_FOUND.value();
        }
    },
    E204("204", "Consulta sem registros","Realize uma operação de cadastro"){
        @Override
        public int getHttpStatus() {
            return HttpStatus.OK.value();
        }
    },
    E106("106", "Registro(s) não localizado(s)","Realize a gravação de um novo registro"),
    E107("107", "Consulta sem resultado","Realize a gravação de um novo registro"){
        @Override
        public int getHttpStatus() {
            return HttpStatus.NO_CONTENT.value();
        }
    },
    E108("108", "Senha expirada","É necessário você alterar a senha"),
    E109("109", "Usuário bloqueado","Favor entre em contato com o administrador do sistema"),
    E110("110", "Usuário desativado","Favor entre em contato com o administrador do sistema"),
    E111("111", "A nova senha e a senha de confirmação não conferem","Confira a nova senha com a senha de confirmação"),
    E112("112", "A nova senha atual não pode ser igual a nova senha","Informe uma nova senha diferente da atual"),
    E113("113", "Não foi possível remover o registro","É possível que haja uma dependência relacional no banco de dados"),
    E114("114", "Preenchimento inválido do CPF ou CNPJ","Revise os números digitados para o preenchimento do CPF ou CNPJ"),
    E115("115", "Preenchimento inválido do E-mail","Revise o formato válido de e-mails"),
    E116("116", "O campo %s não contem o tamanho mínimo de %s caracteres","Preencha o campo com a quantidade mínima de caracteres"),
    E117("117", "O campo %s ultrapassa o tamanho máximo de %s caracteres","Preencha o campo com a quantidade máxima de caracteres"),
    E118("118", "O campo %s não contem o tamanho mínimo de %s e máximo de %s caracteres","Preencha o campo com a quantidade mínima e máxima de caracteres"),
    E119("119", "O campo %s não pode ser alterado","Contacte o administrador do sistema"),
    E120("120", "Usuário não possui empresa associada","Favor entre em contato com o administrador do sistema"),
    E121("121", "Lançamento estornado não permite uma nova operação de estorno","Selecione outro lançamento"),
    E122("122", "CPF/CNPJ Inválido","Revise os dígitos de seu CPF/CNPJ"),
    E123("123", "Ops! não te localizei","Revise seu e-mail e senha por favor"),
    E900("900", "Token inválido ou expirado","Realize uma nova autenticação"),
    E124("124", "Cadastro incompleto, %s","Complete o cadastro conforme especificações cadastrais"),
    E125("125", "O campo %s não pode ser negativo","Informe um número de valor positivo superior ou igual à zero"),
    E126("126", "Saldo insuficiente","Realize um lançamento tipo RECEITA na conta selecionada"),
    E127("127", "%s","O campo precisa atender aos requisitos de negócio"),
    E128("128", "%s","Contacte o administrator do sistema"),
    E129("129", "Não existe uma conta padrão para esta empresa","Defina uma conta padrão para esta empresa"),
    E130("130", "Não existe uma configuração forma de pagamento do tipo: %s","Confira as configurações de contas e forma de pagamento da empresa"),
    E131("131", "%s","Consulte as definições de integração das aplicações ou contacte o time de suporte"),
    E132("132", "O(s) parâmetros(s) %s são obrigatórios","O(s) parâmetros(s) precisa(m) atender aos requisitos de negócio"),
    E133("133", "%s","Organização ou Empresa não encontrada em nossa base de dados"),
    E134("134", "%s","Marca não encontrada em nossa base de dados"),
    E135("135", "O campo %s não pode ser igual à Zero (0)","Informe um número de valor positivo superior à zero"),
    E501("501", "Erro ao tentar acessar o recurso","Contacte o Suporte Técnico"){

        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    E502("502", "Método não implementado","Contacte o Suporte Técnico"){
        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    E500("500", "Erro não mapeado","Contacte o Suporte Técnico"){

        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    ;
    private final String code;
    private final String message;
    private final String suggestion;

    private int httpStatus;

    private BusinessMessage(String code, String mensagem, String suggestion) {
        this.code = code;
        this.message = mensagem;
        this.suggestion = suggestion;
    }

    public int getHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
