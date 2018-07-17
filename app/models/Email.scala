package models

import java.sql.Timestamp
import java.time.LocalDateTime

import enums.Remetente.Remetente
import enums.Situacao.Situacao

case class Email (id: Long,
             cancelledstatus: Boolean,
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

//  def this(id: Long,
//           cancelledstatus: Boolean,
//           assunto: String,
//           conteudo: String,
//           errormg: String,
//           datacadastro: Timestamp,
//           dataenvio: Timestamp,
//           situacao: Situacao)
//         = this(id, false, assunto, conteudo, errormg, datacadastro, dataenvio, situacao,
//          null, null, null, null, null, null, null, null, None, null)


}




//object Email {
//
//  implicit val emailFormat = Json.format[Email]
//
//}

