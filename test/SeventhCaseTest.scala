import com.personal.dragonballuniverse.model.Fighter.Warrior
import com.personal.dragonballuniverse.model.Team.FighterGroup
import org.scalatest.FlatSpec

class SeventhCaseTest extends FlatSpec with SkillsTest {

  it should "Team vs team: warrior win" in {
    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))

    val team = FighterGroup("Los prescindibles", krilin, piccolo)

    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))
    val team2 = FighterGroup("El super", goku)

    assert(team.attack(team2).isLeft)
  }
}
