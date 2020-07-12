package traitsAndMixin

object MixinExample extends App {

  abstract class Writer {
    def write(msg: String)
  }

  class StringWriter extends Writer {
    val target = new StringBuilder

    override def write(msg: String): Unit = target.append(msg)

    override def toString() = target.toString
  }

  trait UppercaseFilter extends Writer {
    abstract override def write(msg: String): Unit = super.write(msg.toUpperCase)
  }

  trait ProfanityFilter extends Writer {
    abstract override def write(msg: String): Unit = super.write(msg.replace("stupid", "s****"))
  }

  def writeStuff(writer: Writer): Unit = {
    writer.write("This is stupid")
    println(writer)
  }

  writeStuff(new StringWriter)

  writeStuff(new StringWriter with UppercaseFilter)
  writeStuff(new StringWriter with ProfanityFilter)

  writeStuff(new StringWriter with UppercaseFilter with ProfanityFilter)
}
