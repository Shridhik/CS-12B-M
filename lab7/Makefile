#------------------------------------------------------------------------------
# Makefile
# Shridhik John
# shjohn@csc.edu
# lab7
# makefile for IntegerList ADT
#------------------------------------------------------------------------------

JAVASRC    = Dictionary.java DictionaryInterface.java DictionaryClient.java\
             DuplicateKeyException.java KeyNotFoundException.java
MAINCLASS  = DictionaryClient
CLASSES    = Dictionary.class DictionaryInterface.class DictionaryClient.class\
             DuplicateKeyException.class KeyNotFoundException.class
JARFILE    = DictionaryClient
JARCLASSES = $(CLASSES)

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac $(JAVASRC)  
# Note: no -Xlint option

clean:
	rm -f $(CLASSES) $(JARFILE)
