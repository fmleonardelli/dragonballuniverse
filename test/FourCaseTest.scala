import com.personal.dragonballuniverse.model.Fighter.Warrior
import org.scalatest.FlatSpec

class FourCaseTest extends FlatSpec with SkillsTest {

  it should "Fight between Warriors" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))

    assert(krilin.attack(goku).isLeft)
  }

  it should "Fight between Warriors 2" in {

    val krilin : Warrior = Warrior("Krilin", 50, 300, List(HighKick))
    val goku : Warrior = Warrior("Goku", 300, 1500, List(Kamehameha))

    assert(goku.attack(krilin).isRight)
  }
}
