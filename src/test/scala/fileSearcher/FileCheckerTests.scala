package fileSearcher

import java.io.File

import org.scalatest.flatspec.AnyFlatSpec

class FileCheckerTests extends AnyFlatSpec {
  "FilterChecker passed a list where one file matches the filter" should
    "return a list with that file" in {
    val matchingFile = FileObject(new File("match"))
    val listOfFiles = List(FileObject(new File("random")), matchingFile)
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
    assert(matchedFiles == List(matchingFile))
  }

  "FilterChecker passed a list where a directory that matches the filter" should
    "not return the directory" in {
    val listOfIOObjects = List(FileObject(new File("random")), DirectoryObject(new File("match")))
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfIOObjects
    assert(matchedFiles.length == 0)
  }
}