package models

import java.sql.Timestamp

import enums.Remetente.Remetente
import enums.Situacao.Situacao

case class Email (id: Long,
             cancelledstatus: Boolean = false,
             assunto: String,
             conteudo: String,
             errormg: String,
             datacadastro:  Option[Timestamp],
             dataenvio:  Option[Timestamp],
             situacao: Situacao,
             usuariointerno: Long,
             remetente: Remetente,
             tramitavel: Long,
             interessado: Long,
             usuarioenvio: Long,
             email: String,
             evento: String,
             codigorastreamento: String,
             dataleiturarastreamento:  Option[Timestamp],
             dadosrastreamento: String ){

}




//object Email {
//
//  implicit val emailFormat = Json.format[Email]
//
//}

