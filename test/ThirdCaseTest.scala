import com.personal.dragonballuniverse.model.Fighter.{Fighter, Mission, Warrior}
import com.personal.dragonballuniverse.model.Team.FighterGroup
import org.scalatest.FlatSpec

class ThirdCaseTest extends FlatSpec with SkillsTest {

  it should "Competition between teams" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))

    val teamGoku = FighterGroup("Goku", goku)
    val teamPiccolo = FighterGroup("Fighters", piccolo, krilin)

    val mission : Mission = Mission("Detener a freezer", 150, 100)

    assert(compareTeams(List(teamGoku, teamPiccolo), mission).isRight)
  }

  /**
    * This function calculate the best team to perform a mission. The list accept more than two team to be evaluated
    * @param teams
    * @param mission
    * @return
    */
  def compareTeams(teams : List[FighterGroup], mission: Fighter): Either[Fighter, Fighter] = teams.map(_.attack(mission)).reduce((a, b) => (a, b) match {
    case (x @ Right(f), y @ Right(s)) => checkComparisonConditions(teams, x, y, List(x => (teams.find(_.id == f.id).map(_.members).getOrElse(0) - f.members) - (teams.find(_.id == s.id).map(_.members).getOrElse(0) - s.members), y => f.health - s.health))
    case (x @ Right(_), _) => x
    case (_, y @Right(_)) => y
    case (_, _) => Left(FighterGroup())
  })

  def checkComparisonConditions(teams: List[FighterGroup], f: Right[Fighter, Fighter], s: Right[Fighter, Fighter], conditions: List[Any => Int]) : Either[Fighter, Fighter] = conditions match {
    case Nil => throw new RuntimeException("Error in checkComparisonConditions")
    case x :: xs => x.apply() match {
      case res if res > 0 => f
      case res if res < 0 => s
      case res if res == 0 => checkComparisonConditions(teams, f, s, xs)
    }
  }

}
