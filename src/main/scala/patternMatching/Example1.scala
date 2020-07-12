package patternMatching

object Example1 extends App {
  // Match expression
  def matchNumber(x: Int): String = x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  println(matchNumber(1))
  println(matchNumber(5))
  println("-------------------------------")

  // Matching on case classes
  abstract class Notification

  case class Email(sender: String, title: String, body: String) extends Notification

  case class SMS(caller: String, message: String) extends Notification

  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String = {
    notification match {
      case Email(sender, title, _) => s"You got an email from $sender with title: $title"
      case SMS(caller, message) => s"You got a SMS from $caller! Message: $message"
      case VoiceRecording(name, link) => s"You received voice recording from $name! Click the link to hear it: $link"
    }
  }

  val sms = SMS("12345", "Are you there?")
  println(showNotification(sms))
  val email = Email("foo@bar.com", "Hello", "How are you?")
  println(showNotification(email))
  println("-------------------------------")

  // Pattern guards
  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
    notification match {
      case Email(sender, _, _) if importantPeopleInfo.contains(sender) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other) // nothing special, delegate to our original showNotification function
    }
  }
  val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

  val someSms = SMS("123-4567", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
  val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
  val importantSms = SMS("867-5309", "I'm here! Where are you?")

  println(showImportantNotification(someSms, importantPeopleInfo))
  println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
  println(showImportantNotification(importantEmail, importantPeopleInfo))
  println(showImportantNotification(importantSms, importantPeopleInfo))
  println("-------------------------------")

  // Matching on type only
  abstract class Device
  case class Phone(model: String) extends Device {
    def screenOff = "Turning screen off"
  }
  case class Computer(model: String) extends Device {
    def screenSaverOn = "Turning screen saver on..."
  }

  def goIdle(device: Device): String = device match {
    case p: Phone => p.screenOff
    case c: Computer => c.screenSaverOn
  }

  val iphone = Phone("iPhone")
  println(goIdle(iphone))
  val dell = Computer("Dell")
  println(goIdle(dell))
}
