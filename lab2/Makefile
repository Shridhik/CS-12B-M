# Makefile
# Shridhik John
# Cmps12b
# shjohn@unix.ucsc.edu
# lab2
# A simple Makefile for the FileReverse program
FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest 
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod u+x FileReverse

FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java

clean:
	rm FileReverse.class FileReverse

submit: Makefile FileReverse.java
	submit cmps012b-pt.u17 lab2 Makefile FileReverse.java README

check: 
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab2/shjohn
