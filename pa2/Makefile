# Makefile
# Shridhik John
# Cmps12b
# shjohn@unix.ucsc.edu
# pa2
# A simple Makefile for the Search program
Search: Search.class
	echo Main-class: Search > Manifest 
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod u+x Search

Search.class: Search.java
	javac -Xlint Search.java

clean:
	rm Search.class Search

submit: Makefile Search.java
	submit cmps012b-pt.u17 pa2 Makefile Search.java README

check: 
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.u17/pa2/shjohn
