#-----------------------------------------------------------------------------
#  Makefile
#  Shridhik John
#  shjohn@ucsc.edu
#  12B
#  6/29/17
#  pa1
#  Makefile with macros that includes a check function
#-----------------------------------------------------------------------------

 JAVASRC = Recursion.java
 SOURCES = README Makefile $(JAVASRC)
 MAINCLASS = Recursion
 CLASSES = Recursion.class
 JARFILE = Recursion
 SUBMIT = submit cmps012b-pt.u17 pa1
 CHECK = ls  /afs/cats.ucsc.edu/class/cmps012b-pt.u17/pa1/shjohn
 
all: $(JARFILE)
	
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
check:
	$(CHECK)

