import com.personal.dragonballuniverse.model.Fighter.{Mission, Warrior}
import com.personal.dragonballuniverse.model.Team.FighterGroup
import org.scalatest.FlatSpec

class SecondCaseTest extends FlatSpec with SkillsTest {

  it should "The team is assigned to a group of missions and fails" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))
    val team = FighterGroup("Fighters", goku, krilin)

    val freezerM : Mission = Mission("Detener a freezer", 250, 420)
    val gohanM : Mission = Mission("Salvar a Gohan", 250, 420)
    val missions = FighterGroup("Missions", freezerM, gohanM)

    assert(team.attack(missions).isLeft)
  }

  it should "The team is assigned to a group of missions and completes" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))

    val team = FighterGroup("Fighters", goku, krilin, piccolo)

    val freezerM : Mission = Mission("Detener a freezer", 250, 420)
    val gohanM : Mission = Mission("Salvar a Gohan", 150, 100)
    val missions = FighterGroup("Missions", freezerM, gohanM)

    assert(team.attack(missions).isRight)
  }

}
