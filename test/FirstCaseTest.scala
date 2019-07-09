import com.personal.dragonballuniverse.model.Fighter.{Mission, Warrior}
import com.personal.dragonballuniverse.model.Team.FighterGroup
import org.scalatest.FlatSpec

class FirstCaseTest extends FlatSpec with SkillsTest {

  it should "The mission is incomplete" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val team = FighterGroup("Krilin", krilin)

    val mission : Mission = Mission("Detener a freezer", 250, 420)

    assert(team.attack(mission).isLeft)
  }

  it should "The team completes the mission" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))

    val team = FighterGroup("Fighters", goku, krilin)

    val mission : Mission = Mission("Detener a freezer", 250, 420)

    assert(team.attack(mission).isRight)
  }
}
