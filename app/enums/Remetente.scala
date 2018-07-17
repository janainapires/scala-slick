package enums

import slick.jdbc.PostgresProfile

object Remetente extends Enumeration {

  type Remetente = Value

  val DIARIOOFICIAL = Value(1)
  val TRAMITA = Value(2)
  val ACESSOAINFORMACAO = Value(3)

  import slick.jdbc.PostgresProfile.api.intColumnType

  implicit val remetenteColumnType = PostgresProfile.MappedColumnType.base[Remetente, Int](
    r => r.id,
    rt => Remetente(rt)
  )
}








