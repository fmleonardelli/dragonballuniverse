import com.personal.dragonballuniverse.model.Fighter.Warrior
import com.personal.dragonballuniverse.model.Team.FighterGroup
import org.scalatest.FlatSpec

class SixthCaseTest extends FlatSpec with SkillsTest {

  it should "Team vs warrior: warrior win" in {
    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))

    val team = FighterGroup("Los prescindibles", krilin, piccolo)

    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))

    assert(team.attack(goku).isLeft)
  }

  it should "Team vs warrior: team win" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))
    val freezer : Warrior = Warrior("Freezer", 300, 1500, List(Kamehameha))

    val team = FighterGroup("Los prescindibles", krilin, piccolo, freezer)

    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))

    assert(team.attack(goku).isRight)
  }
}
