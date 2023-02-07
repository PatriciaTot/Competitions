all:
	make clean
	make classes
	make execute
	make tests	
	make archive
	make doc
	make jar

classes:
	javac -sourcepath src -d classes src/competition/competition/*.java
	javac -sourcepath src -d classes src/competition/strategy/*.java
	javac -sourcepath src -d classes src/competition/match/*.java
	javac -sourcepath src -d classes src/competition/competitor/*.java
	javac -sourcepath src -d classes src/competition/util/*.java
	javac -sourcepath src -d classes src/competition/observer/*.java
	javac -sourcepath src -d classes src/competition/displayer/*.java
	
execute:
	java -classpath classes competition/competition/CompetitionMain

tests:
	--javac -d classes -classpath junit-platform-console-standalone-1.9.0.jar ./src/competition/competition/*.java ./src/competition/displayer/*.java ./src/competition/strategy/*.java ./src/competition/observer/*.java ./src/competition/competitor/*.java ./src/competition/match/*.java ./src/competition/util/*.java ./test/competition/*.java ./test/competitor/*.java ./test/match/*.java ./test/mock/*.java ./test/observer/*.java ./test/strategy/*.java
	java -jar junit-platform-console-standalone-1.9.0.jar -cp classes --scan-classpath --disable-banner

archive:
	jar cvfe monArchive.jar competition.competition.CompetitionMain -C classes competition/competition -C classes competition/observer -C classes competition/match -C classes competition/displayer -C classes competition/competitor -C classes competition/util -C classes competition/strategy
	
jar:
	java -jar monArchive.jar
	
doc:
	javadoc -sourcepath src -d docs -subpackages competition

clean:
	rm -f -r classes/
	rm -f -r docs/
	rm -f test/*.class
