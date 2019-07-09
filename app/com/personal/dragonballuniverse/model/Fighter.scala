package com.personal.dragonballuniverse.model

object Fighter {

  trait Fighter {

    def bestSkill: Option[Skill]

    def reduceHealth(damage: Int): Option[Fighter]

    def consumeEnergy(skill: Option[Skill]): Fighter

    def attack(target : Fighter) : Either[Fighter, Fighter] = {
      val skill: Option[Skill] = this.bestSkill
      target.reduceHealth(skill.map(_.damage).getOrElse(0)) match {
        case None => Right(this.consumeEnergy(skill))
        case Some(att) => att.attack(this.consumeEnergy(skill)).swap
      }
    }

    def id : String

    def members : Int = 1

    def health: Int
  }

  case class Warrior private (id: String, energy: Int, health : Int, skills : List[Skill]) extends Fighter {

    def break: Warrior = this.copy(energy = this.energy + 100)

    def bestSkill: Option[Skill] = {
      skills.filter(_.energy <= this.energy).sortBy(_.damage)(Ordering[Int].reverse).headOption
    }

    def reduceHealth(damage : Int) : Option[Warrior] = this.health - damage match {
      case cond if (cond <= 0) => None
      case _ => Option(this.copy(health = this.health - damage))
    }

    def consumeEnergy(skill: Option[Skill]): Warrior = skill match {
      case None => break
      case Some(s) => this.copy(energy = this.energy - s.energy)
    }
  }

  case class Mission(id: String, damage : Int, health : Int) extends Fighter {

    def bestSkill() : Option[Skill] = {
      Option(DefectSkill(damage = this.damage))
    }

    def reduceHealth(damage: Int) : Option[Mission] = this.health - damage match {
      case cond if (cond <= 0) => None
      case _ => Option(this.copy(health = this.health - damage))
  }

    def consumeEnergy(skill: Option[Skill]): Mission = this
  }

  trait Skill {

    def energy : Int
    def damage : Int
  }

  case class DefectSkill(energy : Int = 0, damage: Int) extends Skill
  case class HighKick(energy : Int, damage : Int) extends Skill
  case class LowKick(energy : Int, damage : Int) extends Skill
  case class Kamehameha(energy : Int, damage : Int) extends Skill
  case class FinalFlash(energy : Int, damage : Int) extends Skill
  case class Makankosanpo(energy : Int, damage : Int) extends Skill
}

