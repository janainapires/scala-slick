package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class PersonRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._


  private class PeopleTable(tag: Tag) extends Table[Person](tag, "people") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def age = column[Int]("age")

    def * = (id, name, age) <> ((Person.apply _).tupled, Person.unapply)
  }

  private val personTable = TableQuery[PeopleTable]


  def create(name: String, age: Int): Future[Person] = db.run {
    (personTable.map(p => (p.name, p.age))
      returning personTable.map(_.id)
      into ((fields, id) => Person(id, fields._1, fields._2))
      ) += (name, age)
  }


  def list(): Future[Seq[Person]] = db.run {
    personTable.result
  }
}
