package models

import javax.inject.{Inject, Singleton}
import java.sql.Timestamp

import enums.{Remetente, Situacao}
import enums.Remetente.Remetente
import enums.Situacao.Situacao
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class EmailRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val tableEmail = TableQuery[EmailTable]


  def list(): Future[Seq[Email]] = db.run {
    tableEmail.result
  }

  private class EmailTable(tag: Tag) extends Table[Email](tag, "email") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def cancelledstatus = column[Boolean]("cancelledstatus")
    def assunto = column[String]("assunto")
    def conteudo = column[String]("conteudo")
    def errormsg = column[String]("errormsg")
    def datacadastro = column[Option[Timestamp]]("datacadastro")
    def dataenvio = column[Option[Timestamp]]("dataenvio")
    def situacao = column[Situacao]("situacao")(Situacao.situacaoColumnType)
    def usuariointerno = column[Long]("usuariointerno")
    def remetente = column[Remetente]("remetente")(Remetente.remetenteColumnType)
    def tramitavel = column[Long]("tramitavel")
    def interessado = column[Long]("interessado")
    def usuarioenvio = column[Long]("usuarioenvio")

    def email = column[String]("email")
    def evento = column[String]("evento")
    def codigorastreamento = column[String]("codigorastreamento")
    def dataleiturarastreamento = column[Option[Timestamp]]("dataleiturarastreamento")
    def dadosrastreamento = column[String]("dadosrastreamento")

        override def * = (
          id,
          cancelledstatus,
          assunto,
          conteudo,
          errormsg,
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
          dadosrastreamento) <> ((Email.apply _).tupled, Email.unapply)

  }


  def create(email: Email): Unit = db.run {
    (tableEmail.map(e => (e.cancelledstatus, e.assunto, e.conteudo, e.errormsg, e.datacadastro, e.dataenvio, e.situacao, e.usuariointerno, e.remetente, e.tramitavel, e.interessado,
                           e.usuarioenvio, e.email, e.evento, e.codigorastreamento, e.dataleiturarastreamento, e.dadosrastreamento))
      returning tableEmail.map(_.id)
      into ((fields, id) => Email(id, fields._1, fields._2, fields._3, fields._4, fields._5, fields._6, fields._7, fields._8, fields._9, fields._10, fields._11, fields._12, fields._13, fields._14, fields._15, fields._16, fields._17))
      ) += (email.cancelledstatus, email.assunto, email.conteudo, email.errormg, email.datacadastro, email.dataenvio, email.situacao, email.usuariointerno,
              email.remetente, email.tramitavel, email.interessado, email.usuarioenvio, email.email, email.evento, email.codigorastreamento, email.dataleiturarastreamento, email.dadosrastreamento )
  }


}
