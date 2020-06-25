package fileSearcher

import java.io.File

import org.scalatest.flatspec.AnyFlatSpec

class MatcherTests extends AnyFlatSpec {
  "Matcher that is passed a file matching the filter" should
    "return a list with that file name" in {
    val matcher = new Matcher("fake", "fakePath")
    val results = matcher.execute()
    assert(results == List("fakePath"))
  }

  "Matcher using a directory containing one file matching the filter" should
    "return a list with that file name" in {
    val matcher = new Matcher("readme", new File(".\\testfiles\\").getCanonicalPath)
    val results = matcher.execute()
    assert(results == List("readme.txt"))
  }

  "Matcher that is not passed a root file location" should
    "use the current location" in {
    val matcher = new Matcher("filter")
    assert(matcher.rootLocation == new File(".").getCanonicalPath)
  }

  "Matcher with subfolder checking matching a root location with two subtree files matching" should
    "return a list with those file names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath, searchSubDirectories)
    val results = matcher.execute()
    assert(results == List("notes.txt", "readme.txt", "data.txt"))
  }

  "Matcher given a path that has one file that matches file filter and content filter" should
    "return a list with those file name" in {
    val matcher = new Matcher("data", new File(".\\testfiles\\").getCanonicalPath, true, Some("Hello"))
    val matchedFiles = matcher.execute()
    assert(matchedFiles == List("data.txt"))
  }

  "Matcher given a path that has no file that matches file filter and content filter" should
    "return a list with those file name" in {
    val matcher = new Matcher("readme", new File(".\\testfiles\\").getCanonicalPath, true, Some("Hello"))
    val matchedFiles = matcher.execute()
    assert(matchedFiles == List())
  }
}
