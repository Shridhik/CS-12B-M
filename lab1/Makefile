
#-----------------------------------------------------------------------------
# Makefile
# Shridhik John
# shjohn@ucsc.edu
# 12B
# 6/29/17
# lab1
# Makefile with macros that includes a check function
#-----------------------------------------------------------------------------

JAVASRC = HelloUser.java HelloUser2.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = HelloUser2
CLASSES = HelloUser.class HelloUser2.class
JARFILE = Hello
SUBMIT = submit cmps012b-pt.u17 lab1
CHECK = ls  /afs/cats.ucsc.edu/class/cmps012b-pt.u17/lab1/shjohn
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
