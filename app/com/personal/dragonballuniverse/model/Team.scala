package com.personal.dragonballuniverse.model

import Fighter.{Fighter, Mission, Skill, Warrior}

object Team {

  case class FighterGroup private (id: String, fighters: List[Fighter]) extends Fighter {

    def bestSkill: Option[Skill] = fighters match {
      case Nil => None
      case x :: _ => x.bestSkill
    }

    def reduceHealth(damage: Int): Option[FighterGroup] = fighters match {
      case Nil => None
      case x :: Nil => x.reduceHealth(damage).map(w => this.copy(fighters = List(w)))
      case x :: xs =>  Option(this.copy(fighters = (x.reduceHealth(damage) ++ xs).toList))
    }

    def consumeEnergy(skill: Option[Skill]): FighterGroup = fighters.headOption.map(_.consumeEnergy(skill)) match {
      case None => this
      case Some(w) => this.copy(fighters = w :: fighters.tail)
    }

    override def members: Int = this.fighters.length

    override def health: Int = this.fighters.foldLeft(0)((seed, elem) => seed + elem.health)
  }

  object FighterGroup {

    def apply(): FighterGroup = new FighterGroup("EmptyGroup", List())
    def apply(id: String, warrior: Warrior, warriors: Warrior*): FighterGroup = FighterGroup(id, warrior :: warriors.toList)
    def apply(id: String, mission: Mission, missions: Mission*): FighterGroup = FighterGroup(id, mission :: missions.toList)
  }

  object Tournament {

    def apply(fighters: List[Fighter]) : Fighter = fighters match {
      case x :: Nil => x
      case x :: y :: z => apply(z ::: List(x.attack(y) match { case Right(a) => x  case Left(b) => y }))
    }
  }
}
