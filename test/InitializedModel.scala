import com.personal.dragonballuniverse.model.Fighter.{FinalFlash, HighKick, Kamehameha, Makankosanpo, Skill}

trait SkillsTest {

  def HighKick: Skill = new HighKick(15, 20)

  def LowKick: Skill = new HighKick(5, 3)

  def Kamehameha: Skill = new Kamehameha(500, 400)

  def FinalFlash: Skill = new FinalFlash(400, 350)

  def Makankosapo: Skill = new Makankosanpo(1000, 700)

  def Blaster: Skill = new Makankosanpo(300, 300)
}
