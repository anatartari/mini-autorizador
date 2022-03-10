package com.solutis.miniautorizador.utils;

public enum ValidacoesEnum {
    SALDO_INSUFICIENTE{
        @Override
        public String getMensagemDeErro() {
            return "SALDO_INSUFICIENTE";
        }
    },
    SENHA_INVALIDA {
        @Override
        public String getMensagemDeErro() {
            return "SENHA_INVALIDA";
        }
    },
    CARTAO_INEXISTENTE {
        @Override
        public String getMensagemDeErro() {
            return "CARTAO_INEXISTENTE ";
        }
    },
    CARTAO_EXISTENTE {
        @Override
        public String getMensagemDeErro() {
            return null;
        }
    },
    CLIENTE_EXISTENTE{
        @Override
        public String getMensagemDeErro() {
            return "Cliente existente";
        }
    };

    public abstract String getMensagemDeErro();
}
