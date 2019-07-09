import com.personal.dragonballuniverse.model.Fighter.Warrior
import com.personal.dragonballuniverse.model.Team.Tournament
import org.scalatest.FlatSpec

class FiveCaseTest extends FlatSpec with SkillsTest {

  it should "Tournament" in {
    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))
    val piccolo : Warrior = Warrior("Piccolo", 200, 1100, List(HighKick, LowKick))

    println(Tournament(List(piccolo, goku, krilin)))
  }
}
