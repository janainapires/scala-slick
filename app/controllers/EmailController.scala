package controllers

import java.sql.Timestamp

import enums.{Remetente, Situacao}
import enums.Remetente.Remetente
import enums.Situacao.Situacao
import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


class EmailController @Inject()(repo: EmailRepository,
                                 cc: MessagesControllerComponents
                                )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {
  /**
   * The mapping for the person form.
   */
  val emailForm: Form[CreateEmailForm] = Form {
    mapping(
      "id" -> longNumber,
      "cancelledstatus" -> boolean,
      "assunto"  -> text,
      "conteudo" -> text,
      "errormg" -> text,
      "datacadastro" -> optional(sqlTimestamp("dd-MM-yyyy hh:mm:ss")),
      "dataenvio" -> optional(sqlTimestamp("dd-MM-yyyy hh:mm:ss")),
      "situacao" -> number,
      "usuariointerno" -> longNumber,
      "remetente" -> number,
      "tramitavel" -> longNumber,
      "interessado" -> longNumber,
      "usuarioenvio" -> longNumber,
      "email" -> email,
      "evento" -> text,
      "codigorastreamento" -> text,
      "dataleiturarastreamento" -> optional(sqlTimestamp("dd-MM-yyyy hh:mm:ss")),
      "dadosrastreamento" -> text
    )(CreateEmailForm.applyEmail)(CreateEmailForm.unapplyEmail)
  }


//  def formularioEmail = Action { implicit request =>
//    Ok(views.html.formularioEmail(emailForm))
//  }

//  def addEmail = Action.async { implicit request =>
//    emailForm.bindFromRequest.fold(errorForm => {
//        Future.successful(Ok(views.html.formularioEmail(errorForm)))
//      },
//      email => {repo.create(email.getEmail)
//        //  Redirect(routes.EmailController.formularioEmail.flashing("success" -> "email.created")
//        Future.successful(Ok(""))
//      }
//    )
//  }

//  def getEmails = Action.async { implicit request =>
//     repo.list().map { email =>
//        Ok(Json.toJson(email))
//     }
//   }
}

case class CreateEmailForm(id: Long,
                           cancelledstatus: Boolean,
                           assunto: String,
                           conteudo: String,
                           errormg: String,
                           datacadastro: Option[Timestamp],
                           dataenvio: Option[Timestamp],
                           situacao: Int,
                           usuariointerno: Long,
                           remetente: Int,
                           tramitavel: Long,
                           interessado: Long,
                           usuarioenvio: Long,
                           email: String,
                           evento: String,
                           codigorastreamento: String,
                           dataleiturarastreamento: Option[Timestamp],
                           dadosrastreamento: String) {
  def getEmail: Email = {
    new Email(id, cancelledstatus, assunto, conteudo, errormg, datacadastro, dataenvio, Situacao(situacao), usuariointerno, Remetente(remetente), tramitavel, interessado, usuarioenvio, email, evento,
      codigorastreamento, dataleiturarastreamento, dadosrastreamento)
  }
}

object CreateEmailForm{
  def unapplyEmail:
  CreateEmailForm => Option[(Long, Boolean,String, String, String, Option[Timestamp],Option[Timestamp],Int, Long, Int,Long, Long, Long,
    String, String, String, Option[Timestamp], String )] =
    email => {
      Option(
        (email.id,
          email.cancelledstatus,
          email.assunto,
          email.conteudo,
          email.errormg,
          email.datacadastro,
          email.dataenvio,
          email.situacao,
          email.usuariointerno,
          email.remetente,
          email.tramitavel,
          email.interessado,
          email.usuarioenvio,
          email.email,
          email.evento,
          email.codigorastreamento,
          email.dataleiturarastreamento,
          email.dadosrastreamento
        )
      )
    }

  def applyEmail(id: Long,
                 cancelledstatus: Boolean,
                 assunto: String,
                 conteudo: String,
                 errormg: String,
                 datacadastro: Option[Timestamp],
                 dataenvio: Option[Timestamp],
                 situacao: Int,
                 usuariointerno: Long,
                 remetente: Int,
                 tramitavel: Long,
                 interessado: Long,
                 usuarioenvio: Long,
                 email: String,
                 evento: String,
                 codigorastreamento: String,
                 dataleiturarastreamento: Option[Timestamp],
                 dadosrastreamento: String)  : CreateEmailForm =
    CreateEmailForm.apply(
      id,
      cancelledstatus,
      assunto,
      conteudo,
      errormg,
      datacadastro,
      dataenvio,
      situacao,
      usuariointerno,
      remetente,
      tramitavel,
      interessado,
      usuarioenvio,
      email,
      evento,
      codigorastreamento,
      dataleiturarastreamento,
      dadosrastreamento
    )
}


