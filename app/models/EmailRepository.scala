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
    DBIO.seq(tableEmail +=(email))
  }


}
