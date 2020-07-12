package traitsAndMixin

object TraitsExample extends App {

  trait Friend {
    val name: String

    def listen(): Unit = println(s"I'm $name... listening...")
  }

  trait Curious {
    def pokeAround(): Unit = println("....curious....")
  }

  class Animal(val name: String)

  class Human(override val name: String) extends Animal(name) with Friend

  class Dog(override val name: String) extends Animal(name) with Friend with Curious

  class Cat(override val name: String) extends Animal(name)

  def seekHelpFrom(friend: Friend) = friend.listen()

  val sam = new Human("Sam")
  println(sam.getClass)
  seekHelpFrom(sam)

  val buddy = new Dog("Buddy")
  println(buddy.getClass)
  seekHelpFrom(buddy)
  buddy.pokeAround

  val rebecca = new Cat("Rebecca") with Friend with Curious
  println(rebecca.getClass)
  seekHelpFrom(rebecca)
  rebecca.pokeAround
}
