package enums

import slick.jdbc.PostgresProfile


object Situacao extends Enumeration {

  type Situacao = Value

  val PENDENTE = Value(1, "Pendente")
  val ENVIADO = Value(2, "Enviado")
  val ERRO = Value(3, "Erro")


  import slick.jdbc.PostgresProfile.api.intColumnType

  implicit val situacaoColumnType = PostgresProfile.MappedColumnType.base[Situacao, Int](
    s => s.id,
    st => Situacao(st)
  )
  override def toString = Situacao.Value.toString

}
















